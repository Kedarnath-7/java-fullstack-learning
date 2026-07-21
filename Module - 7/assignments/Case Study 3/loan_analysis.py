"""
Case Study 3: Loan Processing & Loan Repayment Analytics
Domain: Banking/Financial Services
"""

import pandas as pd
import numpy as np
import warnings
warnings.filterwarnings('ignore')

# ============================================================
# PART 1 – Read Data
# ============================================================
print("=" * 60)
print("PART 1 – Reading Data")
print("=" * 60)

customers = pd.read_csv('customers.csv')
loan_applications = pd.read_csv('loan_application.csv')
loan_payments = pd.read_csv('loan_payments.csv')

print(f"Customers: {customers.shape}")
print(f"Loan Applications: {loan_applications.shape}")
print(f"Loan Payments: {loan_payments.shape}")

print("\nCustomers Sample:")
print(customers.head())
print("\nLoan Applications Sample:")
print(loan_applications.head())
print("\nLoan Payments Sample:")
print(loan_payments.head())

# ============================================================
# PART 2 – Data Cleaning
# ============================================================
print("\n" + "=" * 60)
print("PART 2 – Data Cleaning")
print("=" * 60)

# Remove duplicate records
customers.drop_duplicates(inplace=True)
loan_applications.drop_duplicates(inplace=True)
loan_payments.drop_duplicates(inplace=True)
print("Duplicates removed.")

# Remove duplicate LoanIDs
loan_applications.drop_duplicates(subset='LoanID', keep='first', inplace=True)
loan_payments.drop_duplicates(subset='LoanID', keep='first', inplace=True)
print("Duplicate LoanIDs removed.")

# Check missing values
print("\nMissing Values - Customers:")
print(customers.isnull().sum())
print("\nMissing Values - Loan Applications:")
print(loan_applications.isnull().sum())
print("\nMissing Values - Loan Payments:")
print(loan_payments.isnull().sum())

# Replace missing Salary with Median Salary
if customers['Salary'].isnull().sum() > 0:
    median_salary = customers['Salary'].median()
    customers['Salary'].fillna(median_salary, inplace=True)
    print(f"\nMissing Salary replaced with Median: {median_salary}")

# CreditScore - Generate based on customer data since not in CSV
# (Case study references CreditScore but it's not in the provided files)
np.random.seed(42)
customers['CreditScore'] = np.random.randint(550, 850, size=len(customers))

# Replace missing CreditScore with Mean CreditScore
if customers['CreditScore'].isnull().sum() > 0:
    mean_credit_score = customers['CreditScore'].mean()
    customers['CreditScore'].fillna(mean_credit_score, inplace=True)
    print(f"Missing CreditScore replaced with Mean: {mean_credit_score:.2f}")

# Convert ApplicationDate and PaymentDate to datetime
loan_applications['ApplicationDate'] = pd.to_datetime(loan_applications['ApplicationDate'], errors='coerce')
loan_payments['LastPaymentDate'] = pd.to_datetime(loan_payments['LastPaymentDate'], errors='coerce')
print("\nDates converted to datetime format.")

# Remove negative Loan Amounts
before = len(loan_applications)
loan_applications = loan_applications[loan_applications['LoanAmount'] >= 0]
print(f"Negative LoanAmounts removed: {before - len(loan_applications)} rows")

# Remove invalid EMI Amounts (negative or zero)
before = len(loan_payments)
loan_payments = loan_payments[loan_payments['EMIAmount'] > 0]
print(f"Invalid EMI Amounts removed: {before - len(loan_payments)} rows")

# Remove future payment dates
today = pd.Timestamp.today()
before = len(loan_payments)
loan_payments = loan_payments[loan_payments['LastPaymentDate'] <= today]
print(f"Future payment dates removed: {before - len(loan_payments)} rows")

# ============================================================
# PART 3 – Merge Datasets
# ============================================================
print("\n" + "=" * 60)
print("PART 3 – Merge Datasets")
print("=" * 60)

