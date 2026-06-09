package day02.abstraction;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the animal: 1. Lion\n2. Dog\n3.Deer\n");
        int choice = sc.nextInt();

        Animal animal;

        switch(choice) {
            case 1:
                animal = new Lion();
                break;
            case 2:
                animal = new Dog("Kedar");
                break;
            case 3:
                animal = new Deer();
                break;
            default:
                System.out.println("Invalid response...");
                sc.close();
                return;
        }
        animal.eat();
        animal.talk();
        animal.walk();
        animal.shelter();

        System.out.println("Would you like to send a message: 1. Yes\n2. No\n");
        int messageChoice = sc.nextInt();
        sc.nextLine();
        if(messageChoice != 1){
            sc.close();
            return;
        }
        System.out.println("Enter the message your message: ");
        String message = sc.nextLine();
        
        System.out.println("Choose provider: 1. Text\n2. Whatsapp\n3. Email");
        int providerChoice = sc.nextInt();
        SimpleMessage simpleMessage;
        switch(providerChoice) {
            case 1:
                simpleMessage = new TextMessage();
                break;
            case 2:
                simpleMessage = new Whatsapp();
                break;
            case 3:
                simpleMessage = new Email();
                break;
            default:
                System.out.println("Invalid response...");
                sc.close();
                return;
        }
        simpleMessage.setMessage(message);
        simpleMessage.send();
        

        System.out.println("Would you like to make a payment? 1. Yes\n2. No");
        int paymentChoice = sc.nextInt();
        if(paymentChoice != 1){
            sc.close();
            return;
        }
        System.out.println("Enter the amount you want to pay: ");
        double amount = sc.nextDouble();
        System.out.println("Choose the payment method: 1. UPI\n2. Debit Card\n3. Credit Card \nDefault - Cash");
        int methodChoice = sc.nextInt();
        Payment newPayment;
        switch(methodChoice) {
            case 1:
                newPayment = new Upi(amount);
                break;
            case 2:
                newPayment = new Debit(amount);
                break;
            case 3:
                newPayment = new Credit(amount);
                break;
            default:
                System.out.println("Invalid response, so yourself pay by cash..");
                sc.close();
                return;
        }
        newPayment.pay();

        sc.close();

    }
}