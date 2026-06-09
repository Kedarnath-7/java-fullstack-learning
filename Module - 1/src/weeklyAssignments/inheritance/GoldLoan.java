package weeklyAssignments.inheritance;

public class GoldLoan extends Loan {
    private double goldWeight;

    public GoldLoan(String loanId, String customerId, double loanAmount, double interestRate, double goldWeight) {
        super(loanId, customerId, loanAmount, interestRate);
        this.goldWeight = goldWeight;
    }
    public double getGoldWeight() {
        return goldWeight;
    }
    public void setGoldWeight(double goldWeight) {
        this.goldWeight = goldWeight;
    }
    public void displayLoan(){
        super.displayLoan();
        System.out.println("Gold Weight: " + this.goldWeight);
    }
}
