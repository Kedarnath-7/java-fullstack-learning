package Inheritance;

public class Credit extends Payment {
    public Credit(double amount){
        super(amount);
    }
    public void pay(){
        System.out.println("Paying " + this.amount + " using Credit Card...");
    }
    
}
