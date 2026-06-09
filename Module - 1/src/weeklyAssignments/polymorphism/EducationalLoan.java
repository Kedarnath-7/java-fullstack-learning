package weeklyAssignments.polymorphism;

public class EducationalLoan extends Loan {
    public EducationalLoan(double principal, double interestRate){
        super(principal, interestRate);
    }
    @Override
    public double calculateEMI(){
        return (this.interestRate/100) * this.principal;
    }
}
