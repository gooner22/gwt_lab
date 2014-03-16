package notification;

/**
 * Created by Maks on 16.03.14.
 */
public class Notification {
    private NotificationType notificationType;
    private String message;

    public Notification(NotificationType notificationType, String message) {
        this.notificationType = notificationType;
        this.message = message;
    }

    public Notification(String message) {
        this.notificationType = NotificationType.NOTIFICATION_FOOTBALL;
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationType=" + notificationType +
                ", message='" + message + '\'' +
                '}';
    }
}
