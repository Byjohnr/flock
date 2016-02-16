package cs309.dto;

import cs309.data.Event;
import cs309.data.User;

import java.text.SimpleDateFormat;

/**
 * Created by jeffrey on 1/30/16.
 */
public class EventDTO {

    private Integer eventId;
    private String name;
    private String startTime;
    private String endTime;
    private String type;
    private User creator;
    private String description;
    private String address;
    private int numberOfComments;
    private int attending;
    private int maybeAttending;
    private int notAttending;

    public EventDTO(){}

    public EventDTO(Event event) {
        eventId = event.getId();
        name = event.getEventName();
//        TODO jeffreyh 1/30/16 set type when we figure out diffrent types of event (open,invite,connections, etc)
        type = "Open";
        creator = event.getCreator();
        description = event.getEventDescription();
        address = event.getLocation();
//        TODO jeffreyh 1/30/16 need comments, attending, maybeAttending, and notAttending, for now use static values
        numberOfComments = 0;
        attending = 0;
        maybeAttending = 0;
        notAttending = 0;
        SimpleDateFormat format = new SimpleDateFormat("EEEE MMMM dd  h:mm a");
        if(event.getEventStart() != null) {
            startTime = format.format(event.getEventStart());
        } else {
            startTime = "";
        }
        if(event.getEventEnd() != null) {
            endTime = format.format(event.getEventStart());
        } else {
            endTime = "";
        }
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public int getAttending() {
        return attending;
    }

    public void setAttending(int attending) {
        this.attending = attending;
    }

    public int getMaybeAttending() {
        return maybeAttending;
    }

    public void setMaybeAttending(int maybeAttending) {
        this.maybeAttending = maybeAttending;
    }

    public int getNotAttending() {
        return notAttending;
    }

    public void setNotAttending(int notAttending) {
        this.notAttending = notAttending;
    }
}
