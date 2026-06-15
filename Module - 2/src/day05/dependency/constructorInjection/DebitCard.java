package day05.dependency.constructorInjection;

public class DebitCard implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Paying using debit card....");
    }
}
