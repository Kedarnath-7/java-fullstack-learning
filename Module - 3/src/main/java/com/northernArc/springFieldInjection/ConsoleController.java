package com.northernArc.springFieldInjection;

import java.util.Scanner;

public class ConsoleController {
    private Scanner scanner;
    private PaymentService paymentService;
    private NotificationService notificationService;

    public ConsoleController(Scanner scanner, PaymentService paymentService, NotificationService notificationService) {
        this.scanner = scanner;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public void welcomeMessage(){
        System.out.println("Welcome to the console controller...!");
    }

    public void showMenu(){
        while(true){
            System.out.println("1:Credit");
            System.out.println("2:Debit");
            System.out.println("3:Upi");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            redirectChoice(choice);
        }
    }

    public void redirectChoice(int choice){

    }
}
