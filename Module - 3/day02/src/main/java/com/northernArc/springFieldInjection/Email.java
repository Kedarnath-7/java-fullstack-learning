package com.northernArc.springFieldInjection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("email")
public class Email implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Notifying " + message + " via email....");
    }
}
