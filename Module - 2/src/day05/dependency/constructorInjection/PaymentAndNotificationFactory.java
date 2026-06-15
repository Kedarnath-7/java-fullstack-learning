package day05.dependency.constructorInjection;

public class PaymentAndNotificationFactory {
    private static CreditCard creditCard=new CreditCard();
    private static DebitCard debitCard=new DebitCard();
    private static Upi upi=new Upi();
    private static EmailNotification emailNotification=new EmailNotification();
    private static WhatsAppNotification whatsAppNotification=new WhatsAppNotification();
    public static PaymentService getPaymentService(int paymentType){
        return switch (paymentType) {
            case 1 -> creditCard;
            case 2 -> debitCard;
            case 3 -> upi;
            default -> null;
        };
    }
    public static NotificationService getNotificationService(String notificationType){
        if(notificationType.equalsIgnoreCase("email")){
            return emailNotification;
        }else if(notificationType.equalsIgnoreCase("whatsapp")){
            return whatsAppNotification;
        }
        return null;
    }
}
