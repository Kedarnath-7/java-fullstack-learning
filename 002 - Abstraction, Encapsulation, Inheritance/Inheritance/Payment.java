package Inheritance;

public class Payment {
    protected double amount;

    public Payment(double amount){
        this.amount = amount;
    }
    public void pay(){
        System.out.println("Paying "  + this.amount + " using cash...");
    }
}
