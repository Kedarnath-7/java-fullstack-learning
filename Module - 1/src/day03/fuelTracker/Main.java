package day03.fuelTracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to CSK Fuel Tracker...");
        System.out.println("Let's start by setting up...");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your fuel tank capacity: ");
        double fuelTankCapacity = sc.nextDouble();
        System.out.println("Enter your vehicle mileage: ");
        int mileage = sc.nextInt();
        System.out.println("Enter your initial fuel amount: ");
        double fuelAmount = sc.nextDouble();
        FuelTracker fuelTracker = new FuelTracker(fuelTankCapacity, mileage, fuelAmount);
        boolean flag = true;
        while (flag) {
            System.out.println("Please select the service you want: 1. Fill fuel\n2. Check fuel\n3. Drive\n4. Exit");
            int choice  = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the fuel amount to fill: ");
                    double amount = sc.nextDouble();
                    fuelTracker.fillFuel(amount);
                    break;
                case 2:
                    fuelTracker.checkFuel();
                    break;
                case 3:
                    System.out.println("Enter the distance you want to drive(KMs): ");
                    double kms = sc.nextDouble();
                    fuelTracker.drive(kms);
                    break;
                case 4:
                    System.out.println("Thanks for  using our Fuel Tracker... Exiting..");
                    flag = false;
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid choice.. Try again...");
                    break;
            }
        }

    }
}
