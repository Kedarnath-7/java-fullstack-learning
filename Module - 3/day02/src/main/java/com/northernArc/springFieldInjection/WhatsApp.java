package com.northernArc.springFieldInjection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("whatsapp")
public class WhatsApp implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Notifying " + message + " via WhatsApp....");
    }
}
