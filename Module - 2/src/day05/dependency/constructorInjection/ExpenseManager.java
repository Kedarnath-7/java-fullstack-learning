package day05.dependency.constructorInjection;

public class ExpenseManager {
    PaymentService paymentService;
    NotificationService notificationService;
    public ExpenseManager(PaymentService paymentService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }
    public void payElectricityBill(double amount){
        System.out.println("Paying electricity bill of " + amount);
        paymentService.pay();
        System.out.println("Electricity bill has been paid");
    }

    public void payWaterBill(double amount){
        System.out.println("Paying water bill of " + amount);
        paymentService.pay();
        System.out.println("Water bill has been paid");
    }

    public void payGasBill(double amount){
        System.out.println("Paying gas bill of " + amount);
        paymentService.pay();
        System.out.println("Gas bill has been paid");
    }
}
