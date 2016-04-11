package cs309.service;

import cs309.data.Role;
import cs309.data.User;
import cs309.repo.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void createRole(String email, String role, Integer id) {
        roleRepository.save(new Role(email,role,id));
    }

    @Transactional
    public Role updateRolePrivilages(String email, String roleName) {
        Role role = null;
        if (StringUtils.equalsIgnoreCase(Role.USER, roleName)) {
            role = roleRepository.getRole(email, Role.ADMIN, null);
        } else if (StringUtils.equalsIgnoreCase(Role.ADMIN, roleName)) {
            role = roleRepository.getRole(email, Role.USER, null);
        }
        if (role != null) {
            role.setRoleName(roleName);
            roleRepository.save(role);
        }
        return role;
    }

    public Role getRole(String email, String roleName, Integer id) {
        Role returnedRole = roleRepository.getRole(email, roleName, id);
        return returnedRole;
    }

    public boolean isAdmin(User user) {
        Role role = roleRepository.getAdmin(user.getEmail());
        return role != null;
    }
}
