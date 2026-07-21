"""
Models Module - OOP implementation with FundPortfolio class.
"""

import pandas as pd
import numpy as np
import logging

logger = logging.getLogger(__name__)


class FundPortfolio:
    """
    Represents a mutual fund portfolio for an investor.
    Implements OOP principles for portfolio management and analysis.
    """

    def __init__(self, investor_id: str, investor_name: str, transactions: pd.DataFrame,
                 funds: pd.DataFrame, nav_history: pd.DataFrame):
        self.investor_id = investor_id
        self.investor_name = investor_name
        self.transactions = transactions[transactions["InvestorID"] == investor_id].copy()
        self.funds = funds
        self.nav_history = nav_history
        self._portfolio_value = None

    @property
    def total_investment(self) -> float:
        """Calculate total investment amount (Buy transactions)."""
        buy_txns = self.transactions[self.transactions["TransactionType"] == "Buy"]
        return buy_txns["Amount"].sum()

    @property
    def total_redemption(self) -> float:
        """Calculate total redemption amount (Sell transactions)."""
        sell_txns = self.transactions[self.transactions["TransactionType"] == "Sell"]
        return sell_txns["Amount"].sum()

    @property
    def net_investment(self) -> float:
        """Calculate net investment (Buy - Sell)."""
        return self.total_investment - self.total_redemption

    @property
    def units_held(self) -> pd.Series:
        """Calculate units held per fund."""
        txns = self.transactions.copy()
        txns["SignedUnits"] = txns.apply(
            lambda r: r["Units"] if r["TransactionType"] == "Buy" else -r["Units"], axis=1
        )
        return txns.groupby("FundID")["SignedUnits"].sum()

    @property
    def portfolio_value(self) -> float:
        """Calculate current portfolio value using latest NAV."""
        units = self.units_held
        total_value = 0.0
        for fund_id, unit_count in units.items():
            if unit_count > 0:
                fund_nav = self.nav_history[self.nav_history["FundID"] == fund_id]
                if not fund_nav.empty:
                    latest_nav = fund_nav.sort_values("Date").iloc[-1]["NAV"]
                    total_value += unit_count * latest_nav
        self._portfolio_value = total_value
        return total_value

    @property
    def num_transactions(self) -> int:
        """Get total number of transactions."""
        return len(self.transactions)

    @property
    def num_funds(self) -> int:
        """Get number of unique funds invested in."""
        return self.transactions["FundID"].nunique()

    def get_fund_allocation(self) -> pd.DataFrame:
        """Get fund-wise allocation percentages."""
        units = self.units_held
        allocations = []
        total_value = 0.0

        for fund_id, unit_count in units.items():
            if unit_count > 0:
                fund_nav = self.nav_history[self.nav_history["FundID"] == fund_id]
                if not fund_nav.empty:
                    latest_nav = fund_nav.sort_values("Date").iloc[-1]["NAV"]
                    value = unit_count * latest_nav
                    total_value += value
                    fund_name = self.funds[self.funds["FundID"] == fund_id]["FundName"].values
                    allocations.append({
                        "FundID": fund_id,
                        "FundName": fund_name[0] if len(fund_name) > 0 else fund_id,
                        "Units": unit_count,
                        "CurrentNAV": latest_nav,
                        "Value": value,
                    })

        alloc_df = pd.DataFrame(allocations)
        if not alloc_df.empty and total_value > 0:
            alloc_df["Allocation%"] = (alloc_df["Value"] / total_value) * 100
        return alloc_df

    def get_profit_loss(self) -> float:
        """Calculate profit/loss for the portfolio."""
        return self.portfolio_value - self.net_investment

    def get_absolute_return(self) -> float:
        """Calculate absolute return percentage."""
        net_inv = self.net_investment
        if net_inv == 0:
            return 0.0
        return ((self.portfolio_value - net_inv) / net_inv) * 100

    def get_holding_period_days(self) -> float:
        """Calculate average holding period in days."""
        if self.transactions.empty:
            return 0.0
        first_date = self.transactions["TransactionDate"].min()
        last_date = self.transactions["TransactionDate"].max()
        return (last_date - first_date).days

    def __repr__(self):
        return (
            f"FundPortfolio(investor={self.investor_id}, "
            f"name={self.investor_name}, "
            f"funds={self.num_funds}, "
            f"transactions={self.num_transactions})"
        )

    def __str__(self):
        return (
            f"Portfolio of {self.investor_name} ({self.investor_id})\n"
            f"  Total Investment: ₹{self.total_investment:,.2f}\n"
            f"  Portfolio Value:  ₹{self.portfolio_value:,.2f}\n"
            f"  Profit/Loss:     ₹{self.get_profit_loss():,.2f}\n"
            f"  Funds Invested:  {self.num_funds}\n"
            f"  Transactions:    {self.num_transactions}"
        )
