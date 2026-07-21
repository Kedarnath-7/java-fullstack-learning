"""
Case Study 1: Credit Risk & Loan Portfolio Analysis
Difficulty: Hard

A bank has loan records. The Risk Management team wants to identify high-risk
customers and calculate portfolio risk metrics.
"""

import os
import json
import numpy as np
import pandas as pd

# ============================================================
# OOP: Loan Class
# ============================================================

class Loan:
    """Represents a single loan record with associated customer and credit data."""

    def __init__(self, loan_id, customer_id, loan_amount, interest_rate,
                 tenure, emi, paid_emis, default_flag):
        self.loan_id = loan_id
        self.customer_id = customer_id
        self.loan_amount = loan_amount
        self.interest_rate = interest_rate
        self.tenure = tenure
        self.emi = emi
        self.paid_emis = paid_emis
        self.default_flag = default_flag

    @property
    def outstanding_amount(self):
        """Calculate remaining outstanding amount."""
        return self.emi * (self.tenure - self.paid_emis)

    @property
    def loan_utilization(self):
        """Percentage of tenure completed (EMIs paid / Total tenure)."""
        if self.tenure == 0:
            return 0.0
        return (self.paid_emis / self.tenure) * 100

    def debt_to_income_ratio(self, salary):
        """Calculate Debt-to-Income ratio (annual EMI / annual salary)."""
        if salary == 0 or pd.isna(salary):
            return np.nan
        annual_emi = self.emi * 12
        return annual_emi / salary

    def __repr__(self):
        return (f"Loan(id={self.loan_id}, customer={self.customer_id}, "
                f"amount={self.loan_amount}, default={self.default_flag})")


# ============================================================
# Functions: Read CSV Files with Exception Handling
# ============================================================

def read_csv_safe(filepath):
    """Read a CSV file with exception handling for corrupted files."""
    try:
        if not os.path.exists(filepath):
            raise FileNotFoundError(f"File not found: {filepath}")
        df = pd.read_csv(filepath)
        print(f"  [OK] Loaded '{os.path.basename(filepath)}' — {len(df)} records")
        return df
    except FileNotFoundError as e:
        print(f"  [ERROR] {e}")
        return pd.DataFrame()
    except pd.errors.EmptyDataError:
        print(f"  [ERROR] File is empty: {filepath}")
        return pd.DataFrame()
    except pd.errors.ParserError:
        print(f"  [ERROR] File is corrupted/unparseable: {filepath}")
        return pd.DataFrame()
    except Exception as e:
        print(f"  [ERROR] Unexpected error reading {filepath}: {e}")
        return pd.DataFrame()


def load_all_data(base_path):
    """Load all three CSV files from the given directory."""
    print("\n--- Loading Input Files ---")
    customers = read_csv_safe(os.path.join(base_path, "customers.csv"))
    loans = read_csv_safe(os.path.join(base_path, "loans.csv"))
    credit_scores = read_csv_safe(os.path.join(base_path, "credit_scores.csv"))
    return customers, loans, credit_scores


# ============================================================
# NumPy Calculations
# ============================================================

def numpy_calculations(merged_df):
    """Perform NumPy-based statistical calculations."""
    print("\n--- NumPy Statistical Calculations ---")

    loan_amounts = merged_df["LoanAmount"].dropna().values
    salaries = merged_df["Salary"].dropna().values
    interest_rates = merged_df["InterestRate"].dropna().values

    mean_loan = np.mean(loan_amounts)
    median_salary = np.median(salaries)
    percentile_25_ir = np.percentile(interest_rates, 25)
    percentile_75_ir = np.percentile(interest_rates, 75)

    # Correlation between Salary and Loan Amount
    valid_mask = merged_df[["Salary", "LoanAmount"]].dropna()
    correlation = np.corrcoef(valid_mask["Salary"].values,
                              valid_mask["LoanAmount"].values)[0, 1]

    std_dev_loan = np.std(loan_amounts, ddof=1)

    print(f"  Mean Loan Amount       : ₹{mean_loan:,.2f}")
    print(f"  Median Salary          : ₹{median_salary:,.2f}")
    print(f"  25th Percentile IR     : {percentile_25_ir:.2f}%")
    print(f"  75th Percentile IR     : {percentile_75_ir:.2f}%")
    print(f"  Correlation (Salary↔Loan): {correlation:.4f}")
    print(f"  Std Dev (Loan Amount)  : ₹{std_dev_loan:,.2f}")

    return {
        "mean_loan_amount": round(mean_loan, 2),
        "median_salary": round(median_salary, 2),
        "percentile_25_interest_rate": round(percentile_25_ir, 2),
        "percentile_75_interest_rate": round(percentile_75_ir, 2),
        "correlation_salary_loan": round(correlation, 4),
        "std_dev_loan_amount": round(std_dev_loan, 2),
    }


# ============================================================
# Pandas: Merge, Find Risky Customers, Handle Missing Data, Outliers
# ============================================================

