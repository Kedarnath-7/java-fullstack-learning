package day02.inheritance;

public class Whatsapp extends SimpleMessage{
    public void send(){
        System.out.println("Sending whatsapp message: " + this.message);
    }
}