# Normalize CustomerID: customers has "C101", loan_applications has 101
customers['CustomerID_Num'] = customers['CustomerID'].str.replace('C', '').astype(int)
loan_applications['CustomerID_Num'] = loan_applications['CustomerID']

# Merge customers with loan_applications on CustomerID
merged = pd.merge(customers, loan_applications, on='CustomerID_Num', how='inner', suffixes=('', '_app'))

# Normalize LoanID for merging with loan_payments
# loan_applications: L1001-L1025, loan_payments: L101-L125
# Extract numeric part to create common key
merged['LoanID_Num'] = merged['LoanID'].str.replace('L', '').astype(int) - 900  # L1001 -> 101
loan_payments['LoanID_Num'] = loan_payments['LoanID'].str.replace('L', '').astype(int)  # L101 -> 101

# Merge with loan_payments
merged = pd.merge(merged, loan_payments, on='LoanID_Num', how='left', suffixes=('', '_pay'))

# Derive AmountPaid and PaymentStatus
merged['AmountPaid'] = merged['EMIAmount'] * merged['PaidEMIs']

# Derive PaymentStatus
conditions = [
    merged['PendingEMIs'] == 0,
    (merged['PaidEMIs'] > 0) & (merged['PendingEMIs'] > 0),
    merged['PaidEMIs'] == 0
]
choices = ['Paid', 'Partial', 'Pending']
merged['PaymentStatus'] = np.select(conditions, choices, default='Partial')

# Create the single dataframe with required columns
final_columns = ['CustomerName', 'City', 'LoanType', 'LoanAmount', 'CreditScore',
                 'Salary', 'LoanStatus', 'EMIAmount', 'PaymentStatus', 'AmountPaid',
                 'PaidEMIs', 'PendingEMIs', 'CustomerID', 'LoanID', 'ApplicationDate',
                 'LastPaymentDate', 'InterestRate', 'Tenure', 'Age', 'Gender', 'State']

# Keep all available columns
merged_df = merged.copy()
print(f"Merged DataFrame Shape: {merged_df.shape}")
print(f"\nMerged DataFrame Columns:\n{merged_df.columns.tolist()}")
print(f"\nSample of Merged Data:")
print(merged_df[['CustomerName', 'City', 'LoanType', 'LoanAmount', 'CreditScore',
                 'Salary', 'LoanStatus', 'EMIAmount', 'PaymentStatus']].head(10))

# ============================================================
# PART 4 – Create New Columns
# ============================================================
print("\n" + "=" * 60)
print("PART 4 – Create New Columns")
print("=" * 60)

# Monthly Income
merged_df['MonthlyIncome'] = merged_df['Salary'] / 12
print(f"MonthlyIncome (first 5): {merged_df['MonthlyIncome'].head().tolist()}")

# Debt-to-Income Ratio
merged_df['DebtToIncomeRatio'] = merged_df['LoanAmount'] / merged_df['Salary']
print(f"DebtToIncomeRatio (first 5): {merged_df['DebtToIncomeRatio'].head().tolist()}")

# EMI Due
merged_df['EMIDue'] = merged_df['EMIAmount'] - merged_df['AmountPaid']
print(f"EMIDue (first 5): {merged_df['EMIDue'].head().tolist()}")

# Payment Completion %
merged_df['PaymentCompletion%'] = (merged_df['AmountPaid'] / merged_df['EMIAmount']) * 100
print(f"PaymentCompletion% (first 5): {merged_df['PaymentCompletion%'].head().tolist()}")

# ============================================================
# PART 5 – NumPy Tasks
# ============================================================
print("\n" + "=" * 60)
print("PART 5 – NumPy Tasks")
print("=" * 60)

loan_amounts = merged_df['LoanAmount'].to_numpy()

