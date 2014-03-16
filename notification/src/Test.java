import notification.Notification;
import notification.NotificationCenter;
import notification.NotificationType;
import notification.Subscriber;

/**
 * Created by Maks on 16.03.14.
 */
public class Test {
    public static void main(String[] args) {
        final Subscriber vasia = new Subscriber("vasia");
        final Subscriber petia = new Subscriber("petia");
        final Subscriber ilonna = new Subscriber("ilonna");

        final NotificationCenter notificationCenter = new NotificationCenter();

        notificationCenter.subscribe(vasia, NotificationType.NOTIFICATION_FOOTBALL);
        notificationCenter.subscribe(vasia, NotificationType.NOTIFICATION_BASEBALL);
        notificationCenter.subscribe(petia, NotificationType.NOTIFICATION_FOOTBALL);
        notificationCenter.subscribe(ilonna, NotificationType.NOTIFICATION_BASEBALL);

        // both vasia and petia should receive notification
        final Notification footballNotification1 = new Notification("Chelsea lost 0:1 to AV on Saturday");
        notificationCenter.postNotification(footballNotification1);

        // only vasia should receive notification
        notificationCenter.unsubscribe(petia, NotificationType.NOTIFICATION_FOOTBALL);
        final Notification footballNotification2 = new Notification("MU is loosing 0:1 to Liverpool right now");
        notificationCenter.postNotification(footballNotification2);


        // let's subscribe alina on football events
        // now vasia and alina should get notified
        notificationCenter.subscribe(ilonna, NotificationType.NOTIFICATION_FOOTBALL);
        final Notification footballNotification3 = new Notification("Referee broke game for a half time");
        notificationCenter.postNotification(footballNotification3);


        // let's notify something different
        final Notification baseballNotification = new Notification(NotificationType.NOTIFICATION_BASEBALL, "Is someone interested???");
        notificationCenter.postNotification(baseballNotification);

        // not really
        notificationCenter.unsubscribeAll(NotificationType.NOTIFICATION_BASEBALL);
        final Notification baseballNotification1 = new Notification(NotificationType.NOTIFICATION_BASEBALL, "Not really???");
        notificationCenter.postNotification(baseballNotification1);





    }
}
