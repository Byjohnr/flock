package cs309.data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by jeffrey on 1/28/16.
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_start")
    private Date eventStart;

    @Column(name = "event_end")
    private Date eventEnd;

    @Column(name = "type")
    private Integer type;
//    TODO jeffreyh 1-28-16 wait for tags to be implemented
//    private List<Tags> tagsList
//    TODO jeffreyh 1-28-16 wait for users to be implemented
//    private List<Invites> userList
//    TODO jeffreyh 1-28-16 wait for comments to be implemented
//    private List<Comment> commentList;

    @Column(name = "location")
    private String location;

    @Column(name = "event_description")
    private String eventDescription;

//    TODO jeffreyh 1-28-16, wait for image upload implementation


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", creator=" + creator +
                ", eventName='" + eventName + '\'' +
                ", type=" + type +
                ", location='" + location + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
}

