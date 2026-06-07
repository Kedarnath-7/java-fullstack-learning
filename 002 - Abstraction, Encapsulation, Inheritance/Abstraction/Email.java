package Abstraction;

public class Email extends SimpleMessage {
    public void setMessage(String message) {
        this.message = message;
    }
    public void send() {
        System.out.println("Sending Email message..." + this.message);
    }
    
}
