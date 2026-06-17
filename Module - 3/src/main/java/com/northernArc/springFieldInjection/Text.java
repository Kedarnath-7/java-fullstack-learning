package com.northernArc.springFieldInjection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("text")
public class Text implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println("Notifying " + message + " via Text....");
    }
}
