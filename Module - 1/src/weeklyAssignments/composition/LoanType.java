package weeklyAssignments.composition;

public class LoanType {
    private String type;
    private double interestRate;

    public LoanType(String type, double interestRate) {
        this.type = type;
        this.interestRate = interestRate;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "type=" + type + ", interestRate=" + interestRate;
    }
}
