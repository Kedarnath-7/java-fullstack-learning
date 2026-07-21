"""
Configuration constants for the Mutual Fund Portfolio Analysis project.
"""

import os

# Base directory
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

# Data file paths
DATA_DIR = BASE_DIR
INVESTORS_FILE = os.path.join(DATA_DIR, "investors.csv")
FUNDS_FILE = os.path.join(DATA_DIR, "funds.csv")
TRANSACTIONS_FILE = os.path.join(DATA_DIR, "transactions.csv")
NAV_HISTORY_FILE = os.path.join(DATA_DIR, "nav_history.csv")

# Output directories
OUTPUT_DIR = os.path.join(BASE_DIR, "output")
REPORTS_DIR = os.path.join(OUTPUT_DIR, "reports")
CHARTS_DIR = os.path.join(OUTPUT_DIR, "charts")
LOGS_DIR = os.path.join(OUTPUT_DIR, "logs")

# Thresholds
HIGH_INVESTMENT_THRESHOLD = 1000000  # ₹10 Lakhs
HIGH_INCOME_THRESHOLD = 1500000  # ₹15 Lakhs
MIN_TRANSACTIONS_THRESHOLD = 10
OUTLIER_PERCENTILE = 99
NAV_STD_DEVIATION_THRESHOLD = 3

# Risk-free rate for Sharpe Ratio (annualized, approx 6% for India)
RISK_FREE_RATE = 0.06
