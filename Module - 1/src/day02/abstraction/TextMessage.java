package day02.abstraction;

class TextMessage extends SimpleMessage {
    public void setMessage(String message) {
        this.message = message;
    }
    public void send() {
        System.out.println("Sending Text message..." + this.message);
    }
}