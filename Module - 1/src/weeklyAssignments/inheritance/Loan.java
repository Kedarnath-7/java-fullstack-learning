package weeklyAssignments.inheritance;

public class Loan {
    protected String loanId;
    protected String customerId;
    protected double loanAmount;
    protected double interestRate;

    public Loan(String loanId, String customerId, double loanAmount, double interestRate) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
    }
    public void displayLoan(){
        System.out.println("Loan ID: " + loanId);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Interest Rate: " + interestRate);
    }
}
