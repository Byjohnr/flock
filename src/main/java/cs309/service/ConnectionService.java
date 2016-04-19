package cs309.service;

import cs309.data.*;
import cs309.data.*;
import cs309.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ConnectionRequestRepository connectionRequestRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ChatUserRepository chatUserRepository;
    
    @Autowired
    private ConnectionGroupRepository connectionGroupRepository;

    @Autowired
    private ConnectionGroupUserRepository connectionGroupUserRepository;

    public List<User> getConnectionsByEmail(String email) {
        return connectionRepository.getConnectionsByEmail(email);
    }

    public List<User> getConnectionsNotInvited(String email, Integer eventId) {
        List<User> list = connectionRepository.getConnectionsByEmail(email);
        Event event = eventRepository.findOne(eventId);
        for(EventInvite invite : event.getEventInvites()) {
            if (list.contains(invite.getUserInvited())) {
                list.remove(invite.getUserInvited());
            }
        }
        if (list.contains(event.getCreator())) {
            list.remove(event.getCreator());
        }
        return list;
    }
    /**
     * Checks if user1 has requested to be connected to user2
     */
    public boolean hasRequested(User user1, User user2) {
        return connectionRequestRepository.hasRequested(user1, user2);
    }

    /**
     * Checks if user1 and user2 are already connected
     */
    public boolean isAlreadyConnected(User user1, User user2) {
        return connectionRepository.isConnected(user1,user2);
    }

    @Transactional
    public void deleteConnection(Connection connection) {
        connectionRepository.delete(connection);
    }

    @Transactional
    public void deleteConnection(User user1, User user2) {
        connectionRepository.deleteConnection(user1, user2);
    }

    @Transactional
    public void deleteConnectionRequest(User receiver, User sender) {
        connectionRequestRepository.deleteConnectionRequest(receiver,sender);
    }

    @Transactional
    public void saveConnection(Connection connection) {
        connectionRepository.save(connection);
    }

    @Transactional
    public void saveConnectionRequest(ConnectionRequest connectionRequest) {
        connectionRequestRepository.save(connectionRequest);
    }

    public List<ConnectionGroup> getConnectionGroupByEmail(String email) {
        return connectionGroupRepository.getConnectionGroupsByEmail(email);
    }

    @Transactional
    public void saveConnectionGroup(ConnectionGroup connectionGroup) {
        connectionGroupRepository.save(connectionGroup);
    }

    public ConnectionGroup getConnectionGroupById(int id) {
        return connectionGroupRepository.findOne(id);
    }

    @Transactional
    public void deleteConnectionGroup(ConnectionGroup connectionGroup) {
        connectionGroupRepository.delete(connectionGroup);
    }

    public List<User> getConnectionsInConnectionGroupByGroupId(int groupId) {
        return connectionGroupUserRepository.getUsersInConnectionGroup(groupId);
    }

    public List<User> getConnectionsNotInGroupByGroupIdAndEmail(int groupId, String email) {
        List<User> connectionGroupUsers = connectionGroupUserRepository.getUsersInConnectionGroup(groupId);
        List<User> connections = connectionRepository.getConnectionsByEmail(email);
        return connections.stream().filter(user -> !connectionGroupUsers.contains(user)).collect(Collectors.toList());
    }

    @Transactional
    public void saveConnectionGroupUser(ConnectionGroupUser connectionGroupUser) {
        connectionGroupUserRepository.save(connectionGroupUser);
    }

    @Transactional
    public void deleteConnectionGroupUser(ConnectionGroupUser connectionGroupUser) {
        connectionGroupUserRepository.delete(connectionGroupUser);
    }

    public ConnectionGroupUser getConnectionGroupUserByUserIdAndGroupId(int userId, int groupId) {
        return connectionGroupUserRepository.getConnectionGroupUserByUserIdAndGroupId(userId, groupId);
    }

    public List<User> getConnectionsNotInvitedToChat(String email, int chatId) {

        List<User> connections = connectionRepository.getConnectionsByEmail(email);
        List<User> chatUsers = chatUserRepository.getChatUsersInGroup(chatId);
        return connections.stream().filter(user -> !chatUsers.contains(user)).collect(Collectors.toList());

    }
}
