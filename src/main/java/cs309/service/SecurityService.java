package cs309.service;

import cs309.data.Event;
import cs309.data.EventInvite;
import cs309.data.Role;
import cs309.data.User;
import cs309.repo.EventInviteRepository;
import cs309.repo.EventRepository;
import cs309.repo.RoleRepository;
import cs309.repo.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
public class SecurityService {

    private Logger LOG = Logger.getLogger(SecurityService.class);

    @Autowired
    EventInviteRepository inviteRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public boolean isInvited(Integer id, Principal principal) {
        Event event = eventRepository.findOne(id);
        EventInvite invite = inviteRepository.findEventInviteByUserAndEvent(userRepository.findUserByEmail(principal.getName()), event);
        return invite != null;
    }

    public boolean isAdmin(Principal principal) {
        boolean returnedValue = false;
        if (principal != null) {
            List<Role> roles = roleRepository.findAll();
            for (Role role : roles) {
                if (StringUtils.equals(Role.ADMIN, role.getRoleName()) && StringUtils.equals(role.getEmail(), principal.getName())) {
                    returnedValue = true;
                }
            }
        }
        return returnedValue;
    }
}
