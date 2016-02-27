package cs309.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event_invite")
public class EventInvite {

    public static final Integer EVENT_INVITE_ROLE_CREATOR = 1;
    public static final Integer EVENT_INVITE_ROLE_ADMIN = 2;
    public static final Integer EVENT_INVITE_ROLE_INVITEE = 3;

    public static final Integer INVITED = 0;
    public static final Integer GOING = 1;
    public static final Integer UNDECIDED = 2;
    public static final Integer NOT_GOING = 3;



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

    @Column(name = "invite_status")
    private Integer inviteStatus;

    public EventInvite(User inviter, User userInvited, Event event) {
        this.inviter = inviter;
        this.userInvited = userInvited;
        this.event = event;
        this.dateInvited = new Date();
        this.inviteStatus = INVITED;
    }

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
