package day02.abstraction;

class Debit extends Payment{
    public Debit(double amount){
        this.amount = amount;
    }
    public void pay(){
        System.out.println("Paying " + this.amount + " through Debit...");
    }
}