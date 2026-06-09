package day01.associationExamples.aggregation;

public class LoanApplication {
    private String loanId;
    private double loanAmount;


    public LoanApplication(String loanId, double loanAmount) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
    }

    public String getLoanId() {
        return loanId;
    }
    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
    public double getLoanAmount() {
        return loanAmount;
    }
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    @Override
    public String toString() {
        return loanId + " " + loanAmount;
    }

}
