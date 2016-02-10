package cs309.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffrey on 2/8/16.
 */
@Entity
@Table(name = "connection_group")
public class ConnectionGroup {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "connection_group_user",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "connection_group_id", referencedColumnName = "id")})
    private List<User> groupUsers = new ArrayList<User>();

    public ConnectionGroup() {

    }

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<User> groupUsers) {
        this.groupUsers = groupUsers;
    }
}
