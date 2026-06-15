package day05.dependency.setterInjection;

public class ExpenseManager {
    PaymentService paymentService;

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
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
