"""
Report Generator Module - Generate and export reports with logging.
"""

import pandas as pd
import os
import logging
from datetime import datetime

from src.config import REPORTS_DIR

logger = logging.getLogger(__name__)


def _ensure_reports_dir():
    """Create reports output directory if it doesn't exist."""
    os.makedirs(REPORTS_DIR, exist_ok=True)


def generate_summary_report(analysis_results: dict) -> str:
    """Generate a comprehensive text summary report."""
    _ensure_reports_dir()

    report_lines = []
    report_lines.append("=" * 80)
    report_lines.append("MUTUAL FUND PORTFOLIO PERFORMANCE & RISK ANALYSIS REPORT")
    report_lines.append(f"Generated on: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    report_lines.append("=" * 80)

    # NumPy Analysis Section
    if "numpy_analysis" in analysis_results:
        numpy_res = analysis_results["numpy_analysis"]
        report_lines.append("\n" + "-" * 60)
        report_lines.append("STATISTICAL ANALYSIS (NumPy)")
        report_lines.append("-" * 60)
        report_lines.append(f"Mean Investment Amount:          ₹{numpy_res['mean_investment']:,.2f}")
        report_lines.append(f"Median Investor Income:          ₹{numpy_res['median_income']:,.2f}")
        report_lines.append(f"NAV Standard Deviation:          {numpy_res['nav_std_deviation']:.4f}")
        report_lines.append(
            f"90th Percentile Fund Returns:    {numpy_res['percentile_returns']['90th_percentile']:.4f}%"
        )
        report_lines.append(
            f"95th Percentile Fund Returns:    {numpy_res['percentile_returns']['95th_percentile']:.4f}%"
        )
        report_lines.append(
            f"Income-Investment Correlation:   {numpy_res['income_investment_correlation']:.4f}"
        )

    # Fund Analysis Section
    if "fund_analysis" in analysis_results:
        fund_res = analysis_results["fund_analysis"]
        report_lines.append("\n" + "-" * 60)
        report_lines.append("FUND ANALYSIS")
        report_lines.append("-" * 60)

        if fund_res.get("best_performing"):
            bp = fund_res["best_performing"]
            report_lines.append(
                f"Best Performing Fund:   {bp['FundName']} ({bp['Return%']:.2f}%)"
            )
        if fund_res.get("worst_performing"):
            wp = fund_res["worst_performing"]
            report_lines.append(
                f"Worst Performing Fund:  {wp['FundName']} ({wp['Return%']:.2f}%)"
            )
        if fund_res.get("highest_expense_ratio"):
            her = fund_res["highest_expense_ratio"]
            report_lines.append(
                f"Highest Expense Ratio:  {her['FundName']} ({her['ExpenseRatio']:.2f}%)"
            )
        if fund_res.get("highest_aum"):
            ha = fund_res["highest_aum"]
            report_lines.append(f"Highest AUM Fund:       {ha['FundName']} (₹{ha['AUM']:,.2f})")
        if fund_res.get("most_popular"):
            mp = fund_res["most_popular"]
            report_lines.append(
                f"Most Popular Fund:      {mp['FundName']} ({mp['NumInvestors']} investors)"
            )

    # Finance Metrics Section
    if "finance_metrics" in analysis_results:
        fm = analysis_results["finance_metrics"]
        report_lines.append("\n" + "-" * 60)
        report_lines.append("FINANCE METRICS")
        report_lines.append("-" * 60)
        report_lines.append(f"Total Portfolio Value:           ₹{fm['total_portfolio_value']:,.2f}")
        report_lines.append(f"Portfolio Return:                {fm['portfolio_return_pct']:.2f}%")
        report_lines.append(f"CAGR:                            {fm['cagr']:.2f}%")
        report_lines.append(f"Absolute Return:                 {fm['absolute_return']:.2f}%")
        report_lines.append(f"Annualized Return:               {fm['annualized_return']:.2f}%")
        report_lines.append(f"Diversification Score:           {fm['diversification_score']:.2f}/100")
        report_lines.append(f"Average Holding Period:          {fm['average_holding_period']:.0f} days")
        report_lines.append(f"Sharpe Ratio:                    {fm['sharpe_ratio']:.4f}")

    # Investor Analysis Section
    if "investor_analysis" in analysis_results:
        inv_res = analysis_results["investor_analysis"]
        report_lines.append("\n" + "-" * 60)
        report_lines.append("INVESTOR ANALYSIS")
        report_lines.append("-" * 60)

        if "top_20_investors" in inv_res and not inv_res["top_20_investors"].empty:
            report_lines.append("\nTop 20 Investors by Portfolio Value:")
            report_lines.append(f"{'Rank':<6}{'Name':<25}{'Portfolio Value':<20}{'P&L':<15}")
            report_lines.append("-" * 66)
            for _, row in inv_res["top_20_investors"].iterrows():
                report_lines.append(
                    f"{int(row['Rank']):<6}{row['InvestorName']:<25}"
                    f"₹{row['PortfolioValue']:>12,.2f}    ₹{row['ProfitLoss']:>10,.2f}"
                )

        if "high_value_investors" in inv_res:
            hv = inv_res["high_value_investors"]
            report_lines.append(f"\nHigh-Value Investors (meeting all criteria): {len(hv)}")
            if not hv.empty:
                for _, row in hv.iterrows():
                    report_lines.append(
                        f"  - {row['InvestorName']}: "
                        f"Investment=₹{row['TotalInvestment']:,.0f}, "
                        f"Income=₹{row['AnnualIncome']:,.0f}"
                    )

    report_lines.append("\n" + "=" * 80)
    report_lines.append("END OF REPORT")
    report_lines.append("=" * 80)

    report_text = "\n".join(report_lines)

    # Save report to file
    filepath = os.path.join(REPORTS_DIR, "portfolio_analysis_report.txt")
    with open(filepath, "w", encoding="utf-8") as f:
        f.write(report_text)
    logger.info(f"Summary report saved: {filepath}")

    return report_text


def export_to_csv(analysis_results: dict):
    """Export analysis results to CSV files."""
    _ensure_reports_dir()

    # Export fund rankings
    if "fund_analysis" in analysis_results:
        fund_res = analysis_results["fund_analysis"]
        if "fund_rankings" in fund_res and not fund_res["fund_rankings"].empty:
            filepath = os.path.join(REPORTS_DIR, "fund_rankings.csv")
            fund_res["fund_rankings"].to_csv(filepath, index=False)
            logger.info(f"Exported: {filepath}")

    # Export top investors
    if "investor_analysis" in analysis_results:
        inv_res = analysis_results["investor_analysis"]
        if "top_20_investors" in inv_res and not inv_res["top_20_investors"].empty:
            filepath = os.path.join(REPORTS_DIR, "top_20_investors.csv")
            inv_res["top_20_investors"].to_csv(filepath, index=False)
            logger.info(f"Exported: {filepath}")

        if "high_value_investors" in inv_res and not inv_res["high_value_investors"].empty:
            filepath = os.path.join(REPORTS_DIR, "high_value_investors.csv")
            inv_res["high_value_investors"].to_csv(filepath, index=False)
            logger.info(f"Exported: {filepath}")

    # Export finance metrics
    if "finance_metrics" in analysis_results:
        fm = analysis_results["finance_metrics"]

        if "category_wise_investment" in fm:
            filepath = os.path.join(REPORTS_DIR, "category_wise_investment.csv")
            fm["category_wise_investment"].to_csv(filepath, index=False)
            logger.info(f"Exported: {filepath}")

        if "fund_allocation_pct" in fm:
            filepath = os.path.join(REPORTS_DIR, "fund_allocation.csv")
            fm["fund_allocation_pct"].to_csv(filepath, index=False)
            logger.info(f"Exported: {filepath}")

        if "investor_profit_loss" in fm:
            filepath = os.path.join(REPORTS_DIR, "investor_profit_loss.csv")
            fm["investor_profit_loss"].to_csv(filepath, index=False)
            logger.info(f"Exported: {filepath}")

        if "expense_ratio_impact" in fm:
            filepath = os.path.join(REPORTS_DIR, "expense_ratio_impact.csv")
            fm["expense_ratio_impact"].to_csv(filepath, index=False)
            logger.info(f"Exported: {filepath}")

    logger.info("All CSV exports complete.")


def generate_reports(analysis_results: dict) -> str:
    """Generate all reports."""
    logger.info("=" * 60)
    logger.info("GENERATING REPORTS")
    logger.info("=" * 60)

    report_text = generate_summary_report(analysis_results)
    export_to_csv(analysis_results)

    return report_text
