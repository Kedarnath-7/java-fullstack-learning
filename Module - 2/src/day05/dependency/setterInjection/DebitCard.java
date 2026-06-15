package day05.dependency.setterInjection;

public class DebitCard implements PaymentService {
    @Override
    public void pay() {
        System.out.println("Paying using debit card....");
    }
}
