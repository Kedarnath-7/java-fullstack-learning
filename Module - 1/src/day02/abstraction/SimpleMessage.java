package day02.abstraction;

abstract class SimpleMessage {
    protected String message;
    public abstract void send();
    public abstract void setMessage(String message);
}