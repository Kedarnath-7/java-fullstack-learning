"""
Fund Analysis Module - Analyze fund performance and characteristics.
"""

import pandas as pd
import logging

logger = logging.getLogger(__name__)


def find_best_performing_fund(funds: pd.DataFrame, nav_history: pd.DataFrame) -> dict:
    """Find the best performing fund based on NAV returns."""
    fund_returns = _calculate_fund_returns(nav_history)
    if fund_returns.empty:
        return {}

    best = fund_returns.loc[fund_returns["Return%"].idxmax()]
    fund_info = funds[funds["FundID"] == best["FundID"]].iloc[0]

    result = {
        "FundID": best["FundID"],
        "FundName": fund_info["FundName"],
        "Category": fund_info["Category"],
        "Return%": best["Return%"],
    }
    logger.info(f"Best Performing Fund: {result['FundName']} ({result['Return%']:.2f}%)")
    return result


def find_worst_performing_fund(funds: pd.DataFrame, nav_history: pd.DataFrame) -> dict:
    """Find the worst performing fund based on NAV returns."""
    fund_returns = _calculate_fund_returns(nav_history)
    if fund_returns.empty:
        return {}

    worst = fund_returns.loc[fund_returns["Return%"].idxmin()]
    fund_info = funds[funds["FundID"] == worst["FundID"]].iloc[0]

    result = {
        "FundID": worst["FundID"],
        "FundName": fund_info["FundName"],
        "Category": fund_info["Category"],
        "Return%": worst["Return%"],
    }
    logger.info(f"Worst Performing Fund: {result['FundName']} ({result['Return%']:.2f}%)")
    return result


def find_highest_expense_ratio(funds: pd.DataFrame) -> dict:
    """Find fund with highest expense ratio."""
    idx = funds["ExpenseRatio"].idxmax()
    fund = funds.loc[idx]
    result = {
        "FundID": fund["FundID"],
        "FundName": fund["FundName"],
        "ExpenseRatio": fund["ExpenseRatio"],
    }
    logger.info(f"Highest Expense Ratio: {result['FundName']} ({result['ExpenseRatio']:.2f}%)")
    return result


def find_highest_aum(transactions: pd.DataFrame, funds: pd.DataFrame,
                     nav_history: pd.DataFrame) -> dict:
    """Find fund with highest AUM (Assets Under Management)."""
    # Calculate net units per fund
    txns = transactions.copy()
    txns["SignedUnits"] = txns.apply(
        lambda r: r["Units"] if r["TransactionType"] == "Buy" else -r["Units"], axis=1
    )
    fund_units = txns.groupby("FundID")["SignedUnits"].sum().reset_index()
    fund_units.columns = ["FundID", "TotalUnits"]

    # Get latest NAV per fund
    latest_nav = nav_history.sort_values("Date").groupby("FundID")["NAV"].last().reset_index()
    latest_nav.columns = ["FundID", "LatestNAV"]

    # Calculate AUM = Total Units × Latest NAV
    aum = fund_units.merge(latest_nav, on="FundID", how="inner")
    aum["AUM"] = aum["TotalUnits"] * aum["LatestNAV"]

    if aum.empty:
        return {}

    highest = aum.loc[aum["AUM"].idxmax()]
    fund_info = funds[funds["FundID"] == highest["FundID"]]
    fund_name = fund_info["FundName"].values[0] if not fund_info.empty else highest["FundID"]

    result = {
        "FundID": highest["FundID"],
        "FundName": fund_name,
        "AUM": highest["AUM"],
    }
    logger.info(f"Highest AUM Fund: {result['FundName']} (₹{result['AUM']:,.2f})")
    return result


def find_most_popular_fund(transactions: pd.DataFrame, funds: pd.DataFrame) -> dict:
    """Find the most popular fund based on number of investors."""
    fund_investors = transactions.groupby("FundID")["InvestorID"].nunique().reset_index()
    fund_investors.columns = ["FundID", "NumInvestors"]

    if fund_investors.empty:
        return {}

    most_popular = fund_investors.loc[fund_investors["NumInvestors"].idxmax()]
    fund_info = funds[funds["FundID"] == most_popular["FundID"]]
    fund_name = fund_info["FundName"].values[0] if not fund_info.empty else most_popular["FundID"]

    result = {
        "FundID": most_popular["FundID"],
        "FundName": fund_name,
        "NumInvestors": most_popular["NumInvestors"],
    }
    logger.info(f"Most Popular Fund: {result['FundName']} ({result['NumInvestors']} investors)")
    return result


def _calculate_fund_returns(nav_history: pd.DataFrame) -> pd.DataFrame:
    """Helper to calculate returns for all funds."""
    returns = []
    for fund_id in nav_history["FundID"].unique():
        fund_nav = nav_history[nav_history["FundID"] == fund_id].sort_values("Date")
        if len(fund_nav) >= 2:
            first_nav = fund_nav.iloc[0]["NAV"]
            last_nav = fund_nav.iloc[-1]["NAV"]
            if first_nav > 0:
                ret = ((last_nav - first_nav) / first_nav) * 100
                returns.append({"FundID": fund_id, "Return%": ret})

    return pd.DataFrame(returns)


def rank_funds(funds: pd.DataFrame, nav_history: pd.DataFrame,
               transactions: pd.DataFrame) -> pd.DataFrame:
    """Rank all funds by performance."""
    fund_returns = _calculate_fund_returns(nav_history)
    if fund_returns.empty:
        return pd.DataFrame()

    ranked = fund_returns.merge(funds[["FundID", "FundName", "Category", "ExpenseRatio"]],
                                 on="FundID", how="left")
    ranked = ranked.sort_values("Return%", ascending=False).reset_index(drop=True)
    ranked["Rank"] = ranked.index + 1

    logger.info(f"Fund ranking complete. Top fund: {ranked.iloc[0]['FundName']}")
    return ranked


def run_fund_analysis(data: dict) -> dict:
    """Run complete fund analysis."""
    logger.info("=" * 60)
    logger.info("FUND ANALYSIS")
    logger.info("=" * 60)

    results = {
        "best_performing": find_best_performing_fund(data["funds"], data["nav_history"]),
        "worst_performing": find_worst_performing_fund(data["funds"], data["nav_history"]),
        "highest_expense_ratio": find_highest_expense_ratio(data["funds"]),
        "highest_aum": find_highest_aum(
            data["transactions"], data["funds"], data["nav_history"]
        ),
        "most_popular": find_most_popular_fund(data["transactions"], data["funds"]),
        "fund_rankings": rank_funds(data["funds"], data["nav_history"], data["transactions"]),
    }

    return results
