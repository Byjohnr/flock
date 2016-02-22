package util;

import cs309.data.Event;
import cs309.data.Notification;
import cs309.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by jeffrey on 2/2/16.
 */
public class MockData {

    public static List<Event> getMockEvents(int x) {
        List<Event> events = new ArrayList<>();
        for(int i = 0; i < x; i++) {
            events.add(getEvent(i));
        }
        return events;
    }

    public static Event getEvent(int x) {
        Event event = new Event();
        event.setId(x);
        event.setEventName("event" + x);
        return event;
    }

    public static List<Notification> getMockNotifications(int k) {
        List<Notification> notifications = new ArrayList<>();
        for(int i = 0; i < k; i++){
            notifications.add(getNotification(i));
        }
        return notifications;
    }

    public static Notification getNotification(int k) {
        Notification notification = new Notification();
        notification.setId(k);
        notification.setNotificationName("notification" + k);
        return notification;
    }

    public static User getUser(int k) {
        User user = new User();
        user.setId(k);
        user.setEmail("email" + k);
        user.setFirstName("first" + k);
        user.setLastName("last" + k);
        return user;
    }

    public static List<User> getUsers(int k) {
        List<User> mockUsers = new ArrayList<>();
        IntStream.range(0,k).forEach( i -> mockUsers.add(getUser(k)));
        return mockUsers;
    }
}
