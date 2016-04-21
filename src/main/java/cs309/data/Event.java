package cs309.data;

import cs309.dto.CreateEventDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    public static final int OPEN = 1;
    public static final int CONNECTIONS_ONLY = 2;
    public static final int INVITE_ONLY = 3;

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

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

//    TODO jeffreyh 1-28-16, wait for image upload implementation

    public Event() {

    }

    public Event(CreateEventDTO eventDTO, User user) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy HH:mm a");
        this.eventStart = eventDTO.getStartDate() == null || eventDTO.getStartDate().equals(" ") ? null : dateFormat.parse(eventDTO.getStartDate());
        this.eventEnd = eventDTO.getEndDate() == null || eventDTO.getEndDate().equals(" ") ? null : dateFormat.parse(eventDTO.getEndDate());
        this.eventDescription = eventDTO.getDescription();
        this.location = eventDTO.getAddress();
        this.eventName = eventDTO.getEventName();
        this.type = eventDTO.getType();
        this.creator = user;
        this.longitude = eventDTO.getLongitude();
        this.latitude = eventDTO.getLatitude();
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return new EqualsBuilder()
                .append(creator, event.creator)
                .append(eventName, event.eventName)
                .append(eventStart, event.eventStart)
                .append(eventEnd, event.eventEnd)
                .append(type, event.type)
                .append(eventInvites, event.eventInvites)
                .append(commentList, event.commentList)
                .append(location, event.location)
                .append(eventDescription, event.eventDescription)
                .append(eventInvites, event.eventInvites)
                .append(longitude, event.longitude)
                .append(latitude, event.latitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(creator)
                .append(eventName)
                .append(eventStart)
                .append(eventEnd)
                .append(type)
                .append(eventInvites)
                .append(commentList)
                .append(location)
                .append(eventDescription)
                .append(eventInvites)
                .append(longitude)
                .append(latitude)
                .toHashCode();
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

