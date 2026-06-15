package day05.dependency.setterInjection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Online Payment Service....");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose payment service: 1. Debit Card\2. Credit Card\3. UPI\4. Exit");
        int choice = sc.nextInt();
        sc.nextLine();
        ExpenseManager expenseManager = new ExpenseManager();
        switch (choice) {
            case 1:
                expenseManager.setPaymentService(new DebitCard());
                break;
            case 2:
                expenseManager.setPaymentService(new CreditCard());
                break;
            case 3:
                expenseManager.setPaymentService(new Upi());
                break;
            default:
                System.out.println("Invalid choice.....Defaulting to debit card...");
                expenseManager.setPaymentService(new DebitCard());
                break;
        }
        System.out.println("Please enter the amount you want to pay:");
        double amount = sc.nextDouble();
        sc.nextLine();
        expenseManager.payElectricityBill(amount);
        expenseManager.payWaterBill(amount);
        expenseManager.payGasBill(amount);
        sc.close();
    }
}
