package cs309.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "connection_request")
public class ConnectionRequest {

    @Id
    @Column(name = "id")
    public Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public User userReceiving;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public User userSending;

    @Column(name = "date_sent")
    private Date date;

    public ConnectionRequest() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserReceiving() {
        return userReceiving;
    }

    public void setUserReceiving(User userReceiving) {
        this.userReceiving = userReceiving;
    }

    public User getUserSending() {
        return userSending;
    }

    public void setUserSending(User userSending) {
        this.userSending = userSending;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
