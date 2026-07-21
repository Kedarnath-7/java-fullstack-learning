"""
Investor Analysis Module - Identify top investors and high-risk profiles.
"""

import pandas as pd
import logging

from src.config import HIGH_INVESTMENT_THRESHOLD, HIGH_INCOME_THRESHOLD, MIN_TRANSACTIONS_THRESHOLD
from src.models import FundPortfolio

logger = logging.getLogger(__name__)


def get_top_investors_by_portfolio_value(data: dict, top_n: int = 20) -> pd.DataFrame:
    """Identify Top 20 Investors based on Portfolio Value."""
    logger.info(f"Identifying Top {top_n} Investors by Portfolio Value...")

    transactions = data["transactions"]
    investors = data["investors"]
    funds = data["funds"]
    nav_history = data["nav_history"]

    investor_portfolios = []
    for _, investor in investors.iterrows():
        portfolio = FundPortfolio(
            investor["InvestorID"],
            investor["InvestorName"],
            transactions,
            funds,
            nav_history,
        )
        portfolio_value = portfolio.portfolio_value
        investor_portfolios.append({
            "InvestorID": investor["InvestorID"],
            "InvestorName": investor["InvestorName"],
            "PortfolioValue": portfolio_value,
            "TotalInvestment": portfolio.total_investment,
            "ProfitLoss": portfolio.get_profit_loss(),
            "NumFunds": portfolio.num_funds,
            "NumTransactions": portfolio.num_transactions,
        })

    result = pd.DataFrame(investor_portfolios)
    result = result.sort_values("PortfolioValue", ascending=False).head(top_n).reset_index(drop=True)
    result["Rank"] = result.index + 1

    logger.info(f"Top investor: {result.iloc[0]['InvestorName']} "
                f"(₹{result.iloc[0]['PortfolioValue']:,.2f})")
    return result


def identify_high_value_investors(data: dict) -> pd.DataFrame:
    """
    Identify investors meeting ALL criteria:
    - Investment > ₹10 Lakhs
    - High Risk Profile
    - More than 10 Transactions
    - Annual Income > ₹15 Lakhs
    """
    logger.info("Identifying High-Value Investors...")

    transactions = data["transactions"]
    investors = data["investors"]

    # Calculate total investment per investor
    buy_txns = transactions[transactions["TransactionType"] == "Buy"]
    investor_investment = buy_txns.groupby("InvestorID")["Amount"].sum().reset_index()
    investor_investment.columns = ["InvestorID", "TotalInvestment"]

    # Count transactions per investor
    txn_count = transactions.groupby("InvestorID").size().reset_index(name="TransactionCount")

    # Merge all data
    merged = investors.merge(investor_investment, on="InvestorID", how="left")
    merged = merged.merge(txn_count, on="InvestorID", how="left")
    merged["TotalInvestment"] = merged["TotalInvestment"].fillna(0)
    merged["TransactionCount"] = merged["TransactionCount"].fillna(0)

    # Apply filters
    high_value = merged[
        (merged["TotalInvestment"] > HIGH_INVESTMENT_THRESHOLD)
        & (merged["RiskProfile"] == "High")
        & (merged["TransactionCount"] > MIN_TRANSACTIONS_THRESHOLD)
        & (merged["AnnualIncome"] > HIGH_INCOME_THRESHOLD)
    ].copy()

    high_value = high_value.sort_values("TotalInvestment", ascending=False).reset_index(drop=True)

    logger.info(f"Found {len(high_value)} high-value investors matching all criteria.")
    if not high_value.empty:
        for _, inv in high_value.iterrows():
            logger.info(
                f"  - {inv['InvestorName']}: Investment=₹{inv['TotalInvestment']:,.0f}, "
                f"Income=₹{inv['AnnualIncome']:,.0f}, Transactions={int(inv['TransactionCount'])}"
            )

    return high_value


def run_investor_analysis(data: dict) -> dict:
    """Run complete investor analysis."""
    logger.info("=" * 60)
    logger.info("INVESTOR ANALYSIS")
    logger.info("=" * 60)

    results = {
        "top_20_investors": get_top_investors_by_portfolio_value(data, top_n=20),
        "high_value_investors": identify_high_value_investors(data),
    }

    return results
