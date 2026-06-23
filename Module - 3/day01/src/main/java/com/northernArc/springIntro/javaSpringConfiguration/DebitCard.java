package com.northernArc.springIntro.javaSpringConfiguration;

public class DebitCard implements PaymentService{
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using debit card");
    }
}
