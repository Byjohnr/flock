package util;

import cs309.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


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
        IntStream.range(0,k).forEach(i -> mockUsers.add(getUser(k)));
        return mockUsers;
    }

    public static Connection getConnection(int k) {
        Connection connection = new Connection();
        connection.setId(k);
        connection.setUser1(getUser(k));
        connection.setUser2(getUser(k + 1));
        return connection;
    }

    public static List<Connection> getConnections(int k) {
        List<Connection> mockConnections = new ArrayList<>();
        IntStream.range(0,k).forEach(i -> mockConnections.add(getConnection(k)));
        return mockConnections;
    }

    public static Comment getComment(int k) {
        Comment comment = new Comment();
        comment.setId(k);
        comment.setComment("Hey");
        return  comment;
    }
}
