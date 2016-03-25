package cs309.data;

import cs309.dto.NotificationDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.jar.Attributes;


@Entity
@Table(name = "Notification")
public class Notification {

    public static final Integer EVENT_INVITE = 1;
    public static final Integer USER_CONNECTION = 2;

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @Column(name = "type")
    private Integer type;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "time")
    private Date dateCreated;

    @Column(name ="notification_name")
    private String notificationName;

    public Notification() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;

    }
}
