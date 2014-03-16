package notification;

/**
 * Created by Maks on 16.03.14.
 */
public class Subscriber {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void notifyNotification(Notification notification) {

        System.out.println();
        System.out.println("********************************");
        System.out.println(name + " received notification:");
        System.out.println(notification.toString());
        System.out.println("********************************");
        System.out.println();
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "name='" + name + '\'' +
                '}';
    }
}
