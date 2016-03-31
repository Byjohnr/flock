package cs309.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "chat_invite")
public class ChatUser {

    private User userInvited;

    private ChatGroup chatGroup;

    private List<ChatMsg> chatMsgs;

    private Integer status;
}
