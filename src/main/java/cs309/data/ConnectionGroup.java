package cs309.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "connection_group")
public class ConnectionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "connectionGroup")
    private List<ConnectionGroupUser> groupUsers = new ArrayList<ConnectionGroupUser>();

    public ConnectionGroup() {

    }

    public ConnectionGroup(String groupName) {
        this.groupName = groupName;
    }

    public ConnectionGroup(String groupName, User user) {
        this.groupName = groupName;
        this.user = user;
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

    public List<ConnectionGroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<ConnectionGroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }

    @Override
    public String toString() {
        return "ConnectionGroup{" +
                "groupName='" + groupName + '\'' +
                '}';
    }
}
