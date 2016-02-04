package util;

import cs309.data.Event;

import java.util.ArrayList;
import java.util.List;

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
}
