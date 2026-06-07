package Abstraction;

public class Whatsapp extends SimpleMessage {
    public void setMessage(String message) {
        this.message = message;
    }
    public void send() {
        System.out.println("Sending Whatsapp message..." + this.message);
    }
    
}
