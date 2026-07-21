"""
Data Cleaning Module - Handle missing values, duplicates, and outliers.
"""

import pandas as pd
import numpy as np
import logging

from src.config import OUTLIER_PERCENTILE, NAV_STD_DEVIATION_THRESHOLD

logger = logging.getLogger(__name__)


def handle_missing_values(data: dict) -> dict:
    """
    Replace missing values as per requirements:
    - AnnualIncome → Median
    - ExpenseRatio → Mean
    - NAV → Previous Day NAV
    - RiskProfile → "Moderate"
    """
    logger.info("Handling missing values...")

    # AnnualIncome → Median
    investors = data["investors"].copy()
    median_income = investors["AnnualIncome"].median()
    investors["AnnualIncome"] = investors["AnnualIncome"].fillna(median_income)
    logger.info(f"Replaced missing AnnualIncome with median: {median_income}")

    # RiskProfile → "Moderate"
    investors["RiskProfile"] = investors["RiskProfile"].fillna("Moderate")
    logger.info("Replaced missing RiskProfile with 'Moderate'")

    # ExpenseRatio → Mean
    funds = data["funds"].copy()
    mean_expense = funds["ExpenseRatio"].mean()
    funds["ExpenseRatio"] = funds["ExpenseRatio"].fillna(mean_expense)
    logger.info(f"Replaced missing ExpenseRatio with mean: {mean_expense:.4f}")

    # NAV → Previous Day NAV (forward fill within each fund)
    nav_history = data["nav_history"].sort_values(["FundID", "Date"]).copy()
    nav_history["NAV"] = nav_history.groupby("FundID")["NAV"].ffill()
    # If first value is missing, backfill
    nav_history["NAV"] = nav_history.groupby("FundID")["NAV"].bfill()
    data["nav_history"] = nav_history
    logger.info("Replaced missing NAV with Previous Day NAV (forward fill)")

    # Also handle NAV in transactions
    transactions = data["transactions"].copy()
    transactions["NAV"] = transactions["NAV"].ffill()

    data["investors"] = investors
    data["funds"] = funds
    data["transactions"] = transactions

    logger.info("Missing value handling complete.")
    return data


def remove_duplicate_transactions(data: dict) -> dict:
    """Remove duplicate transactions."""
    before = len(data["transactions"])
    data["transactions"] = data["transactions"].drop_duplicates()
    after = len(data["transactions"])
    removed = before - after
    logger.info(f"Removed {removed} duplicate transactions. ({before} → {after})")
    return data


def detect_and_remove_outliers(data: dict) -> dict:
    """
    Remove outliers:
    - Investment Amount > 99th Percentile
    - NAV Changes > 3 Standard Deviations
    """
    logger.info("Detecting and removing outliers...")

    # Investment Amount > 99th Percentile
    transactions = data["transactions"]
    percentile_99 = np.percentile(transactions["Amount"].dropna(), OUTLIER_PERCENTILE)
    before = len(transactions)
    transactions = transactions[transactions["Amount"] <= percentile_99]
    after = len(transactions)
    logger.info(
        f"Removed {before - after} transactions with Amount > 99th percentile (₹{percentile_99:,.2f})"
    )
    data["transactions"] = transactions

    # NAV Changes > 3 Standard Deviations
    nav_history = data["nav_history"].sort_values(["FundID", "Date"])
    nav_history["NAV_Change"] = nav_history.groupby("FundID")["NAV"].diff()
    nav_std = nav_history["NAV_Change"].std()
    nav_mean = nav_history["NAV_Change"].mean()
    lower_bound = nav_mean - NAV_STD_DEVIATION_THRESHOLD * nav_std
    upper_bound = nav_mean + NAV_STD_DEVIATION_THRESHOLD * nav_std

    before = len(nav_history)
    nav_history = nav_history[
        (nav_history["NAV_Change"].isna())  # Keep first entries (no change calculated)
        | ((nav_history["NAV_Change"] >= lower_bound) & (nav_history["NAV_Change"] <= upper_bound))
    ]
    after = len(nav_history)
    logger.info(f"Removed {before - after} NAV entries with changes > 3 std deviations")

    nav_history = nav_history.drop(columns=["NAV_Change"])
    data["nav_history"] = nav_history

    logger.info("Outlier removal complete.")
    return data


def clean_data(data: dict) -> dict:
    """Run all data cleaning steps."""
    data = handle_missing_values(data)
    data = remove_duplicate_transactions(data)
    data = detect_and_remove_outliers(data)
    return data