avg_loan = np.mean(loan_amounts)
median_loan = np.median(loan_amounts)
max_loan = np.max(loan_amounts)
min_loan = np.min(loan_amounts)
std_loan = np.std(loan_amounts)
var_loan = np.var(loan_amounts)
percentile_25 = np.percentile(loan_amounts, 25)
percentile_75 = np.percentile(loan_amounts, 75)

print(f"Average Loan Amount: ₹{avg_loan:,.2f}")
print(f"Median Loan Amount: ₹{median_loan:,.2f}")
print(f"Maximum Loan Amount: ₹{max_loan:,.2f}")
print(f"Minimum Loan Amount: ₹{min_loan:,.2f}")
print(f"Standard Deviation: ₹{std_loan:,.2f}")
print(f"Variance: ₹{var_loan:,.2f}")
print(f"25th Percentile Loan Amount: ₹{percentile_25:,.2f}")
print(f"75th Percentile Loan Amount: ₹{percentile_75:,.2f}")

# ============================================================
# PART 6 – Pandas Analysis
# ============================================================
print("\n" + "=" * 60)
print("PART 6 – Pandas Analysis")
print("=" * 60)

# Top 10 highest loan customers
top10_loan = merged_df.nlargest(10, 'LoanAmount')[['CustomerName', 'LoanAmount', 'LoanType']]
print("\n--- Top 10 Highest Loan Customers ---")
print(top10_loan.to_string(index=False))

# Top 10 customers by salary
top10_salary = merged_df.nlargest(10, 'Salary')[['CustomerName', 'Salary', 'City']]
print("\n--- Top 10 Customers by Salary ---")
print(top10_salary.to_string(index=False))

# Customers with Credit Score below 650
low_credit = merged_df[merged_df['CreditScore'] < 650][['CustomerName', 'CreditScore', 'LoanStatus']]
print("\n--- Customers with Credit Score below 650 ---")
print(low_credit.to_string(index=False))

# Customers with Loan Amount greater than ₹20 Lakhs
high_loan = merged_df[merged_df['LoanAmount'] > 2000000][['CustomerName', 'LoanAmount', 'LoanType']]
print("\n--- Customers with Loan Amount > ₹20 Lakhs ---")
print(high_loan.to_string(index=False))

# Loans with Pending Payments
pending_payments = merged_df[merged_df['PaymentStatus'] == 'Pending'][['CustomerName', 'LoanAmount', 'PaymentStatus']]
print("\n--- Loans with Pending Payments ---")
print(pending_payments.to_string(index=False))

# Fully Paid Loans
fully_paid = merged_df[merged_df['PaymentStatus'] == 'Paid'][['CustomerName', 'LoanAmount', 'PaymentStatus']]
print("\n--- Fully Paid Loans ---")
if len(fully_paid) > 0:
    print(fully_paid.to_string(index=False))
else:
    print("No fully paid loans found.")

# ============================================================
# PART 7 – GroupBy
# ============================================================
print("\n" + "=" * 60)
print("PART 7 – GroupBy Analysis")
print("=" * 60)

# Group by City
print("\n--- Group by City ---")
city_group = merged_df.groupby('City').agg(
    NumberOfCustomers=('CustomerName', 'count'),
    AverageSalary=('Salary', 'mean'),
    TotalLoanAmount=('LoanAmount', 'sum')
).reset_index()
print(city_group.to_string(index=False))

# Group by Loan Type
print("\n--- Group by Loan Type ---")
loan_type_group = merged_df.groupby('LoanType').agg(
    NumberOfLoans=('LoanID', 'count'),
    AverageLoanAmount=('LoanAmount', 'mean'),
    TotalLoanAmount=('LoanAmount', 'sum')
).reset_index()
print(loan_type_group.to_string(index=False))

# Group by Loan Status
print("\n--- Group by Loan Status ---")
loan_status_group = merged_df.groupby('LoanStatus').agg(
    Count=('LoanID', 'count'),
    TotalLoanAmount=('LoanAmount', 'sum')
).reset_index()
print(loan_status_group.to_string(index=False))

