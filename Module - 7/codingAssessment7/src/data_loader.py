"""
Data Loader Module - Reads CSV files with exception handling.
"""

import pandas as pd
import logging

from src.config import INVESTORS_FILE, FUNDS_FILE, TRANSACTIONS_FILE, NAV_HISTORY_FILE

logger = logging.getLogger(__name__)


def load_csv(file_path: str) -> pd.DataFrame:
    """Load a CSV file with error handling for missing or corrupted files."""
    try:
        df = pd.read_csv(file_path)
        logger.info(f"Successfully loaded {file_path} with {len(df)} records.")
        return df
    except FileNotFoundError:
        logger.error(f"File not found: {file_path}")
        raise
    except pd.errors.EmptyDataError:
        logger.error(f"File is empty: {file_path}")
        raise
    except pd.errors.ParserError:
        logger.error(f"File is corrupted or malformed: {file_path}")
        raise
    except Exception as e:
        logger.error(f"Unexpected error reading {file_path}: {e}")
        raise


def load_all_data() -> dict:
    """Load all CSV files and return as a dictionary of DataFrames."""
    logger.info("Loading all data files...")

    data = {
        "investors": load_csv(INVESTORS_FILE),
        "funds": load_csv(FUNDS_FILE),
        "transactions": load_csv(TRANSACTIONS_FILE),
        "nav_history": load_csv(NAV_HISTORY_FILE),
    }

    # Convert date columns
    data["transactions"]["TransactionDate"] = pd.to_datetime(
        data["transactions"]["TransactionDate"]
    )
    data["nav_history"]["Date"] = pd.to_datetime(data["nav_history"]["Date"])

    logger.info("All data files loaded successfully.")
    return data


def merge_all_data(data: dict) -> pd.DataFrame:
    """Merge investors, transactions, funds, and nav_history into a single DataFrame."""
    logger.info("Merging all data...")

    # Merge transactions with investors
    merged = data["transactions"].merge(data["investors"], on="InvestorID", how="left")

    # Merge with funds
    merged = merged.merge(data["funds"], on="FundID", how="left")

    # Merge with nav_history (match on FundID and Date)
    merged = merged.merge(
        data["nav_history"],
        left_on=["FundID", "TransactionDate"],
        right_on=["FundID", "Date"],
        how="left",
        suffixes=("", "_history"),
    )

    logger.info(f"Merged dataset has {len(merged)} records with {len(merged.columns)} columns.")
    return merged
