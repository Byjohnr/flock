package service;

import config.UnitTestBase;
import cs309.data.User;
import cs309.repo.UserRepository;
import cs309.service.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by jeffrey on 2/6/16.
 */
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
}
