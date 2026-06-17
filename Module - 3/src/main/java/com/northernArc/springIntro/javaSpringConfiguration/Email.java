package com.northernArc.springIntro.javaSpringConfiguration;

public class Email implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Notifying " + message + " via email....");
    }
}
