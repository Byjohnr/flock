package cs309.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "description")
    private String description;
    @Column(name = "password")
    private String password;
    //TODO tstack 2/4/16 figure out how images should be stored in the database
//    private ImageIO profilePicture;
    @Column(name = "current_city")
    private String currentCity;
    //TODO tstack 2/4/16 implement saving of any further location information
//    private Long cityXCoordinate;
//    private Long cityYCoordinate;
//    @OneToMany
//    private List<Connection> connections;
//    @OneToMany
//    private List<EventInvite> events;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ConnectionGroup> connectionGroups = new ArrayList<ConnectionGroup>();

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    //    public List<Connection> getConnections() {
//        return connections;
//    }
//
//    public void setConnections(List<Connection> connections) {
//        this.connections = connections;
//    }
//
//    public List<EventInvite> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<EventInvite> events) {
//        this.events = events;
//    }
//


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ConnectionGroup> getConnectionGroups() {
        return connectionGroups;
    }

    public void setConnectionGroups(List<ConnectionGroup> connectionGroups) {
        this.connectionGroups = connectionGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(email, user.email)
                .append(description, user.description)
                .append(currentCity, user.currentCity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(description)
                .append(currentCity)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", currentCity='" + currentCity + '\'' +
                '}';
    }
}