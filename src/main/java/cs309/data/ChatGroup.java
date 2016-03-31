package cs309.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "chat_group")
public class ChatGroup {

    private List<ChatUser> chatUsers;

    private List<ChatMsg> chatMsgs;

    private User chatAdmin;

    private Date dateCreated;

    private Date lastUpdated;

    private String chatName;

}
