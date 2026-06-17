package com.northernArc.springIntro.xmlSpringConfiguration;

public class Upi implements PaymentService {
    @Override
    public void pay(double amount){
        System.out.println("Paying " + amount + " using upi");
    }
}
