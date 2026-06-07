package Polymorphism.demo;

public class BankAccount {

    //protected long bankAccountNumber;
    protected String owner;
    protected double balance;
    public BankAccount(String owner, double initialBalance){
        this.owner = owner;
        this.balance = initialBalance;
    }

    public void deposit(double amount){
        System.out.println("Initiating deposit...");
        this.balance += amount;
        System.out.println("Deposit is successful!, Your account is credited with " + amount + ". You can check the updated balance using check balance option..");
    }

    public void withdraw(double amount){
        if(amount > this.balance){
            System.out.println("Sorry bud!, you do not have enough balance in your bank account..");
            System.out.println("You can add the money to your bank account first.");
            return;
        }
        System.out.println("Initiating withdraw...");
        this.balance -= amount;
        System.out.println("Withdrawal Successful!, Your account is debited with " + amount + ". You can check the updated balance using check balance option..");
    }

    public void checkBalance(){
        System.out.println("Checking balance....");
        System.out.println("Your account balance is.. " + this.balance);
    }

}
