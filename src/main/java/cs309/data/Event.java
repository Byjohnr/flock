package cs309.data;

import cs309.dto.CreateEventDTO;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    @OneToMany(mappedBy = "event")
    private List<EventInvite> eventInvites;

    @OneToMany (mappedBy = "event")
    private List<Comment> commentList;

    @Column(name = "location")
    private String location;

    @Column(name = "event_description")
    private String eventDescription;

//    TODO jeffreyh 1-28-16, wait for image upload implementation

    public Event() {

    }

    public Event(CreateEventDTO eventDTO, User user) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy HH:mm a");
//        TODO jeffreyh 2-6-16, need to set up spring security before we can grab user from the session
//        this.creator = new User();
        this.eventStart = eventDTO.getStartDate() == null || eventDTO.getStartDate().equals(" ") ? null : dateFormat.parse(eventDTO.getStartDate());
        this.eventEnd = eventDTO.getEndDate() == null || eventDTO.getEndDate().equals(" ") ? null : dateFormat.parse(eventDTO.getEndDate());
        this.eventDescription = eventDTO.getDescription();
        this.location = eventDTO.getAddress();
        this.eventName = eventDTO.getEventName();
        this.type = eventDTO.getType();
        this.creator = user;
    }



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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<EventInvite> getEventInvites() {
        return eventInvites;
    }

    public void setEventInvites(List<EventInvite> eventInvites) {
        this.eventInvites = eventInvites;
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

