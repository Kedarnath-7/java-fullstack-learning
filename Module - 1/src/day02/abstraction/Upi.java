package day02.abstraction;

public class Upi extends Payment{
    public Upi(double amount) {
        this.amount = amount;
    }

    public void pay(){
        System.out.println("Paying " + this.amount + " through UPI...");
    }
}
