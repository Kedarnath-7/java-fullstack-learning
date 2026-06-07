package Inheritance;

public class TextMessage extends SimpleMessage{
    public void send(){
        System.out.println("Sending text message: " + this.message);
    }
}
