package weeklyAssignments.polymorphism;

abstract class Loan {
    protected double principal;
    protected double interestRate;

    public Loan(double principal, double interestRate) {
        this.principal = principal;
        this.interestRate = interestRate;
    }

    public abstract double calculateEMI();
}
