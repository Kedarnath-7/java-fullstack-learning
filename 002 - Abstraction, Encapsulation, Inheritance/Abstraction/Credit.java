package Abstraction;

class Credit extends Payment{
    public Credit(double amount){
        this.amount = amount;
    }
    public void pay(){
        System.out.println("Paying " + this.amount + " through Credit...");
    }
    
}