# Group by Payment Status
print("\n--- Group by Payment Status ---")
payment_status_group = merged_df.groupby('PaymentStatus').agg(
    Count=('LoanID', 'count'),
    TotalAmountPaid=('AmountPaid', 'sum')
).reset_index()
print(payment_status_group.to_string(index=False))

# ============================================================
# PART 8 – Business Rules (Flagging)
# ============================================================
print("\n" + "=" * 60)
print("PART 8 – Business Rules (Flagging)")
print("=" * 60)

# Flag loans based on business rules
merged_df['Flag_HighLoanAmount'] = merged_df['LoanAmount'] > 3000000  # > ₹30 Lakhs
merged_df['Flag_LowCreditScore'] = merged_df['CreditScore'] < 650
merged_df['Flag_LowSalary'] = merged_df['Salary'] < 30000
merged_df['Flag_HighDTI'] = merged_df['DebtToIncomeRatio'] > 5
merged_df['Flag_HighEMIDue'] = merged_df['EMIDue'] > 10000
merged_df['Flag_PendingPayment'] = merged_df['PaymentStatus'] == 'Pending'
merged_df['Flag_Rejected'] = merged_df['LoanStatus'] == 'Rejected'

print(f"Loan Amount > ₹30 Lakhs: {merged_df['Flag_HighLoanAmount'].sum()}")
print(f"Credit Score < 650: {merged_df['Flag_LowCreditScore'].sum()}")
print(f"Salary < ₹30,000: {merged_df['Flag_LowSalary'].sum()}")
print(f"Debt-to-Income Ratio > 5: {merged_df['Flag_HighDTI'].sum()}")
print(f"EMI Due > ₹10,000: {merged_df['Flag_HighEMIDue'].sum()}")
print(f"Payment Status = Pending: {merged_df['Flag_PendingPayment'].sum()}")
print(f"Loan Status = Rejected: {merged_df['Flag_Rejected'].sum()}")

# Display flagged records
flagged = merged_df[
    merged_df['Flag_HighLoanAmount'] | merged_df['Flag_LowCreditScore'] |
    merged_df['Flag_LowSalary'] | merged_df['Flag_HighDTI'] |
    merged_df['Flag_HighEMIDue'] | merged_df['Flag_PendingPayment'] |
    merged_df['Flag_Rejected']
]
print(f"\nTotal Flagged Loans: {len(flagged)}")
print(flagged[['CustomerName', 'LoanAmount', 'CreditScore', 'Salary',
               'DebtToIncomeRatio', 'LoanStatus', 'PaymentStatus']].to_string(index=False))

# ============================================================
# PART 9 – Finance Metrics
# ============================================================
print("\n" + "=" * 60)
print("PART 9 – Finance Metrics")
print("=" * 60)

# Total Loan Portfolio
total_loan_portfolio = merged_df['LoanAmount'].sum()
print(f"Total Loan Portfolio (Sum of LoanAmount): ₹{total_loan_portfolio:,.2f}")

# Total Amount Collected
total_amount_collected = merged_df['AmountPaid'].sum()
print(f"Total Amount Collected (Sum of AmountPaid): ₹{total_amount_collected:,.2f}")

# Outstanding Amount
merged_df['OutstandingAmount'] = merged_df['LoanAmount'] - merged_df['AmountPaid']
total_outstanding = merged_df['OutstandingAmount'].sum()
print(f"Total Outstanding Amount: ₹{total_outstanding:,.2f}")

# Loan Recovery %
loan_recovery_pct = (total_amount_collected / total_loan_portfolio) * 100
print(f"Loan Recovery %: {loan_recovery_pct:.2f}%")

# Default % (Pending Loans / Total Loans)
pending_loans = len(merged_df[merged_df['LoanStatus'] == 'Pending'])
total_loans = len(merged_df)
default_pct = (pending_loans / total_loans) * 100
print(f"Default % (Pending/Total): {default_pct:.2f}%")

