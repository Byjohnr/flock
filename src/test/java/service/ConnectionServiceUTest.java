package service;

import config.UnitTestBase;
import cs309.data.Connection;
import cs309.data.ConnectionRequest;
import cs309.data.User;
import cs309.repo.ConnectionRepository;
import cs309.repo.ConnectionRequestRepository;
import cs309.repo.EventRepository;
import cs309.service.ConnectionService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConnectionServiceUTest extends UnitTestBase {

    @Mock
    private ConnectionRepository connectionRepository;

    @Mock
    private ConnectionRequestRepository connectionRequestRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private ConnectionService connectionService;

    @Test
    public void getConnections() {
        when(connectionRepository.getConnections("jabba")).thenReturn(MockData.getUsers(3));
        List<User> users = connectionService.getConnections("jabba");
        assertTrue(users.size() == 3);
    }

    @Test
    public void hasRequested() {
        User user1 = MockData.getUser(2);
        User user2 = MockData.getUser(9);
        when(connectionRequestRepository.hasRequested(user1, user2)).thenReturn(true);
        boolean boo = connectionService.hasRequested(user1, user2);
        assertTrue(boo);
    }

    @Test
    public void isAlreadyConnected() {
        User user1 = MockData.getUser(3);
        User user2 = MockData.getUser(-42);
        when(connectionRepository.isConnected(user1, user2)).thenReturn(false);
        boolean boo = connectionService.isAlreadyConnected(user1, user2);
        assertFalse(boo);
    }

    @Test
    public void deleteConnection() {
        Connection connection = MockData.getConnection(1);
        connectionService.deleteConnection(connection);
        verify(connectionRepository,times(1)).delete(connection);
    }

    @Test
    public void deleteConnectionWithUsers() {
        User user1 = MockData.getUser(5);
        User user2 = MockData.getUser(8);
        connectionService.deleteConnection(user1, user2);
        verify(connectionRepository,times(1)).deleteConnection(user1, user2);

    }

    @Test
    public void deleteConnectionRequest() {
        User user1 = MockData.getUser(1);
        User user2 = MockData.getUser(2);
        connectionService.deleteConnectionRequest(user1, user2);
        verify(connectionRequestRepository, times(1)).deleteConnectionRequest(user1, user2);
    }

    @Test
    public void saveConnection() {
        Connection connection = MockData.getConnection(3);
        connectionService.saveConnection(connection);
        verify(connectionRepository, times(1)).save(connection);
    }

    @Test
    public void saveConnectionRequest() {
        ConnectionRequest connectionRequest = new ConnectionRequest();
        connectionService.saveConnectionRequest(connectionRequest);
        verify(connectionRequestRepository, times(1)).save(connectionRequest);
    }

    @Test
    public void getConnectionsNotInvtied() {
        when(connectionRepository.getConnections("name")).thenReturn(MockData.getUsers(3));
        when(eventRepository.findOne(1)).thenReturn(MockData.getEvent(1));
        List<User> users = connectionService.getConnectionsNotInvited("name", 1);
        verify(connectionRepository, times(1)).getConnections("name");
        verify(eventRepository, times(1)).findOne(1);
    }
}
