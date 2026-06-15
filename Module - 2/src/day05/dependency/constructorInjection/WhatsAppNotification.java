package day05.dependency.constructorInjection;

public class WhatsAppNotification implements NotificationService{
    @Override
    public void sendMessage(String message) {
        System.out.println("WhatsApp Notification Sending Message");
    }
}
