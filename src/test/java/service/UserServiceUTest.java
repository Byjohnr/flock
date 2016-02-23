package service;

import config.UnitTestBase;
import cs309.data.User;
import cs309.repo.UserRepository;
import cs309.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        verify(userRepository, times(1)).findOne(3);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setFirstName("First");
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void getUsersByCurrentCity() {
        String currentCity = "Ames";
        List<User> users = new LinkedList<>();
        User user1 = new User();
        user1.setFirstName("First1");
        user1.setLastName("Last1");
        user1.setCurrentCity(currentCity);
        users.add(user1);
        User user2 = new User();
        user2.setFirstName("First2");
        user2.setLastName("Last2");
        user2.setCurrentCity(currentCity);
        users.add(user2);
        when(userRepository.findByCurrentCity(anyString())).thenReturn(users);

        List<User> methodReturn = userService.getUsersByCurrentCity(currentCity);

        assertEquals(users, methodReturn);
        verify(userRepository, times(1)).findByCurrentCity(currentCity);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void getUserByEmail() {
        User user = new User();
        user.setFirstName("First");
        user.setLastName("Last");
        user.setEmail("totallyLegit@email.com");
        when(userRepository.findUserByEmail(anyString())).thenReturn(user);

        User methodReturn = userRepository.findUserByEmail("totallyLegit@email.com");

        assertEquals(user, methodReturn);
        verify(userRepository, times(1)).findUserByEmail("totallyLegit@email.com");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void getUsers() {
        when(userRepository.findAll()).thenReturn(MockData.getUsers(5));
        List<User> users = userService.getUsers();
        assertEquals(5, users.size());
    }

    @After
    public void resetMocks() {
        reset(userRepository);
    }
}
