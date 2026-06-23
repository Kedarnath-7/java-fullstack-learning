package com.northernArc.springIntro.xmlSpringConfiguration;

public class Email implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Notifying " + message + " via email....");
    }
}
