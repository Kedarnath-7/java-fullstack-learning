"""
Finance Metrics Module - Calculate portfolio and fund financial metrics.
"""

import pandas as pd
import numpy as np
import logging

from src.config import RISK_FREE_RATE

logger = logging.getLogger(__name__)


def calculate_total_portfolio_value(transactions: pd.DataFrame,
                                     nav_history: pd.DataFrame) -> float:
    """Calculate Total Portfolio Value across all investors."""
    txns = transactions.copy()
    txns["SignedUnits"] = txns.apply(
        lambda r: r["Units"] if r["TransactionType"] == "Buy" else -r["Units"], axis=1
    )
    fund_units = txns.groupby("FundID")["SignedUnits"].sum()

    latest_nav = nav_history.sort_values("Date").groupby("FundID")["NAV"].last()

    total_value = 0.0
    for fund_id, units in fund_units.items():
        if units > 0 and fund_id in latest_nav.index:
            total_value += units * latest_nav[fund_id]

    logger.info(f"Total Portfolio Value: ₹{total_value:,.2f}")
    return total_value


def calculate_portfolio_return_pct(transactions: pd.DataFrame,
                                    nav_history: pd.DataFrame) -> float:
    """Calculate Portfolio Return %."""
    total_value = calculate_total_portfolio_value(transactions, nav_history)
    total_investment = transactions[transactions["TransactionType"] == "Buy"]["Amount"].sum()
    total_redemption = transactions[transactions["TransactionType"] == "Sell"]["Amount"].sum()
    net_investment = total_investment - total_redemption

    if net_investment == 0:
        return 0.0

    return_pct = ((total_value - net_investment) / net_investment) * 100
    logger.info(f"Portfolio Return: {return_pct:.2f}%")
    return return_pct


def calculate_cagr(transactions: pd.DataFrame, nav_history: pd.DataFrame) -> float:
    """Calculate Compound Annual Growth Rate (CAGR)."""
    total_value = calculate_total_portfolio_value(transactions, nav_history)
    total_investment = transactions[transactions["TransactionType"] == "Buy"]["Amount"].sum()

    if total_investment == 0:
        return 0.0

    # Calculate holding period in years
    first_date = transactions["TransactionDate"].min()
    last_date = nav_history["Date"].max()
    years = (last_date - first_date).days / 365.25

    if years <= 0:
        return 0.0

    cagr = ((total_value / total_investment) ** (1 / years) - 1) * 100
    logger.info(f"CAGR: {cagr:.2f}% (over {years:.2f} years)")
    return cagr


def calculate_absolute_return(transactions: pd.DataFrame,
                               nav_history: pd.DataFrame) -> float:
    """Calculate Absolute Return."""
    total_value = calculate_total_portfolio_value(transactions, nav_history)
    total_investment = transactions[transactions["TransactionType"] == "Buy"]["Amount"].sum()

    if total_investment == 0:
        return 0.0

    absolute_return = ((total_value - total_investment) / total_investment) * 100
    logger.info(f"Absolute Return: {absolute_return:.2f}%")
    return absolute_return


def calculate_annualized_return(transactions: pd.DataFrame,
                                 nav_history: pd.DataFrame) -> float:
    """Calculate Annualized Return."""
    absolute_ret = calculate_absolute_return(transactions, nav_history) / 100
    first_date = transactions["TransactionDate"].min()
    last_date = nav_history["Date"].max()
    years = (last_date - first_date).days / 365.25

    if years <= 0:
        return 0.0

    annualized = ((1 + absolute_ret) ** (1 / years) - 1) * 100
    logger.info(f"Annualized Return: {annualized:.2f}%")
    return annualized


def calculate_diversification_score(transactions: pd.DataFrame,
                                     funds: pd.DataFrame) -> float:
    """
    Calculate Portfolio Diversification Score.
    Based on number of unique categories and allocation distribution.
    Uses Herfindahl-Hirschman Index (HHI) - lower is more diversified.
    Score = 1 - HHI (normalized to 0-100).
    """
    buy_txns = transactions[transactions["TransactionType"] == "Buy"]
    fund_investment = buy_txns.groupby("FundID")["Amount"].sum().reset_index()
    fund_investment = fund_investment.merge(funds[["FundID", "Category"]], on="FundID", how="left")

    category_investment = fund_investment.groupby("Category")["Amount"].sum()
    total = category_investment.sum()

    if total == 0:
        return 0.0

    weights = (category_investment / total).to_numpy()
    hhi = np.sum(weights ** 2)
    score = (1 - hhi) * 100

    logger.info(f"Portfolio Diversification Score: {score:.2f}/100")
    return score


