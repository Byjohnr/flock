package cs309.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "chat_group")
public class ChatGroup {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "chatGroup")
    private List<ChatUser> chatUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "chatGroup")
    private List<ChatMessage> chatMessages;

    @ManyToOne
    @JoinColumn(name = "chat_admin_id", referencedColumnName = "id")
    private User chatAdmin;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "chat_name")
    private String chatName;

    public ChatGroup() {
        chatMessages = new ArrayList<>();
        chatUsers = new ArrayList<>();
        dateCreated = new Date();
    }

    public ChatGroup(String chatName, User chatAdmin) {
        chatMessages = new ArrayList<>();
        chatUsers = new ArrayList<>();
        this.chatName = chatName;
        this.chatAdmin = chatAdmin;
        this.dateCreated = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ChatUser> getChatUsers() {
        return chatUsers;
    }

    public void setChatUsers(List<ChatUser> chatUsers) {
        this.chatUsers = chatUsers;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public User getChatAdmin() {
        return chatAdmin;
    }

    public void setChatAdmin(User chatAdmin) {
        this.chatAdmin = chatAdmin;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}
