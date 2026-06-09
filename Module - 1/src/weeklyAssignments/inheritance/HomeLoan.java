package weeklyAssignments.inheritance;

public class HomeLoan extends Loan {
    private String address;
    public HomeLoan(String loanId, String customerId, double loanAmount, double interestRate, String address){
        super(loanId, customerId, loanAmount, interestRate);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void displayLoan(){
        super.displayLoan();
        System.out.println("Address: " + this.address);
    }
}
