package weeklyAssignments.association;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to customer review loan application....");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id: ");
        String customerId = sc.nextLine();
        System.out.println("Enter customer name: ");
        String customerName = sc.nextLine();

        Customer customer = new Customer(customerId, customerName);

        System.out.println("Enter loan officer name: ");
        String officerName = sc.nextLine();
        LoanOfficer loanOfficer = new LoanOfficer(officerName);

        loanOfficer.reviewLoanApplication(customer);

        sc.close();
    }
}
