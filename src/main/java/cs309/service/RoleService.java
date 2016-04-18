package cs309.service;

import cs309.data.Role;
import cs309.data.User;
import cs309.repo.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void createRole(String email, String role, Integer id) {
        roleRepository.save(new Role(email,role,id));
    }

    @Transactional
    public Boolean updateRolePrivilages(String email, String roleName) {
        List<Role> roles = new LinkedList<>();
        Boolean toReturn = false;
        if (StringUtils.equalsIgnoreCase(Role.USER, roleName)) {
            roles = roleRepository.getRolesByEmail(email);
        } else if (StringUtils.equalsIgnoreCase(Role.ADMIN, roleName)) {
            roles = roleRepository.getRolesByEmail(email);
        }
        if (!roles.isEmpty()) {
            for (Role role : roles) {
                if (StringUtils.equalsIgnoreCase(Role.ADMIN, role.getRoleName()) || StringUtils.equalsIgnoreCase(Role.USER, role.getRoleName())) {
                    role.setRoleName(roleName);
                    roleRepository.save(role);
                    toReturn = true;
                }
            }
        }
        return toReturn;
    }

    public Role getRole(String email, String roleName, Integer id) {
        return roleRepository.getRole(email, roleName, id);
    }

    public boolean isAdmin(User user) {
        Role role = roleRepository.getAdmin(user.getEmail());
        return role != null;
    }
}
