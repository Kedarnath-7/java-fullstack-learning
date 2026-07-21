"""
Case Study 2: Mutual Fund Performance Analytics
ABC Asset Management Company (AMC)
"""

import pandas as pd
import numpy as np

# ============================================================
# Part 1 - Read Data
# ============================================================
print("=" * 60)
print("PART 1 - Read Data")
print("=" * 60)

funds = pd.read_csv("funds.csv")
investors = pd.read_csv("investors.csv")
transactions = pd.read_csv("transactions.csv")
nav_history = pd.read_csv("nav_history.csv")

print(f"funds.csv: {funds.shape}")
print(f"investors.csv: {investors.shape}")
print(f"transactions.csv: {transactions.shape}")
print(f"nav_history.csv: {nav_history.shape}")

# ============================================================
# Part 2 - Data Cleaning
# ============================================================
print("\n" + "=" * 60)
print("PART 2 - Data Cleaning")
print("=" * 60)

# Remove duplicate rows
funds.drop_duplicates(inplace=True)
investors.drop_duplicates(inplace=True)
transactions.drop_duplicates(inplace=True)
nav_history.drop_duplicates(inplace=True)
print("Duplicates removed.")

# Check missing values
print("\nMissing values:")
print(f"  funds: {funds.isnull().sum().sum()}")
print(f"  investors: {investors.isnull().sum().sum()}")
print(f"  transactions: {transactions.isnull().sum().sum()}")
print(f"  nav_history: {nav_history.isnull().sum().sum()}")

# Fill missing NAV using Forward Fill
nav_history["NAV"] = nav_history["NAV"].ffill()

# Replace missing InvestorType with "Retail"
investors["InvestorType"] = investors["InvestorType"].fillna("Retail")

# Remove rows having negative NAV
nav_history = nav_history[nav_history["NAV"] >= 0]
transactions = transactions[transactions["PurchaseNAV"] >= 0]

# Convert Date columns into datetime format
transactions["PurchaseDate"] = pd.to_datetime(transactions["PurchaseDate"], errors="coerce")
nav_history["Date"] = pd.to_datetime(nav_history["Date"], errors="coerce")

print("Data cleaning completed.")

# ============================================================
# Part 3 - Merge Data
# ============================================================
print("\n" + "=" * 60)
print("PART 3 - Merge Data")
print("=" * 60)

# Get latest NAV for each fund
latest_nav = nav_history.sort_values("Date").groupby("FundID").last().reset_index()
latest_nav = latest_nav.rename(columns={"NAV": "LatestNAV"})
latest_nav = latest_nav[["FundID", "LatestNAV"]]

# Merge all datasets
merged = transactions.merge(investors, on="InvestorID", how="left")
merged = merged.merge(funds, on="FundID", how="left")
merged = merged.merge(latest_nav, on="FundID", how="left")

# Select required columns
required_cols = ["InvestorName", "FundName", "Category", "AMC", "State",
                 "UnitsPurchased", "PurchaseNAV", "LatestNAV",
                 "InvestorID", "FundID", "PurchaseDate", "InvestorType"]
merged = merged[[c for c in required_cols if c in merged.columns]]

print(f"Merged DataFrame shape: {merged.shape}")
print(merged.head())

# ============================================================
# Part 4 - Create New Columns
# ============================================================
print("\n" + "=" * 60)
print("PART 4 - Create New Columns")
print("=" * 60)

merged["InvestmentAmount"] = merged["UnitsPurchased"] * merged["PurchaseNAV"]
merged["CurrentValue"] = merged["UnitsPurchased"] * merged["LatestNAV"]
merged["Profit"] = merged["CurrentValue"] - merged["InvestmentAmount"]
merged["ROI%"] = ((merged["CurrentValue"] - merged["InvestmentAmount"]) / merged["InvestmentAmount"]) * 100

print(merged[["InvestorName", "FundName", "InvestmentAmount", "CurrentValue", "Profit", "ROI%"]].head(10))

# ============================================================
# Part 5 - NumPy Tasks
# ============================================================
print("\n" + "=" * 60)
print("PART 5 - NumPy Tasks")
print("=" * 60)

nav_array = nav_history["NAV"].dropna().values

avg_nav = np.mean(nav_array)
max_nav = np.max(nav_array)
min_nav = np.min(nav_array)
var_nav = np.var(nav_array)
std_nav = np.std(nav_array)