def calculate_average_holding_period(transactions: pd.DataFrame) -> float:
    """Calculate Average Holding Period in days."""
    if transactions.empty:
        return 0.0

    first_date = transactions["TransactionDate"].min()
    last_date = transactions["TransactionDate"].max()
    holding_period = (last_date - first_date).days

    logger.info(f"Average Holding Period: {holding_period} days")
    return holding_period


def calculate_expense_ratio_impact(transactions: pd.DataFrame,
                                    funds: pd.DataFrame,
                                    nav_history: pd.DataFrame) -> pd.DataFrame:
    """Calculate Expense Ratio Impact on portfolio value."""
    buy_txns = transactions[transactions["TransactionType"] == "Buy"]
    fund_investment = buy_txns.groupby("FundID")["Amount"].sum().reset_index()
    fund_investment = fund_investment.merge(
        funds[["FundID", "FundName", "ExpenseRatio"]], on="FundID", how="left"
    )

    # Expense impact = Investment × ExpenseRatio / 100
    fund_investment["ExpenseImpact"] = (
        fund_investment["Amount"] * fund_investment["ExpenseRatio"] / 100
    )

    total_impact = fund_investment["ExpenseImpact"].sum()
    logger.info(f"Total Expense Ratio Impact: ₹{total_impact:,.2f}")

    return fund_investment[["FundID", "FundName", "Amount", "ExpenseRatio", "ExpenseImpact"]]


def calculate_sharpe_ratio(transactions: pd.DataFrame, nav_history: pd.DataFrame) -> float:
    """
    Calculate Simplified Sharpe Ratio.
    Sharpe Ratio = (Portfolio Return - Risk Free Rate) / Portfolio Std Deviation
    """
    # Calculate daily portfolio returns from NAV history
    nav_pivot = nav_history.pivot_table(index="Date", columns="FundID", values="NAV")
    daily_returns = nav_pivot.pct_change().dropna()

    if daily_returns.empty:
        return 0.0

    # Equal-weighted portfolio return
    portfolio_daily_returns = daily_returns.mean(axis=1)
    avg_daily_return = portfolio_daily_returns.mean()
    std_daily_return = portfolio_daily_returns.std()

    if std_daily_return == 0:
        return 0.0

    # Annualize
    annualized_return = avg_daily_return * 252
    annualized_std = std_daily_return * np.sqrt(252)

    sharpe = (annualized_return - RISK_FREE_RATE) / annualized_std
    logger.info(f"Sharpe Ratio (Simplified): {sharpe:.4f}")
    return sharpe


def calculate_category_wise_investment(transactions: pd.DataFrame,
                                        funds: pd.DataFrame) -> pd.DataFrame:
    """Calculate Category-wise Investment %."""
    buy_txns = transactions[transactions["TransactionType"] == "Buy"]
    merged = buy_txns.merge(funds[["FundID", "Category"]], on="FundID", how="left")
    category_inv = merged.groupby("Category")["Amount"].sum().reset_index()
    total = category_inv["Amount"].sum()
    category_inv["Percentage"] = (category_inv["Amount"] / total) * 100
    category_inv = category_inv.sort_values("Percentage", ascending=False).reset_index(drop=True)

    logger.info("Category-wise Investment %:")
    for _, row in category_inv.iterrows():
        logger.info(f"  {row['Category']}: {row['Percentage']:.2f}%")

    return category_inv


def calculate_fund_allocation_pct(transactions: pd.DataFrame,
                                   funds: pd.DataFrame) -> pd.DataFrame:
    """Calculate Fund Allocation %."""
    buy_txns = transactions[transactions["TransactionType"] == "Buy"]
    fund_inv = buy_txns.groupby("FundID")["Amount"].sum().reset_index()
    fund_inv = fund_inv.merge(funds[["FundID", "FundName"]], on="FundID", how="left")
    total = fund_inv["Amount"].sum()
    fund_inv["Allocation%"] = (fund_inv["Amount"] / total) * 100
    fund_inv = fund_inv.sort_values("Allocation%", ascending=False).reset_index(drop=True)

    return fund_inv


