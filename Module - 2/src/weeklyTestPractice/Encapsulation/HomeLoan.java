package weeklyTestPractice.Encapsulation;

public class HomeLoan extends Loan{
    private String address;
    public HomeLoan(String loanId, String customerName, double loanAmount, String address){
        super(loanId, customerName, loanAmount);
        this.address = address;
    }
    public void calculateInterest(){
        super.display();
        System.out.println("Interest: " +  this.getLoanAmount()*0.08);
    }

}
