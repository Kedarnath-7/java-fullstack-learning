package weeklyAssignments.aggregation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to CSK Bank...");
        System.out.println("Enter number of customers:");
        Scanner sc = new Scanner(System.in);
        int numCustomers = sc.nextInt();
        Customer[] customers = new Customer[numCustomers];
        for (int i = 0; i < numCustomers; i++) {
            System.out.println("Enter customer " + (int)(i+1) + ":");
            System.out.println("Enter customer id; ");
            String customerId = sc.nextLine();
            System.out.println("Enter customer name; ");
            String customerName = sc.nextLine();
            customers[i] = new Customer(customerId, customerName);
        }

        System.out.println("Enter branch name: ");
        String branchName = sc.nextLine();
        Branch branch = new Branch(branchName, customers);
        branch.showCustomers();

    }
}
