package com.northernArc.springFieldInjection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upi")
public class Upi implements PaymentService {
    @Override
    public void pay(double amount){
        System.out.println("Paying " + amount + " using upi");
    }
}
