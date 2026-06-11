package day02.streams.entity;

public class Loan {
    private int loadId;
    private double loadAmount;
    private double interestRate;
    private double tenure;
    private String status;
    private String loanType;

    public Loan(int id, double loanAmount, double interestRate, double tenure, String status, String loanType) {
        this.loadId = id;
        this.loadAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.status = status;
        this.loanType = loanType;
    }

    public int getLoadId() {
        return loadId;
    }
    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }
    public double getLoadAmount() {
        return loadAmount;
    }
    public void setLoadAmount(double loadAmount) {
        this.loadAmount = loadAmount;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public double getTenure() {
        return tenure;
    }
    public void setTenure(double tenure) {
        this.tenure = tenure;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getLoanType() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @Override
    public String toString(){
        return "{Loan id: " + this.loadId + ", LoanAmount: " + this.loadAmount + ", Loan type: " + this.loanType + ", Loan Status: " + this.status + ", LoanType: " + this.loanType + ", Interest: " + this.interestRate + "}";
    }
}
