package day02.inheritance;

public class Upi extends Payment {

    public Upi(double amount){
        super(amount);
    }
    public void pay(){
        System.out.println("Paying " + this.amount + " using UPI...");
    }
    
}
