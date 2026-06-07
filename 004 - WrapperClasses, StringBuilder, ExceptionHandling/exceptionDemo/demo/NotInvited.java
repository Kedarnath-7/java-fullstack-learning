package exceptionDemo.demo;

public class NotInvited extends RuntimeException{
    public NotInvited(String message){
        super(message);
    }
}
