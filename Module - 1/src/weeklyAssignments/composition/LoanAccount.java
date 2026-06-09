package weeklyAssignments.composition;

public class LoanAccount {
    private String loanId;
    private double sanctionedAmount;

    private Customer customer;
    private KycDetails kycDetails;
    private LoanType loanType;

    public LoanAccount(String loanId, double sanctionedAmount, Customer customer, KycDetails kycDetails, LoanType loanType) {
        this.loanId = loanId;
        this.sanctionedAmount = sanctionedAmount;
        this.customer = customer;
        this.kycDetails = kycDetails;
        this.loanType = loanType;
    }

    public void displayLoanDetails(){
        System.out.println("Loan id: " + this.loanId);
        System.out.println("Customer id: " + this.customer.getCustomerId());
        System.out.println("Customer name: " + this.customer.getCustomerName());
        System.out.println("Loan type: " + this.loanType);
        System.out.println("Loan amount: " + this.sanctionedAmount);
        System.out.println("Kyc details: " + this.kycDetails);

    }


}
