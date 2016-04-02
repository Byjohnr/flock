package cs309.data;

import cs309.dto.NotificationDTO;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        return new EqualsBuilder()
                .append(receiver, that.receiver)
                .append(creator, that.creator)
                .append(type, that.type)
                .append(type_id, that.type_id)
                .append(dateCreated, that.dateCreated)
                .append(notificationName, that.notificationName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(receiver)
                .append(creator)
                .append(type)
                .append(type_id)
                .append(dateCreated)
                .append(notificationName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", creator=" + creator +
                ", type=" + type +
                ", type_id=" + type_id +
                ", dateCreated=" + dateCreated +
                ", notificationName='" + notificationName + '\'' +
                '}';
    }
}
