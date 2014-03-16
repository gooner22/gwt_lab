package notification;

/**
 * Created by Maks on 16.03.14.
 */
public enum NotificationType {
    NOTIFICATION_FOOTBALL("simple"), NOTIFICATION_BASEBALL("complicated");

    private String name;
    private NotificationType(String aName){
        name = aName;
    }

    public String getName() {
        return name;
    }
}
