package day02.inheritance;

public class Debit extends Payment {
    public Debit(double amount){
        super(amount);
    }
    public void pay(){
        System.out.println("Paying " + this.amount + " using Debit Card...");
    }
}
