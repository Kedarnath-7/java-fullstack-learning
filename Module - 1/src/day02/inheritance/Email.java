package day02.inheritance;

public class Email extends SimpleMessage{
    public void send(){
        System.out.println("Sending email message: "+ this.message);
    }
}
