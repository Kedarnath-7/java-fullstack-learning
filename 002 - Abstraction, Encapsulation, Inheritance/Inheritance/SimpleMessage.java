package Inheritance;

public class SimpleMessage {

    protected String message;

    public void setMessage(String message){
        this.message = message;
    }
 
    public void send(){
        System.out.println("Sending simple message..." + message);
    }

}
