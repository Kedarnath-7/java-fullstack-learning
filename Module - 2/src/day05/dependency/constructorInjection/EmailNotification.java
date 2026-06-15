package day05.dependency.constructorInjection;

public class EmailNotification implements NotificationService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Email Notification Sending Message");
    }
}
