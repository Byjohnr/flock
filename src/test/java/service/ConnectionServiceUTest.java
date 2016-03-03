package service;

import config.UnitTestBase;
import cs309.data.User;
import cs309.repo.ConnectionRepository;
import cs309.service.ConnectionService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ConnectionServiceUTest extends UnitTestBase {

    @Mock
    private ConnectionRepository connectionRepository;

    @InjectMocks
    private ConnectionService connectionService;

    @Test
    public void getConnections() {
        when(connectionRepository.getConnections("jabba")).thenReturn(MockData.getUsers(3));
        List<User> users = connectionService.getConnections("jabba");
        assertTrue(users.size() == 3);
    }
}
