package cs309.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "chat_group_id", referencedColumnName = "id")
    private ChatGroup chatGroup;

    @ManyToOne
    @JoinColumn(name = "chat_user_id")
    private ChatUser messageSender;

    @Column(name = "message")
    private String message;

    @Column(name = "date_sent")
    private Date dateSent;

    public ChatMessage() {
        dateSent = new Date();
    }

    public ChatMessage(String message, ChatGroup chatGroup, ChatUser messageSender) {
        this.message = message;
        this.chatGroup = chatGroup;
        this.messageSender = messageSender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ChatGroup getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ChatGroup chatGroup) {
        this.chatGroup = chatGroup;
    }

    public ChatUser getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(ChatUser messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
}
