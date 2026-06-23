package com.northernArc.springIntro.javaSpringConfiguration;

public class WhatsApp implements NotificationService{
    @Override
    public void notify(String message) {
        System.out.println("Notifying " + message + " via WhatsApp....");
    }
}
