package service;

import config.UnitTestBase;
import cs309.data.User;
import cs309.repo.UserRepository;
import cs309.service.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceUTest extends UnitTestBase {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void getUser() {
        when(userRepository.findOne(3)).thenReturn(mock(User.class));
        User user = userService.getUser(3);
        assertNotNull(user);
    }

    @Test
    public void saveUser() {
        User user = new User();
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getUsers() {
        when(userRepository.findAll()).thenReturn(MockData.getUsers(5));
        List<User> users = userService.getUsers();
        assertEquals(5, users.size());
    }

    @Test
    public void getUserByEmail() {
        when(userRepository.getUserByEmail("test@email.com")).thenReturn(MockData.getUser(3));
        User user = userService.getUserByEmail("test@email.com");
        assertNotNull(user);
    }
}