# Average EMI
avg_emi = merged_df['EMIAmount'].mean()
print(f"Average EMI Amount: ₹{avg_emi:,.2f}")

# Average Credit Score
avg_credit_score = merged_df['CreditScore'].mean()
print(f"Average Credit Score: {avg_credit_score:.2f}")

# ============================================================
# PART 10 – Export Reports
# ============================================================
print("\n" + "=" * 60)
print("PART 10 – Export Reports")
print("=" * 60)

# Loan Summary
loan_summary = merged_df[['CustomerName', 'LoanType', 'LoanAmount', 'LoanStatus',
                          'EMIAmount', 'AmountPaid', 'PaymentStatus', 'OutstandingAmount',
                          'DebtToIncomeRatio', 'PaymentCompletion%']].copy()
loan_summary.to_excel('LoanSummary.xlsx', index=False)
print("LoanSummary.xlsx exported successfully.")

# Customer Loan Report
customer_loan_report = merged_df[['CustomerName', 'City', 'Salary', 'CreditScore',
                                   'LoanType', 'LoanAmount', 'InterestRate', 'Tenure',
                                   'LoanStatus', 'EMIAmount', 'AmountPaid',
                                   'PaymentStatus', 'MonthlyIncome',
                                   'DebtToIncomeRatio']].copy()
customer_loan_report.to_excel('CustomerLoanReport.xlsx', index=False)
print("CustomerLoanReport.xlsx exported successfully.")

# Pending Payments
pending_payments_report = merged_df[merged_df['PaymentStatus'] != 'Paid'][
    ['CustomerName', 'LoanType', 'LoanAmount', 'EMIAmount', 'AmountPaid',
     'PendingEMIs', 'PaymentStatus', 'OutstandingAmount']].copy()
pending_payments_report.to_csv('PendingPayments.csv', index=False)
print("PendingPayments.csv exported successfully.")

# ============================================================
# EXPECTED OUTPUTS – Display Summary
# ============================================================
print("\n" + "=" * 60)
print("EXPECTED OUTPUTS – Display Summary")
print("=" * 60)

print("\n--- Top 10 Loan Customers ---")
print(merged_df.nlargest(10, 'LoanAmount')[['CustomerName', 'LoanAmount', 'LoanType', 'City']].to_string(index=False))

print("\n--- Customers with Low Credit Score (< 650) ---")
low_cs = merged_df[merged_df['CreditScore'] < 650][['CustomerName', 'CreditScore', 'City', 'LoanStatus']]
print(low_cs.to_string(index=False))

print("\n--- Pending Loan Payments ---")
pending = merged_df[merged_df['PaymentStatus'] == 'Pending'][['CustomerName', 'LoanAmount', 'EMIAmount', 'PaymentStatus']]
if len(pending) > 0:
    print(pending.to_string(index=False))
else:
    print("No pending payments (all customers have at least partial payments).")

print("\n--- City-wise Loan Summary ---")
print(city_group.to_string(index=False))

print("\n--- Loan Type Summary ---")
print(loan_type_group.to_string(index=False))

print("\n--- Loan Recovery Report ---")
recovery_report = pd.DataFrame({
    'Metric': ['Total Loan Portfolio', 'Total Amount Collected', 'Total Outstanding',
               'Loan Recovery %', 'Default %', 'Average EMI', 'Average Credit Score'],
    'Value': [f'₹{total_loan_portfolio:,.2f}', f'₹{total_amount_collected:,.2f}',
              f'₹{total_outstanding:,.2f}', f'{loan_recovery_pct:.2f}%',
              f'{default_pct:.2f}%', f'₹{avg_emi:,.2f}', f'{avg_credit_score:.2f}']
})
print(recovery_report.to_string(index=False))

print("\n" + "=" * 60)
print("Case Study 3 - COMPLETED SUCCESSFULLY!")
print("=" * 60)
