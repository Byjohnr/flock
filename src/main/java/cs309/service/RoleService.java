package cs309.service;

import cs309.data.Role;
import cs309.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void createRole(String email, String role) {
        roleRepository.save(new Role(email,role));
    }
}
