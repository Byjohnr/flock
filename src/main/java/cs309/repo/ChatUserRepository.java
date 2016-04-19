package cs309.repo;

import cs309.data.ChatUser;
import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatUserRepository extends JpaRepository<ChatUser, Integer> {

    @Query("select c from ChatUser c where c.user.email = :email and c.chatGroup.id = :groupId")
    ChatUser getChatUserByUserEmailAndChatGroupId(@Param("email") String email, @Param("groupId") int groupId);

    @Query("select c.user from ChatUser c where c.chatGroup.id = :chatId")
    List<User> getChatUsersInGroup(@Param("chatId") int chatId);

}
