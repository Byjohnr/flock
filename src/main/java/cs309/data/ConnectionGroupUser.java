package cs309.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by jeffrey on 2/9/16.
 */
@Entity
@Table(name = "connection_group_user")
public class ConnectionGroupUser {

    @Id
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "connection_group_id", referencedColumnName = "id")
    @JsonIgnore
    private ConnectionGroup connectionGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConnectionGroup getConnectionGroup() {
        return connectionGroup;
    }

    public void setConnectionGroup(ConnectionGroup connectionGroup) {
        this.connectionGroup = connectionGroup;
    }

    @Override
    public String toString() {
        return "ConnectionGroupUser{" +
                "id=" + id +
                ", user=" + user +
                ", connectionGroup=" + connectionGroup +
                '}';
    }
}
