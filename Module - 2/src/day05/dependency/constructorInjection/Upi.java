package day05.dependency.constructorInjection;

public class Upi implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Paying using Upi card....");
    }
}
