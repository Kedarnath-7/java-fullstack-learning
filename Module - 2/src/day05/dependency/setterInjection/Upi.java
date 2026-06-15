package day05.dependency.setterInjection;

public class Upi implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Paying using Upi card....");
    }
}
