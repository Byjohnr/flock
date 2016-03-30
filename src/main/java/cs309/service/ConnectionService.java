package cs309.service;

import cs309.data.*;
import cs309.repo.ConnectionRepository;
import cs309.repo.ConnectionRequestRepository;
import cs309.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ConnectionRequestRepository connectionRequestRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<User> getConnections(String email) {
        return connectionRepository.getConnections(email);
    }

    public List<User> getConnectionsNotInvited(String email, Integer eventId) {
        List<User> list = connectionRepository.getConnections(email);
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
}