def merge_data(customers, loans, credit_scores):
    """Merge customers, loans, and credit_scores on CustomerID."""
    print("\n--- Merging DataFrames ---")
    merged = customers.merge(loans, on="CustomerID", how="outer")
    merged = merged.merge(credit_scores, on="CustomerID", how="outer")
    print(f"  Merged DataFrame: {merged.shape[0]} rows × {merged.shape[1]} columns")
    return merged


def handle_missing_data(df):
    """
    Replace missing data:
      - Salary → Median
      - CreditScore → Mean
      - InterestRate → Previous Value (forward fill)
    """
    print("\n--- Handling Missing Data ---")
    missing_before = df.isnull().sum()

    if df["Salary"].isnull().any():
        median_salary = df["Salary"].median()
        df["Salary"] = df["Salary"].fillna(median_salary)
        print(f"  Salary: filled {missing_before['Salary']} missing with median ({median_salary:.2f})")

    if df["CreditScore"].isnull().any():
        mean_credit = df["CreditScore"].mean()
        df["CreditScore"] = df["CreditScore"].fillna(mean_credit)
        print(f"  CreditScore: filled {missing_before['CreditScore']} missing with mean ({mean_credit:.2f})")

    if df["InterestRate"].isnull().any():
        df["InterestRate"] = df["InterestRate"].fillna(method="ffill")
        # If first values are still NaN, back-fill
        df["InterestRate"] = df["InterestRate"].fillna(method="bfill")
        print(f"  InterestRate: filled {missing_before['InterestRate']} missing with previous value (ffill)")

    remaining = df.isnull().sum().sum()
    print(f"  Remaining missing values: {remaining}")
    return df


def remove_outliers(df):
    """Remove rows where LoanAmount > 99th percentile."""
    print("\n--- Removing Outliers ---")
    percentile_99 = np.percentile(df["LoanAmount"].dropna().values, 99)
    before = len(df)
    df = df[df["LoanAmount"] <= percentile_99].copy()
    after = len(df)
    print(f"  99th percentile LoanAmount: ₹{percentile_99:,.2f}")
    print(f"  Removed {before - after} outlier(s) (rows: {before} → {after})")
    return df


def find_high_risk_customers(df):
    """
    Find Top 20 risky customers having:
      - CreditScore < 650
      - Salary < 60000
      - LoanAmount > 10 Lakhs (1,000,000)
      - DefaultFlag = 1
    """
    print("\n--- Identifying High-Risk Customers ---")
    risky = df[
        (df["CreditScore"] < 650) &
        (df["Salary"] < 60000) &
        (df["LoanAmount"] > 1000000) &
        (df["DefaultFlag"] == 1)
    ].copy()

    # If fewer than 20 exact matches, relax: take top 20 by risk score
    if len(risky) < 20:
        # Create a risk score for ranking
        df_temp = df.copy()
        df_temp["RiskScore"] = (
            (700 - df_temp["CreditScore"].clip(upper=700)) / 700 * 25 +
            (80000 - df_temp["Salary"].clip(upper=80000)) / 80000 * 25 +
            df_temp["LoanAmount"] / df_temp["LoanAmount"].max() * 25 +
            df_temp["DefaultFlag"] * 25
        )
        top_20 = df_temp.nlargest(20, "RiskScore")
        print(f"  Strict criteria matches: {len(risky)}")
        print(f"  Returning Top 20 by risk score")
        return top_20.drop(columns=["RiskScore"])
    else:
        top_20 = risky.head(20)
        print(f"  Found {len(risky)} high-risk customers, returning top 20")
        return top_20


# ============================================================
# Finance Metrics
# ============================================================

def calculate_finance_metrics(df):
    """
    Calculate:
      - Debt-to-Income Ratio
      - Loan Utilization
      - Default %
      - NPA %
      - Average EMI
      - Expected Loss
    """
    print("\n--- Calculating Finance Metrics ---")

    # Debt-to-Income Ratio (Annual EMI / Annual Salary)
    df["DebtToIncomeRatio"] = (df["EMI"] * 12) / df["Salary"]

    # Loan Utilization (PaidEMIs / Tenure * 100)
    df["LoanUtilization"] = (df["PaidEMIs"] / df["Tenure"]) * 100

    # Default %
    total_loans = len(df)
    default_count = df["DefaultFlag"].sum()
    default_pct = (default_count / total_loans) * 100

    # NPA % (Non-Performing Assets: default loans amount / total loan amount)
    total_loan_amount = df["LoanAmount"].sum()
    npa_amount = df[df["DefaultFlag"] == 1]["LoanAmount"].sum()
    npa_pct = (npa_amount / total_loan_amount) * 100 if total_loan_amount > 0 else 0

    # Average EMI
    avg_emi = df["EMI"].mean()

    # Expected Loss = Probability of Default × Outstanding Amount
    prob_default = default_count / total_loans if total_loans > 0 else 0
    df["OutstandingAmount"] = df["EMI"] * (df["Tenure"] - df["PaidEMIs"])
    expected_loss = prob_default * df["OutstandingAmount"].sum()

    print(f"  Debt-to-Income Ratio (avg): {df['DebtToIncomeRatio'].mean():.4f}")
    print(f"  Loan Utilization (avg)    : {df['LoanUtilization'].mean():.2f}%")
    print(f"  Default %                 : {default_pct:.2f}%")
    print(f"  NPA %                     : {npa_pct:.2f}%")
    print(f"  Average EMI               : ₹{avg_emi:,.2f}")
    print(f"  Expected Loss             : ₹{expected_loss:,.2f}")

    metrics = {
        "avg_debt_to_income_ratio": round(df["DebtToIncomeRatio"].mean(), 4),
        "avg_loan_utilization_pct": round(df["LoanUtilization"].mean(), 2),
        "default_pct": round(default_pct, 2),
        "npa_pct": round(npa_pct, 2),
        "average_emi": round(avg_emi, 2),
        "expected_loss": round(expected_loss, 2),
        "total_loans": total_loans,
        "default_count": int(default_count),
    }

    return df, metrics


