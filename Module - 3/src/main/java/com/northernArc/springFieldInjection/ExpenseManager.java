package com.northernArc.springFieldInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ExpenseManager {
    @Autowired
    @Qualifier("debit")
    private PaymentService debitService;

    @Autowired
    @Qualifier("credit")
    private PaymentService creditService;

    @Autowired
    @Qualifier("upi")
    private PaymentService upiService;

    @Autowired
    @Qualifier("text")
    private NotificationService textService;

    @Autowired
    @Qualifier("whatsapp")
    private NotificationService whatsappService;

    @Autowired
    @Qualifier("email")
    private NotificationService emailService;

    private PaymentService paymentService;
    private NotificationService notificationService;

    public ExpenseManager() {

    }

    // constructor injection
//    public ExpenseManager(PaymentService paymentService, NotificationService notificationService) {
//        this.paymentService = paymentService;
//        this.notificationService = notificationService;
//    }


    public PaymentService getPaymentService(int choice) {
        return switch (choice) {
            case 1 -> creditService;
            case 2 -> debitService;
            case 3 -> upiService;
            default -> throw new IllegalArgumentException("Invalid payment choice");
        };
    }

    public NotificationService getNotificationService(int choice) {
        return switch (choice) {
            case 1 -> emailService;
            case 2 -> whatsappService;
            case 3 -> textService;
            default -> throw new IllegalArgumentException("Invalid notification choice");
        };
    }



    public void payElectricityBill(int paymentChoice, int notificationChoice, double amount){
        getPaymentService(paymentChoice);
        getNotificationService(notificationChoice);
        paymentService.pay(amount);
        notificationService.notify("Electricity bill has been paid");
    }

    public void payWaterBill(double amount){
        paymentService.pay(amount);
        notificationService.notify("'Water bill has been paid'");
    }

    public void payGasBill(double amount){
        System.out.println("Paying gas bill of " + amount);
        paymentService.pay(amount);
        notificationService.notify("'Gas bill has been paid'");
    }


    // setter injection
//    public void setPaymentService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    public void setNotificationService(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
}
