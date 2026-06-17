package com.northernArc.springIntro.xmlSpringConfiguration;

public class DebitCard implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using debit card");
    }
}
