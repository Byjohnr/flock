package cs309.service;

import cs309.data.Role;
import cs309.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void createRole(String email, String role) {
        roleRepository.save(new Role(email,role));
    }
}
