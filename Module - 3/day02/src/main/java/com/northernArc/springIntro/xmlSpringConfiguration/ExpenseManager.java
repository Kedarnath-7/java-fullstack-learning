package com.northernArc.springIntro.xmlSpringConfiguration;

public class ExpenseManager {
    private PaymentService paymentService;
    private NotificationService notificationService;

    public ExpenseManager() {}

    // Constructor injection
    public ExpenseManager(PaymentService paymentService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }
    public void payElectricityBill(double amount){
        paymentService.pay(amount);
        notificationService.notify("'Electricity bill has been paid..'");
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


    //setter injection
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

}
