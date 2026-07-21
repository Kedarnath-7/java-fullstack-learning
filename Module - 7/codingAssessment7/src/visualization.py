"""
Data Visualization Module - Generate charts for portfolio analysis.
"""

import pandas as pd
import matplotlib
matplotlib.use("Agg")  # Non-interactive backend
import matplotlib.pyplot as plt
import os
import logging

from src.config import CHARTS_DIR

logger = logging.getLogger(__name__)


def _ensure_charts_dir():
    """Create charts output directory if it doesn't exist."""
    os.makedirs(CHARTS_DIR, exist_ok=True)


def plot_portfolio_allocation(category_investment: pd.DataFrame):
    """Generate Portfolio Allocation Pie Chart."""
    _ensure_charts_dir()

    fig, ax = plt.subplots(figsize=(10, 8))
    ax.pie(
        category_investment["Amount"],
        labels=category_investment["Category"],
        autopct="%1.1f%%",
        startangle=140,
        colors=plt.cm.Set3.colors,
    )
    ax.set_title("Portfolio Allocation by Category", fontsize=14, fontweight="bold")
    plt.tight_layout()

    filepath = os.path.join(CHARTS_DIR, "portfolio_allocation_pie.png")
    plt.savefig(filepath, dpi=150, bbox_inches="tight")
    plt.close()
    logger.info(f"Chart saved: {filepath}")


def plot_fund_wise_investment(fund_allocation: pd.DataFrame):
    """Generate Fund-wise Investment Bar Chart."""
    _ensure_charts_dir()

    fig, ax = plt.subplots(figsize=(12, 6))
    top_funds = fund_allocation.head(15)
    ax.bar(range(len(top_funds)), top_funds["Amount"], color="steelblue")
    ax.set_xticks(range(len(top_funds)))
    ax.set_xticklabels(top_funds["FundName"], rotation=45, ha="right", fontsize=8)
    ax.set_xlabel("Fund")
    ax.set_ylabel("Investment Amount (₹)")
    ax.set_title("Fund-wise Investment", fontsize=14, fontweight="bold")
    ax.yaxis.set_major_formatter(plt.FuncFormatter(lambda x, p: f"₹{x:,.0f}"))
    plt.tight_layout()

    filepath = os.path.join(CHARTS_DIR, "fund_wise_investment_bar.png")
    plt.savefig(filepath, dpi=150, bbox_inches="tight")
    plt.close()
    logger.info(f"Chart saved: {filepath}")


def plot_monthly_investment_trend(transactions: pd.DataFrame):
    """Generate Monthly Investment Trend Line Chart."""
    _ensure_charts_dir()

    buy_txns = transactions[transactions["TransactionType"] == "Buy"].copy()
    buy_txns["Month"] = buy_txns["TransactionDate"].dt.to_period("M")
    monthly = buy_txns.groupby("Month")["Amount"].sum().reset_index()
    monthly["Month"] = monthly["Month"].astype(str)

    fig, ax = plt.subplots(figsize=(12, 6))
    ax.plot(monthly["Month"], monthly["Amount"], marker="o", linewidth=2, color="green")
    ax.set_xlabel("Month")
    ax.set_ylabel("Investment Amount (₹)")
    ax.set_title("Monthly Investment Trend", fontsize=14, fontweight="bold")
    ax.yaxis.set_major_formatter(plt.FuncFormatter(lambda x, p: f"₹{x:,.0f}"))
    plt.xticks(rotation=45, ha="right")
    ax.grid(True, alpha=0.3)
    plt.tight_layout()

    filepath = os.path.join(CHARTS_DIR, "monthly_investment_trend_line.png")
    plt.savefig(filepath, dpi=150, bbox_inches="tight")
    plt.close()
    logger.info(f"Chart saved: {filepath}")


