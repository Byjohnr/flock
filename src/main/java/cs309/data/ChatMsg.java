package cs309.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "chat_message")
public class ChatMsg {

    private User messageSender;

    private ChatGroup chatGroup;

    private ChatUser chatUser;

    private String message;

    private Date dateSent;

}
