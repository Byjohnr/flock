package service;

import config.UnitTestBase;
import cs309.data.Role;
import cs309.repo.RoleRepository;
import cs309.service.RoleService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RoleServiceUTest extends UnitTestBase {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void createRole() {
        roleService.createRole("google@bing.com", Role.ADMIN,1);
        Mockito.verify(roleRepository, Mockito.times(1)).save(Mockito.any(Role.class));
    }

    @Test
    public void getRole() {
        roleService.getRole("chase@test.com", "ROLE_EVENT_ADMIN", 1);
        Mockito.verify(roleRepository, Mockito.times(1)).getRole("chase@test.com", "ROLE_EVENT_ADMIN", 1);
    }
}
