package day05.dependency.setterInjection;

public class CreditCard implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Paying using credit card....");
    }
}