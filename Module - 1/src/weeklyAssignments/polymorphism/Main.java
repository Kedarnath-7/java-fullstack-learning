package weeklyAssignments.polymorphism;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Northern Arc EMI Calculator...");
        System.out.println("Choose the loan type: 1. Personal\n2. Educational\n3. Vehicle");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        Loan loan;
        System.out.println("Enter principal amount: ");
        double principal = sc.nextDouble();
        System.out.println("Enter interest rate: ");
        double interestRate = sc.nextDouble();
        sc.nextLine();
        switch (choice){
            case 1:
                System.out.println("Calculating Personal Loan EMI....");
                loan = new PersonalLoan(principal, interestRate);
                break;
            case 2:
                System.out.println("Calculating Educational Loan EMI....");
                loan = new EducationalLoan(principal, interestRate);
                break;
            case 3:
                System.out.println("Calculating Vehicle Loan EMI....");
                loan = new VehicleLoan(principal, interestRate);
                break;
            default:
                System.out.println("Invalid choice");
                loan = new PersonalLoan(0, 0);
                break;
        }
        System.out.println("EMI: " + loan.calculateEMI());
    }
}
