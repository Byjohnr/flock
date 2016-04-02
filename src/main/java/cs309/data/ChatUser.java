package cs309.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat_group_user")
public class ChatUser {

    public static final int STATUS_INVITED = 1;
    public static final int STATUS_ACCEPTED = 1;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_group_id", referencedColumnName = "id")
    private ChatGroup chatGroup;

    @OneToMany(mappedBy = "messageSender")
    private List<ChatMessage> chatMessages;

    @Column(name = "status")
    private Integer status;

    public ChatUser() {

    }

    public ChatUser(ChatGroup chatGroup, Integer status, User user) {
        this.chatGroup = chatGroup;
        this.status = status;
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

    public ChatGroup getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ChatGroup chatGroup) {
        this.chatGroup = chatGroup;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