print(f"Average NAV: {avg_nav:.4f}")
print(f"Maximum NAV: {max_nav:.4f}")
print(f"Minimum NAV: {min_nav:.4f}")
print(f"Variance of NAV: {var_nav:.4f}")
print(f"Standard Deviation of NAV: {std_nav:.4f}")

# Rolling Average (window=5)
rolling_avg = pd.Series(nav_array).rolling(window=5).mean().dropna().values
print(f"\nRolling Average (window=5) - first 10 values:")
print(rolling_avg[:10])

# ============================================================
# Part 6 - Pandas Analysis
# ============================================================
print("\n" + "=" * 60)
print("PART 6 - Pandas Analysis")
print("=" * 60)

# Top 5 investors based on investment amount
top5_investors = merged.groupby("InvestorName")["InvestmentAmount"].sum().nlargest(5)
print("\nTop 5 Investors by Investment Amount:")
print(top5_investors)

# Top 5 profitable funds
top5_funds = merged.groupby("FundName")["Profit"].sum().nlargest(5)
print("\nTop 5 Profitable Funds:")
print(top5_funds)

# Worst performing fund
worst_fund = merged.groupby("FundName")["ROI%"].mean().idxmin()
print(f"\nWorst Performing Fund: {worst_fund}")

# Highest NAV fund
highest_nav_fund = merged.loc[merged["LatestNAV"].idxmax(), "FundName"]
print(f"Highest NAV Fund: {highest_nav_fund}")

# Lowest NAV fund
lowest_nav_fund = merged.loc[merged["LatestNAV"].idxmin(), "FundName"]
print(f"Lowest NAV Fund: {lowest_nav_fund}")

# ============================================================
# Part 7 - GroupBy
# ============================================================
print("\n" + "=" * 60)
print("PART 7 - GroupBy")
print("=" * 60)

# Group by Category
print("\n--- Group by Category ---")
category_group = merged.groupby("Category").agg(
    AverageROI=("ROI%", "mean"),
    AverageNAV=("LatestNAV", "mean"),
    TotalInvestment=("InvestmentAmount", "sum")
)
print(category_group)

# Group by AMC
print("\n--- Group by AMC ---")
amc_group = merged.groupby("AMC").agg(
    NumberOfFunds=("FundName", "nunique"),
    AverageNAV=("LatestNAV", "mean"),
    TotalInvestment=("InvestmentAmount", "sum")
)
print(amc_group)

# Group by State
print("\n--- Group by State ---")
state_group = merged.groupby("State").agg(
    NumberOfInvestors=("InvestorName", "nunique"),
    TotalInvestment=("InvestmentAmount", "sum"),
    AverageROI=("ROI%", "mean")
)
print(state_group)

# Group by InvestorType
print("\n--- Group by InvestorType ---")
investor_type_group = merged.groupby("InvestorType").agg(
    TotalInvestment=("InvestmentAmount", "sum"),
    AverageProfit=("Profit", "mean")
)
print(investor_type_group)

# ============================================================
# Part 8 - Detect Issues
# ============================================================
print("\n" + "=" * 60)
print("PART 8 - Detect Issues")
print("=" * 60)

# Duplicate NAV records
duplicate_nav = nav_history[nav_history.duplicated(subset=["FundID", "Date"], keep=False)]
print(f"Duplicate NAV records: {len(duplicate_nav)}")

# Negative NAV
negative_nav = nav_history[nav_history["NAV"] < 0]
print(f"Negative NAV records: {len(negative_nav)}")

# Future dates
today = pd.Timestamp.today()
future_nav = nav_history[nav_history["Date"] > today]
future_transactions = transactions[transactions["PurchaseDate"] > today] if "PurchaseDate" in transactions.columns else pd.DataFrame()
print(f"Future dates in NAV history: {len(future_nav)}")
print(f"Future dates in Transactions: {len(future_transactions)}")

# Missing Fund IDs
missing_fund_ids = transactions[~transactions["FundID"].isin(funds["FundID"])]
print(f"Missing Fund IDs in transactions: {len(missing_fund_ids)}")

# Missing Investor IDs
missing_investor_ids = transactions[~transactions["InvestorID"].isin(investors["InvestorID"])]
print(f"Missing Investor IDs in transactions: {len(missing_investor_ids)}")

