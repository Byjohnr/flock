package cs309.repo;

import cs309.data.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatUserRepository extends JpaRepository<ChatUser, Integer> {

    @Query("select c from ChatUser c where c.user.email = :email and c.chatGroup.id = :groupId")
    ChatUser getChatUserByUserEmailAndChatGroupId(@Param("email") String email, @Param("groupId") int groupId);

}
