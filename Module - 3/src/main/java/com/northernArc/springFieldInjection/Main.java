package com.northernArc.springFieldInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@ComponentScan("com.northernArc.springFieldInjection")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Expense Manager Services...");

        System.out.println("Enter electricity bill amount: ");
        double electricityBillAmount = sc.nextDouble();
        System.out.println("Enter gas bill amount: ");
        double gasBillAmount = sc.nextDouble();
        System.out.println("Enter water bill amount: ");
        double waterBillAmount = sc.nextDouble();

        System.out.println("Paying the bills through debit card....");

//        PaymentService paymentService = switch (paymentChoice) {
//            case 1 -> context.getBean("creditCard", PaymentService.class);
//            case 2 -> context.getBean("debitCard", PaymentService.class);
//            case 3 -> context.getBean("upi", PaymentService.class);
//            default -> throw new IllegalArgumentException("Invalid choice");
//        };

        //PaymentService paymentService = context.getBean(paymentChoice, PaymentService.class);

        System.out.println("Notifying through text...");

//        NotificationService notificationService = switch (notificationChoice) {
//            case 1 -> context.getBean("emailService", NotificationService.class);
//            case 2 -> context.getBean("whatsAppService", NotificationService.class);
//            case 3 -> context.getBean("textService", NotificationService.class);
//            default -> throw new IllegalArgumentException("Invalid choice");
//        };

        //NotificationService notificationService = context.getBean(notificationChoice, NotificationService.class);


        // field injection
        ExpenseManager expenseManager = context.getBean(ExpenseManager.class);

//        *** setter injection ****
//        ExpenseManager expenseManager =  new ExpenseManager();
//        expenseManager.setPaymentService(paymentService);
//        expenseManager.setNotificationService(notificationService);

        //expenseManager.payElectricityBill(electricityBillAmount);
        //expenseManager.payGasBill(gasBillAmount);
        //expenseManager.payWaterBill(waterBillAmount);
    }
}
