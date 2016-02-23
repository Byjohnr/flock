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
        roleService.createRole("google@bing.com", Role.ADMIN);
        Mockito.verify(roleRepository, Mockito.times(1)).save(Mockito.any(Role.class));
    }
}
