package day03.rechargeTracker;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to CSK Recharge Tracker");
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        System.out.println("Let's get you a connection first..");
        System.out.println("Please enter your initial topup amount:");
        double initialBalance = sc.nextDouble();
        System.out.println("We have different charges per minute depending on local, state, and international: ");
        System.out.println("1. Local - 0.30/Min\n2. State - 0.50/Min\n3. International - 0.75/Min");
        System.out.println("Enter your choice:");
        int chargeChoice = sc.nextInt();
        double chargeAmount;
        if(chargeChoice == 1){
            chargeAmount = 0.30;
        }else if(chargeChoice == 2){
            chargeAmount = 0.50;
        }else if(chargeChoice == 3){
            chargeAmount = 0.75;
        }else{
            System.out.println("Invalid choice. Try again.");
            return;
        }
        RechargeTracker rechargeTracker = new RechargeTracker(initialBalance, chargeAmount);
        while(flag){
            System.out.println("Please select the service you want: 1. Topup\n2. Make Call\n3. Check Balance\n4. Exit\n");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Please enter the amount you want to topup: ");
                    double amount = sc.nextDouble();
                    rechargeTracker.topup(amount);
                    break;
                case 2:
                    System.out.println("Please enter the number of minutes: ");
                    int minutes = sc.nextInt();

                    rechargeTracker.makeCall(minutes);
                    break;
                case 3:
                    rechargeTracker.checkBalance();
                    break;
                case 4:
                    System.out.println("Thanks for  using our Recharge Tracker... Exiting.");
                    flag = false;
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again");
                    break;
            }
        }
    }

}