def calculate_investor_profit_loss(data: dict) -> pd.DataFrame:
    """Calculate Investor-wise Profit/Loss."""
    transactions = data["transactions"]
    investors = data["investors"]
    nav_history = data["nav_history"]

    # Calculate net units per investor per fund
    txns = transactions.copy()
    txns["SignedUnits"] = txns.apply(
        lambda r: r["Units"] if r["TransactionType"] == "Buy" else -r["Units"], axis=1
    )

    investor_fund_units = txns.groupby(["InvestorID", "FundID"]).agg(
        NetUnits=("SignedUnits", "sum"),
        TotalBuyAmount=("Amount", lambda x: x[txns.loc[x.index, "TransactionType"] == "Buy"].sum()),
    ).reset_index()

    # Get latest NAV
    latest_nav = nav_history.sort_values("Date").groupby("FundID")["NAV"].last().reset_index()
    latest_nav.columns = ["FundID", "LatestNAV"]

    investor_fund_units = investor_fund_units.merge(latest_nav, on="FundID", how="left")
    investor_fund_units["CurrentValue"] = investor_fund_units["NetUnits"] * investor_fund_units["LatestNAV"]

    # Aggregate per investor
    # Recalculate buy amounts properly
    buy_amounts = (
        txns[txns["TransactionType"] == "Buy"]
        .groupby("InvestorID")["Amount"]
        .sum()
        .reset_index()
    )
    buy_amounts.columns = ["InvestorID", "TotalInvestment"]

    sell_amounts = (
        txns[txns["TransactionType"] == "Sell"]
        .groupby("InvestorID")["Amount"]
        .sum()
        .reset_index()
    )
    sell_amounts.columns = ["InvestorID", "TotalRedemption"]

    current_values = (
        investor_fund_units[investor_fund_units["NetUnits"] > 0]
        .groupby("InvestorID")["CurrentValue"]
        .sum()
        .reset_index()
    )

    result = investors[["InvestorID", "InvestorName"]].copy()
    result = result.merge(buy_amounts, on="InvestorID", how="left")
    result = result.merge(sell_amounts, on="InvestorID", how="left")
    result = result.merge(current_values, on="InvestorID", how="left")

    result["TotalInvestment"] = result["TotalInvestment"].fillna(0)
    result["TotalRedemption"] = result["TotalRedemption"].fillna(0)
    result["CurrentValue"] = result["CurrentValue"].fillna(0)

    result["NetInvestment"] = result["TotalInvestment"] - result["TotalRedemption"]
    result["ProfitLoss"] = result["CurrentValue"] - result["NetInvestment"]
    result["Return%"] = np.where(
        result["NetInvestment"] > 0,
        (result["ProfitLoss"] / result["NetInvestment"]) * 100,
        0.0,
    )

    result = result.sort_values("ProfitLoss", ascending=False).reset_index(drop=True)

    logger.info("Investor-wise Profit/Loss calculated.")
    return result


def run_finance_metrics(data: dict) -> dict:
    """Run all finance metric calculations."""
    logger.info("=" * 60)
    logger.info("FINANCE METRICS")
    logger.info("=" * 60)

    transactions = data["transactions"]
    funds = data["funds"]
    nav_history = data["nav_history"]

    results = {
        "total_portfolio_value": calculate_total_portfolio_value(transactions, nav_history),
        "portfolio_return_pct": calculate_portfolio_return_pct(transactions, nav_history),
        "cagr": calculate_cagr(transactions, nav_history),
        "absolute_return": calculate_absolute_return(transactions, nav_history),
        "annualized_return": calculate_annualized_return(transactions, nav_history),
        "diversification_score": calculate_diversification_score(transactions, funds),
        "average_holding_period": calculate_average_holding_period(transactions),
        "expense_ratio_impact": calculate_expense_ratio_impact(transactions, funds, nav_history),
        "sharpe_ratio": calculate_sharpe_ratio(transactions, nav_history),
        "category_wise_investment": calculate_category_wise_investment(transactions, funds),
        "fund_allocation_pct": calculate_fund_allocation_pct(transactions, funds),
        "investor_profit_loss": calculate_investor_profit_loss(data),
    }

    return results
