package day05.dependency.constructorInjection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Online Payment Service....");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose payment service: 1. Debit Card\2. Credit Card\3. UPI\4. Exit");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter notification type: email/whatsapp");
        String notificationType=sc.next();
        PaymentService paymentService= PaymentAndNotificationFactory.getPaymentService(choice);
        NotificationService notificationService=PaymentAndNotificationFactory.getNotificationService(notificationType);
        ExpenseManager expenseManager=new ExpenseManager(paymentService,notificationService);
        expenseManager.payElectricityBill(1000);
        expenseManager.payWaterBill(200);
        expenseManager.payGasBill(100);


    }
}