# ============================================================
# Automation: Generate Reports
# ============================================================

def generate_reports(df, high_risk_df, stats, metrics, output_path):
    """
    Generate:
      - risk_report.xlsx
      - high_risk_customers.csv
      - summary.json
    """
    print("\n--- Generating Reports ---")

    # 1. risk_report.xlsx — full analysis with multiple sheets
    xlsx_path = os.path.join(output_path, "risk_report.xlsx")
    with pd.ExcelWriter(xlsx_path, engine="openpyxl") as writer:
        df.to_excel(writer, sheet_name="Full_Portfolio", index=False)
        high_risk_df.to_excel(writer, sheet_name="High_Risk_Customers", index=False)

        # Metrics summary as a DataFrame
        metrics_df = pd.DataFrame([metrics])
        metrics_df.to_excel(writer, sheet_name="Metrics_Summary", index=False)

        # Stats summary
        stats_df = pd.DataFrame([stats])
        stats_df.to_excel(writer, sheet_name="Statistical_Summary", index=False)

    print(f"  [OK] risk_report.xlsx")

    # 2. high_risk_customers.csv
    csv_path = os.path.join(output_path, "high_risk_customers.csv")
    high_risk_df.to_csv(csv_path, index=False)
    print(f"  [OK] high_risk_customers.csv")

    # 3. summary.json
    summary = {
        "report_title": "Credit Risk & Loan Portfolio Analysis",
        "total_customers": len(df),
        "high_risk_customers_count": len(high_risk_df),
        "statistical_summary": stats,
        "finance_metrics": metrics,
    }
    json_path = os.path.join(output_path, "summary.json")
    with open(json_path, "w") as f:
        json.dump(summary, f, indent=4)
    print(f"  [OK] summary.json")

    return xlsx_path, csv_path, json_path


# ============================================================
# Main Execution
# ============================================================

def main():
    print("=" * 60)
    print("   CREDIT RISK & LOAN PORTFOLIO ANALYSIS")
    print("=" * 60)

    base_path = os.path.dirname(os.path.abspath(__file__))

    # Step 1: Load data
    customers, loans, credit_scores = load_all_data(base_path)

    if customers.empty or loans.empty or credit_scores.empty:
        print("\n[FATAL] One or more input files could not be loaded. Exiting.")
        return

    # Step 2: Merge data
    merged = merge_data(customers, loans, credit_scores)

    # Step 3: Handle missing data
    merged = handle_missing_data(merged)

    # Step 4: Create Loan objects (OOP demonstration)
    print("\n--- Creating Loan Objects (OOP) ---")
    loan_objects = []
    for _, row in merged.iterrows():
        loan_obj = Loan(
            loan_id=row.get("LoanID"),
            customer_id=row["CustomerID"],
            loan_amount=row.get("LoanAmount", 0),
            interest_rate=row.get("InterestRate", 0),
            tenure=row.get("Tenure", 0),
            emi=row.get("EMI", 0),
            paid_emis=row.get("PaidEMIs", 0),
            default_flag=row.get("DefaultFlag", 0),
        )
        loan_objects.append(loan_obj)
    print(f"  Created {len(loan_objects)} Loan objects")
    print(f"  Sample: {loan_objects[0]}")

    # Step 5: NumPy calculations
    stats = numpy_calculations(merged)

    # Step 6: Remove outliers
    merged = remove_outliers(merged)

    # Step 7: Find high-risk customers
    high_risk_df = find_high_risk_customers(merged)

    # Step 8: Calculate finance metrics
    merged, metrics = calculate_finance_metrics(merged)

    # Step 9: Generate reports
    generate_reports(merged, high_risk_df, stats, metrics, base_path)

    print("\n" + "=" * 60)
    print("   ANALYSIS COMPLETE")
    print("=" * 60)


if __name__ == "__main__":
    main()
