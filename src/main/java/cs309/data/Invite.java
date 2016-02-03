package cs309.data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jeffrey on 1/29/16.
 */
@Entity
@Table(name = "invite")
public class Invite {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "inviter_id", referencedColumnName = "id")
    private User inviter;

    @ManyToOne
    @JoinColumn(name = "invited_user_id", referencedColumnName = "id")
    private User userInvited;

    @Column(name = "date_invited")
    private Date dateInvited;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @JoinColumn(name = "invite_status")
    private Integer inviteStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public User getUserInvited() {
        return userInvited;
    }

    public void setUserInvited(User userInvited) {
        this.userInvited = userInvited;
    }

    public Date getDateInvited() {
        return dateInvited;
    }

    public void setDateInvited(Date dateInvited) {
        this.dateInvited = dateInvited;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(Integer inviteStatus) {
        this.inviteStatus = inviteStatus;
    }
}
