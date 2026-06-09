package weeklyAssignments.composition;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("CUS123", "Kedar");
        KycDetails kycDetails = new KycDetails("ABCD1235E", 123456789012d, true);
        LoanType loanType = new LoanType("Educational", 0.084);
        LoanAccount loanAccount = new LoanAccount("LO1234", 2000000d, customer, kycDetails, loanType);

        loanAccount.displayLoanDetails();
    }
}
