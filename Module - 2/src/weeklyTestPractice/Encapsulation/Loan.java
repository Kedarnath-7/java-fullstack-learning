package weeklyTestPractice.Encapsulation;

abstract class Loan {
    private String loanId;
    private String customerName;
    private double loanAmount;

    public Loan(String loanId, String customerName, double loanAmount){
        this.loanId = loanId;
        this.customerName = customerName;
        this.loanAmount = loanAmount;
    }
    abstract void calculateInterest();
    public void display(){
        System.out.println("Loan ID: " + this.loanId);
        System.out.println("Customer: " + this.customerName);
        System.out.println("Loan Amount: " + this.loanAmount);

    }

    public String getLoanId(){
        return this.loanId;
    }
    public String getCustomerName(){
        return this.customerName;
    }
    public double getLoanAmount(){
        return this.loanAmount;
    }
}
