package com.northernArc.springIntro.javaSpringConfiguration;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfiguration.class);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Expense Manager Services...");

        System.out.println("Enter electricity bill amount: ");
        double electricityBillAmount = sc.nextDouble();
        System.out.println("Enter gas bill amount: ");
        double gasBillAmount = sc.nextDouble();
        System.out.println("Enter water bill amount: ");
        double waterBillAmount = sc.nextDouble();

        System.out.println("Select the type of payment you want to do: ");
        System.out.println("1.credit\n2.debit\n3.upi");
        String paymentChoice = sc.next();

//        PaymentService paymentService = switch (paymentChoice) {
//            case 1 -> context.getBean("creditCard", PaymentService.class);
//            case 2 -> context.getBean("debitCard", PaymentService.class);
//            case 3 -> context.getBean("upi", PaymentService.class);
//            default -> throw new IllegalArgumentException("Invalid choice");
//        };

        PaymentService paymentService = context.getBean(paymentChoice, PaymentService.class);

        System.out.println("Now select notification service: ");
        System.out.println("1.email\n2.whatsapp\n3.text");
        String notificationChoice = sc.next();

//        NotificationService notificationService = switch (notificationChoice) {
//            case 1 -> context.getBean("emailService", NotificationService.class);
//            case 2 -> context.getBean("whatsAppService", NotificationService.class);
//            case 3 -> context.getBean("textService", NotificationService.class);
//            default -> throw new IllegalArgumentException("Invalid choice");
//        };

        NotificationService notificationService = context.getBean(notificationChoice, NotificationService.class);


        // constructor injection
        ExpenseManager expenseManager = new ExpenseManager(paymentService, notificationService);

//        *** setter injection ****
//        ExpenseManager expenseManager =  new ExpenseManager();
//        expenseManager.setPaymentService(paymentService);
//        expenseManager.setNotificationService(notificationService);

        expenseManager.payElectricityBill(electricityBillAmount);
        expenseManager.payGasBill(gasBillAmount);
        expenseManager.payWaterBill(waterBillAmount);
    }
}
