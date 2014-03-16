package notification;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Maks on 16.03.14.
 */
public class NotificationCenter {

    private HashMap<String, ArrayList<Subscriber>> info = new HashMap<String, ArrayList<Subscriber>>();

    public void subscribe(Subscriber subscriber, NotificationType notificationType) {
        ArrayList<Subscriber> subscribers = getSubscribers(notificationType);
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    private ArrayList<Subscriber> getSubscribers(NotificationType notificationType) {
        final String name = notificationType.getName();
        ArrayList<Subscriber> subscribers = info.get(name);
        if (subscribers == null) {
            subscribers = new ArrayList<Subscriber>();
            info.put(name, subscribers);
        }
        return subscribers;
    }

    public void postNotification(Notification notification) {
        final String name = notification.getNotificationType().getName();
        final ArrayList<Subscriber> subscribers = info.get(name);
        if (!subscribers.isEmpty()) {
            for (Subscriber subscriber : subscribers) {
                subscriber.notifyNotification(notification);
            }
        } else {
            System.out.println("No subscribers interested on " + notification.getNotificationType());
        }


    }

    public void unsubscribe(Subscriber subscriber, NotificationType notificationType) {
        ArrayList<Subscriber> subscribers = getSubscribers(notificationType);
        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        }
    }

    public void unsubscribeAll(NotificationType notificationType) {
        ArrayList<Subscriber> subscribers = getSubscribers(notificationType);
        if (!subscribers.isEmpty()) {
            subscribers.clear();
        }
    }
}
