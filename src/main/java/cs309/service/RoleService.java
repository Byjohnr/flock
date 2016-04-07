package cs309.service;

import cs309.data.Role;
import cs309.data.User;
import cs309.repo.RoleRepository;
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

    public Role getRole(String email, String roleName, Integer id) {
        return roleRepository.getRole(email, roleName, id);
    }

    public boolean isAdmin(User user) {
        Role role = roleRepository.getAdmin(user.getEmail());
        return role != null;
    }
}
