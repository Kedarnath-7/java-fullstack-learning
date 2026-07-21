"""
NumPy Analysis Module - Statistical calculations using NumPy.
"""

import numpy as np
import pandas as pd
import logging

logger = logging.getLogger(__name__)


def calculate_mean_investment(transactions: pd.DataFrame) -> float:
    """Calculate mean investment amount."""
    amounts = transactions[transactions["TransactionType"] == "Buy"]["Amount"].to_numpy()
    mean_val = np.mean(amounts)
    logger.info(f"Mean Investment Amount: ₹{mean_val:,.2f}")
    return mean_val


def calculate_median_income(investors: pd.DataFrame) -> float:
    """Calculate median investor income."""
    incomes = investors["AnnualIncome"].to_numpy()
    median_val = np.median(incomes)
    logger.info(f"Median Investor Income: ₹{median_val:,.2f}")
    return median_val


def calculate_nav_std_deviation(nav_history: pd.DataFrame) -> float:
    """Calculate standard deviation of NAV."""
    navs = nav_history["NAV"].to_numpy()
    std_val = np.std(navs)
    logger.info(f"Standard Deviation of NAV: {std_val:.4f}")
    return std_val


def calculate_percentile_returns(transactions: pd.DataFrame, funds: pd.DataFrame,
                                  nav_history: pd.DataFrame) -> dict:
    """Calculate 90th and 95th percentile fund returns."""
    # Calculate returns per fund using first and last NAV
    fund_returns = []
    for fund_id in nav_history["FundID"].unique():
        fund_nav = nav_history[nav_history["FundID"] == fund_id].sort_values("Date")
        if len(fund_nav) >= 2:
            first_nav = fund_nav.iloc[0]["NAV"]
            last_nav = fund_nav.iloc[-1]["NAV"]
            if first_nav > 0:
                ret = ((last_nav - first_nav) / first_nav) * 100
                fund_returns.append(ret)

    returns_arr = np.array(fund_returns)
    p90 = np.percentile(returns_arr, 90) if len(returns_arr) > 0 else 0
    p95 = np.percentile(returns_arr, 95) if len(returns_arr) > 0 else 0

    logger.info(f"90th Percentile Fund Returns: {p90:.4f}%")
    logger.info(f"95th Percentile Fund Returns: {p95:.4f}%")

    return {"90th_percentile": p90, "95th_percentile": p95}


def calculate_income_investment_correlation(investors: pd.DataFrame,
                                            transactions: pd.DataFrame) -> float:
    """Calculate correlation between Investor Income and Investment Amount."""
    # Aggregate investment per investor
    investor_investments = (
        transactions[transactions["TransactionType"] == "Buy"]
        .groupby("InvestorID")["Amount"]
        .sum()
        .reset_index()
    )
    merged = investors.merge(investor_investments, on="InvestorID", how="inner")

    if len(merged) < 2:
        logger.warning("Not enough data for correlation calculation.")
        return 0.0

    incomes = merged["AnnualIncome"].to_numpy()
    amounts = merged["Amount"].to_numpy()
    correlation = np.corrcoef(incomes, amounts)[0, 1]

    logger.info(f"Correlation (Income vs Investment): {correlation:.4f}")
    return correlation


def calculate_average_daily_nav(nav_history: pd.DataFrame) -> pd.DataFrame:
    """Calculate average daily NAV across all funds."""
    avg_nav = nav_history.groupby("Date")["NAV"].mean().reset_index()
    avg_nav.columns = ["Date", "AverageDailyNAV"]

    overall_avg = np.mean(nav_history["NAV"].to_numpy())
    logger.info(f"Overall Average Daily NAV: {overall_avg:.4f}")

    return avg_nav


def run_numpy_analysis(data: dict) -> dict:
    """Run all NumPy-based statistical analyses."""
    logger.info("=" * 60)
    logger.info("NUMPY STATISTICAL ANALYSIS")
    logger.info("=" * 60)

    results = {
        "mean_investment": calculate_mean_investment(data["transactions"]),
        "median_income": calculate_median_income(data["investors"]),
        "nav_std_deviation": calculate_nav_std_deviation(data["nav_history"]),
        "percentile_returns": calculate_percentile_returns(
            data["transactions"], data["funds"], data["nav_history"]
        ),
        "income_investment_correlation": calculate_income_investment_correlation(
            data["investors"], data["transactions"]
        ),
        "average_daily_nav": calculate_average_daily_nav(data["nav_history"]),
    }

    return results
