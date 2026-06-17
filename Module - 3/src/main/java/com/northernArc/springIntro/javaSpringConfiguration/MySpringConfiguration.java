package com.northernArc.springIntro.javaSpringConfiguration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySpringConfiguration {
    @Bean("credit")
    public PaymentService creditCard() {
        return new CreditCard();
    }

    @Bean("debit")
    public PaymentService debitCard(){
        return new DebitCard();
    }

    @Bean("upi")
    public PaymentService upi(){
        return new Upi();
    }

    @Bean("email")
    public NotificationService emailService(){
        return new Email();
    }

    @Bean("whatsapp")
    public NotificationService whatsAppService(){
        return new WhatsApp();
    }

    @Bean("text")
    public NotificationService textService(){
        return new Text();
    }

    @Bean
    public ExpenseManager upiEmailManager(@Qualifier("upi") PaymentService paymentService, @Qualifier("email") NotificationService notificationService){
        return new ExpenseManager(paymentService, notificationService);
    }

    @Bean
    public ExpenseManager upiTextManager(@Qualifier("upi") PaymentService paymentService, @Qualifier("text") NotificationService notificationService){
        return  new ExpenseManager(paymentService, notificationService);
    }
}
