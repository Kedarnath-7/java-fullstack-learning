package weeklyAssignments.inheritance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome Northern Arc Loan Account System....");
        System.out.println("Let's set your loan account first...");
        System.out.println("Choose type of loan account: 1. Home Loan\n2. Gold Loan");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter home loan account details as prompted....");
        System.out.println("Enter load id: ");
        String loadId = sc.nextLine();
        System.out.println("Enter customer id: ");
        String customerId = sc.nextLine();
        System.out.println("Enter loan amount: ");
        double loanAmount = sc.nextDouble();
        System.out.println("Enter loan interest rate: ");
        double interestRate = sc.nextDouble();
        sc.nextLine();
        Loan loan;
        switch (choice) {
            case 1:
                System.out.println("Enter property address: ");
                String propertyAddress = sc.nextLine();
                loan = new HomeLoan(loadId, customerId, loanAmount, interestRate, propertyAddress);
                break;
            case 2:
                System.out.println("Enter gold weight: ");
                double goldWeight = sc.nextDouble();
                loan = new GoldLoan(loadId, customerId, loanAmount, interestRate, goldWeight);
                break;
            default:
                System.out.println("Wrong choice...loan details empty...");
                loan = new Loan("", "", 0.0, 0.0);
        }
        System.out.println("Displaying loan details....");
        loan.displayLoan();
    }
}
