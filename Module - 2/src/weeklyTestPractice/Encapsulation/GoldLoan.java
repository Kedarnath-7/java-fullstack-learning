package weeklyTestPractice.Encapsulation;

public class GoldLoan extends Loan{
    private double goldWeight;

    public GoldLoan(String loanId, String customerName, double loanAmount, double goldWeight){
        super(loanId, customerName, loanAmount);
        this.goldWeight = goldWeight;
    }
    public void calculateInterest(){
        super.display();
        System.out.println("Interest: " + this.getLoanAmount()*0.014);
    }
}
