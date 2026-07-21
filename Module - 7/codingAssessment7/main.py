"""
Automated Mutual Fund Performance Dashboard
============================================
Main entry point for the portfolio analysis system.

This script:
1. Reads all CSV files
2. Cleans missing values
3. Removes duplicate transactions
4. Calculates portfolio metrics
5. Ranks funds
6. Identifies high-value investors
7. Creates charts
8. Exports reports
9. Logs execution status
"""

import os
import sys
import logging
from datetime import datetime

# Add project root to path
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from src.config import OUTPUT_DIR, LOGS_DIR
from src.data_loader import load_all_data, merge_all_data
from src.data_cleaning import clean_data
from src.numpy_analysis import run_numpy_analysis
from src.fund_analysis import run_fund_analysis
from src.investor_analysis import run_investor_analysis
from src.finance_metrics import run_finance_metrics
from src.visualization import generate_all_charts
from src.report_generator import generate_reports


def setup_logging():
    """Configure logging for the application."""
    os.makedirs(LOGS_DIR, exist_ok=True)
    log_file = os.path.join(LOGS_DIR, f"execution_{datetime.now().strftime('%Y%m%d_%H%M%S')}.log")

    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s | %(levelname)-8s | %(name)s | %(message)s",
        datefmt="%Y-%m-%d %H:%M:%S",
        handlers=[
            logging.FileHandler(log_file, encoding="utf-8"),
            logging.StreamHandler(sys.stdout),
        ],
    )
    return logging.getLogger(__name__)


def main():
    """Main execution function for the Automated Mutual Fund Performance Dashboard."""
    logger = setup_logging()

    logger.info("=" * 80)
    logger.info("AUTOMATED MUTUAL FUND PERFORMANCE DASHBOARD")
    logger.info(f"Execution started at: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    logger.info("=" * 80)

    try:
        # Step 1: Read all CSV files
        logger.info("\n[STEP 1] Loading data files...")
        data = load_all_data()

        # Step 2: Clean missing values and Step 3: Remove duplicates
        logger.info("\n[STEP 2] Cleaning data (missing values, duplicates, outliers)...")
        data = clean_data(data)

        # Step 4: Merge all data
        logger.info("\n[STEP 3] Merging datasets...")
        merged_data = merge_all_data(data)

        # Step 5: NumPy Statistical Analysis
        logger.info("\n[STEP 4] Running NumPy statistical analysis...")
        numpy_results = run_numpy_analysis(data)

        # Step 6: Fund Analysis & Rankings
        logger.info("\n[STEP 5] Running fund analysis...")
        fund_results = run_fund_analysis(data)

        # Step 7: Investor Analysis
        logger.info("\n[STEP 6] Running investor analysis...")
        investor_results = run_investor_analysis(data)

        # Step 8: Finance Metrics
        logger.info("\n[STEP 7] Calculating finance metrics...")
        finance_results = run_finance_metrics(data)

        # Compile all results
        analysis_results = {
            "numpy_analysis": numpy_results,
            "fund_analysis": fund_results,
            "investor_analysis": investor_results,
            "finance_metrics": finance_results,
        }

        # Step 9: Generate Charts
        logger.info("\n[STEP 8] Generating charts...")
        generate_all_charts(data, analysis_results)

        # Step 10: Export Reports
        logger.info("\n[STEP 9] Generating and exporting reports...")
        report = generate_reports(analysis_results)

        # Print final report summary
        logger.info("\n" + report)

        logger.info("\n" + "=" * 80)
        logger.info("DASHBOARD EXECUTION COMPLETED SUCCESSFULLY")
        logger.info(f"Execution finished at: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
        logger.info(f"Output directory: {OUTPUT_DIR}")
        logger.info("=" * 80)

    except FileNotFoundError as e:
        logger.error(f"Data file missing: {e}")
        sys.exit(1)
    except Exception as e:
        logger.error(f"Unexpected error during execution: {e}", exc_info=True)
        sys.exit(1)


if __name__ == "__main__":
    main()