# Invalid PurchaseNAV (< 0)
invalid_purchase_nav = transactions[transactions["PurchaseNAV"] < 0]
print(f"Invalid PurchaseNAV (< 0): {len(invalid_purchase_nav)}")

# ============================================================
# Part 9 - Finance Metrics
# ============================================================
print("\n" + "=" * 60)
print("PART 9 - Finance Metrics")
print("=" * 60)

# ROI
merged["ROI"] = ((merged["CurrentValue"] - merged["InvestmentAmount"]) / merged["InvestmentAmount"]) * 100

# Absolute Return
merged["AbsoluteReturn"] = merged["CurrentValue"] - merged["InvestmentAmount"]

# Annual Return (assuming holding period is 1 year)
merged["AnnualReturn"] = merged["ROI"]  # Since holding period = 1 year, annual return = ROI

# Volatility using np.std(NAV)
volatility = np.std(nav_history["NAV"].dropna().values)
print(f"Volatility (std of NAV): {volatility:.4f}")

# Sharpe Ratio
risk_free_rate = 6  # 6%
avg_return = merged["ROI"].mean()
sharpe_ratio = (avg_return - risk_free_rate) / volatility
print(f"Average ROI: {avg_return:.4f}%")
print(f"Sharpe Ratio: {sharpe_ratio:.4f}")

print("\nFinance Metrics per Fund:")
fund_metrics = merged.groupby("FundName").agg(
    ROI=("ROI", "mean"),
    AbsoluteReturn=("AbsoluteReturn", "sum"),
    AnnualReturn=("AnnualReturn", "mean")
).round(4)
print(fund_metrics)

# ============================================================
# Part 10 - Export Reports
# ============================================================
print("\n" + "=" * 60)
print("PART 10 - Export Reports")
print("=" * 60)

# TopFunds.xlsx
top_funds_report = merged.groupby("FundName").agg(
    TotalProfit=("Profit", "sum"),
    AverageROI=("ROI%", "mean"),
    LatestNAV=("LatestNAV", "first")
).sort_values("AverageROI", ascending=False)
top_funds_report.to_excel("TopFunds.xlsx")
print("TopFunds.xlsx generated.")

# InvestorSummary.xlsx
investor_summary = merged.groupby("InvestorName").agg(
    TotalInvestment=("InvestmentAmount", "sum"),
    TotalCurrentValue=("CurrentValue", "sum"),
    TotalProfit=("Profit", "sum"),
    AverageROI=("ROI%", "mean")
).sort_values("TotalInvestment", ascending=False)
investor_summary.to_excel("InvestorSummary.xlsx")
print("InvestorSummary.xlsx generated.")

# CategorySummary.csv
category_summary = merged.groupby("Category").agg(
    TotalInvestment=("InvestmentAmount", "sum"),
    TotalProfit=("Profit", "sum"),
    AverageROI=("ROI%", "mean"),
    AverageNAV=("LatestNAV", "mean")
)
category_summary.to_csv("CategorySummary.csv")
print("CategorySummary.csv generated.")

# ============================================================
# Expected Outputs Summary
# ============================================================
print("\n" + "=" * 60)
print("EXPECTED OUTPUTS SUMMARY")
print("=" * 60)

# Top Performing Funds
print("\n--- Top Performing Funds ---")
print(f"Highest ROI: {merged.loc[merged['ROI%'].idxmax(), 'FundName']} ({merged['ROI%'].max():.2f}%)")
print(f"Highest Profit: {merged.loc[merged['Profit'].idxmax(), 'FundName']} (₹{merged['Profit'].max():.2f})")
print(f"Highest NAV: {highest_nav_fund} ({merged['LatestNAV'].max():.2f})")

# Worst Performing Fund
print(f"\n--- Worst Performing Fund ---")
print(f"Lowest ROI: {worst_fund} ({merged.groupby('FundName')['ROI%'].mean().min():.2f}%)")

# State-wise Investment
print("\n--- State-wise Investment ---")
print(merged.groupby("State")["InvestmentAmount"].sum().sort_values(ascending=False))

# AMC-wise Investment
print("\n--- AMC-wise Investment ---")
print(merged.groupby("AMC")["InvestmentAmount"].sum().sort_values(ascending=False))

# Category-wise ROI
print("\n--- Category-wise ROI ---")
print(merged.groupby("Category")["ROI%"].mean().sort_values(ascending=False))

print("\n" + "=" * 60)
print("ANALYSIS COMPLETE")
print("=" * 60)
