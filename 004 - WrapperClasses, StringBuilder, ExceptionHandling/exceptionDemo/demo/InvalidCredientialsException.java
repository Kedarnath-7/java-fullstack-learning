package exceptionDemo.demo;

public class InvalidCredientialsException extends RuntimeException {
    public InvalidCredientialsException(String message) {
        super(message);
    }
}
