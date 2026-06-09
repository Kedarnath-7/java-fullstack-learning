package day03.financeTracker;

import java.util.Scanner;

public class MainBank {
    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("CSK Bank welcomes you to the Net Banking Portal...");
        System.out.println("Let's first open an account in our bank to proceed with net banking options..");
        System.out.println("Please provide your name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("Now please enter your inital deposit amount: ");
        double initialBalance = sc.nextDouble();
        BankAccount newAccount = new BankAccount(name, initialBalance);
        while(flag){
            System.out.println("Please enter your choice...");
            System.out.println("Options: 1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit:");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("enter your deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    newAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("enter your withdrawal amount: ");
                    double withdrawalAmount = sc.nextDouble();
                    newAccount.withdraw(withdrawalAmount);
                    break;
                case 3:
                    newAccount.checkBalance();
                    break;
                case 4:
                    System.out.println("Exiting online net banking....See you again");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice, please choose one of the option above..");
                    break;
            }
        }
    }
}
