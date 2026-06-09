package day03.rechargeTracker;

public class RechargeTracker {
    private double balance;
    private double chargesPerMin;

    public RechargeTracker(double initialBalance,  double chargesPerMin) {
        this.balance = initialBalance;
        this.chargesPerMin = chargesPerMin;
    }

    public void topup(double amount){
        this.balance += amount;
    }

    public void makeCall(int minutes){
        if(minutes < 0){
            System.out.println("Invalid minutes....");
        }else if(minutes * this.chargesPerMin > this.balance){
            System.out.println("Your balance is insufficient for given minutes...cannot proceed to making call...");
        }else if(minutes * this.chargesPerMin <= this.balance){
            System.out.println("Your balance is sufficient for given minutes, going ahead and making call...");
            this.balance -= minutes * chargesPerMin;
        }
    }

    public void checkBalance(){
        System.out.println("Current balance is " + this.balance);
    }
}
