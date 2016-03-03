package cs309.data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "connection_request")
public class ConnectionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public ConnectionRequest(User userSending, User userReceiving) {
        this.userReceiving = userReceiving;
        this.userSending = userSending;
        this.date = new Date();
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
