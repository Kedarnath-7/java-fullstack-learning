package weeklyAssignments.polymorphism;

public class VehicleLoan extends Loan{
    public VehicleLoan(double principal, double interestRate){
        super(principal, interestRate);
    }
    @Override
    public double calculateEMI(){
        return (this.interestRate/100) * this.principal;
    }
}
