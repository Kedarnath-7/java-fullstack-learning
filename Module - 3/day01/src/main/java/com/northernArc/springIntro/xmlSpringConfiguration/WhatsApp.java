package com.northernArc.springIntro.xmlSpringConfiguration;

public class WhatsApp implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Notifying " + message + " via WhatsApp....");
    }
}
