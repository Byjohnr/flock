//package cs309.util;
//
//import cs309.data.ChatMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.core.annotation.HandleAfterCreate;
//import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
//import org.springframework.hateoas.EntityLinks;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@RepositoryEventHandler(ChatMessage.class)
//public class ChatHandler {
//
//    static final String MESSAGE_PREFIX = "/topic";
//
//    @Autowired
//    private SimpMessagingTemplate websocket;
//
//    @Autowired
//    private EntityLinks entityLinks;
//
//
//    @HandleAfterCreate
//    public void newChatMessage(ChatMessage chatMessage) {
//        this.websocket.convertAndSend(MESSAGE_PREFIX + "/createMessage", getPath(chatMessage));
//    }
//
//    private String getPath(ChatMessage chatMessage) {
//        return this.entityLinks.linkForSingleResource(chatMessage.getClass(),chatMessage.getId()).toUri().getPath();
//    }
//
//}