def plot_category_wise_returns(nav_history: pd.DataFrame, funds: pd.DataFrame):
    """Generate Category-wise Returns Bar Chart."""
    _ensure_charts_dir()

    # Calculate returns per fund
    fund_returns = []
    for fund_id in nav_history["FundID"].unique():
        fund_nav = nav_history[nav_history["FundID"] == fund_id].sort_values("Date")
        if len(fund_nav) >= 2:
            first_nav = fund_nav.iloc[0]["NAV"]
            last_nav = fund_nav.iloc[-1]["NAV"]
            if first_nav > 0:
                ret = ((last_nav - first_nav) / first_nav) * 100
                fund_returns.append({"FundID": fund_id, "Return%": ret})

    returns_df = pd.DataFrame(fund_returns)
    if returns_df.empty:
        logger.warning("No fund returns to plot.")
        return

    returns_df = returns_df.merge(funds[["FundID", "Category"]], on="FundID", how="left")
    category_returns = returns_df.groupby("Category")["Return%"].mean().reset_index()
    category_returns = category_returns.sort_values("Return%", ascending=False)

    fig, ax = plt.subplots(figsize=(10, 6))
    colors = ["green" if x >= 0 else "red" for x in category_returns["Return%"]]
    ax.bar(category_returns["Category"], category_returns["Return%"], color=colors)
    ax.set_xlabel("Category")
    ax.set_ylabel("Average Return (%)")
    ax.set_title("Category-wise Returns", fontsize=14, fontweight="bold")
    plt.xticks(rotation=45, ha="right")
    ax.axhline(y=0, color="black", linewidth=0.5)
    ax.grid(True, alpha=0.3, axis="y")
    plt.tight_layout()

    filepath = os.path.join(CHARTS_DIR, "category_wise_returns_bar.png")
    plt.savefig(filepath, dpi=150, bbox_inches="tight")
    plt.close()
    logger.info(f"Chart saved: {filepath}")


def plot_nav_movement(nav_history: pd.DataFrame, funds: pd.DataFrame):
    """Generate NAV Movement Line Chart."""
    _ensure_charts_dir()

    fig, ax = plt.subplots(figsize=(12, 6))

    for fund_id in nav_history["FundID"].unique():
        fund_nav = nav_history[nav_history["FundID"] == fund_id].sort_values("Date")
        fund_name = funds[funds["FundID"] == fund_id]["FundName"].values
        label = fund_name[0] if len(fund_name) > 0 else fund_id
        ax.plot(fund_nav["Date"], fund_nav["NAV"], marker=".", linewidth=1.5, label=label)

    ax.set_xlabel("Date")
    ax.set_ylabel("NAV")
    ax.set_title("NAV Movement Over Time", fontsize=14, fontweight="bold")
    ax.legend(loc="best", fontsize=8)
    ax.grid(True, alpha=0.3)
    plt.xticks(rotation=45, ha="right")
    plt.tight_layout()

    filepath = os.path.join(CHARTS_DIR, "nav_movement_line.png")
    plt.savefig(filepath, dpi=150, bbox_inches="tight")
    plt.close()
    logger.info(f"Chart saved: {filepath}")


def plot_top_investors(top_investors: pd.DataFrame):
    """Generate Top 10 Investors Horizontal Bar Chart."""
    _ensure_charts_dir()

    top_10 = top_investors.head(10)

    fig, ax = plt.subplots(figsize=(10, 6))
    ax.barh(
        range(len(top_10)),
        top_10["PortfolioValue"],
        color="darkorange",
    )
    ax.set_yticks(range(len(top_10)))
    ax.set_yticklabels(top_10["InvestorName"])
    ax.set_xlabel("Portfolio Value (₹)")
    ax.set_title("Top 10 Investors by Portfolio Value", fontsize=14, fontweight="bold")
    ax.xaxis.set_major_formatter(plt.FuncFormatter(lambda x, p: f"₹{x:,.0f}"))
    ax.invert_yaxis()
    ax.grid(True, alpha=0.3, axis="x")
    plt.tight_layout()

    filepath = os.path.join(CHARTS_DIR, "top_10_investors_horizontal_bar.png")
    plt.savefig(filepath, dpi=150, bbox_inches="tight")
    plt.close()
    logger.info(f"Chart saved: {filepath}")


def generate_all_charts(data: dict, analysis_results: dict):
    """Generate all required charts."""
    logger.info("=" * 60)
    logger.info("GENERATING CHARTS")
    logger.info("=" * 60)

    # Portfolio Allocation Pie Chart
    if "category_wise_investment" in analysis_results.get("finance_metrics", {}):
        plot_portfolio_allocation(analysis_results["finance_metrics"]["category_wise_investment"])

    # Fund-wise Investment Bar Chart
    if "fund_allocation_pct" in analysis_results.get("finance_metrics", {}):
        plot_fund_wise_investment(analysis_results["finance_metrics"]["fund_allocation_pct"])

    # Monthly Investment Trend Line Chart
    plot_monthly_investment_trend(data["transactions"])

    # Category-wise Returns Bar Chart
    plot_category_wise_returns(data["nav_history"], data["funds"])

    # NAV Movement Line Chart
    plot_nav_movement(data["nav_history"], data["funds"])

    # Top 10 Investors Horizontal Bar Chart
    if "top_20_investors" in analysis_results.get("investor_analysis", {}):
        plot_top_investors(analysis_results["investor_analysis"]["top_20_investors"])

    logger.info("All charts generated successfully.")
