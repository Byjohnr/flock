package cs309.dto;

import cs309.data.Event;
import cs309.data.User;

import java.util.List;

public class SearchDTO {

    private List<Event> events;

    private List<User> users;

    public SearchDTO(List<Event> events, List<User> users) {
        this.events = events;
        this.users = users;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<User> getUsers() {
        return users;
    }
}
