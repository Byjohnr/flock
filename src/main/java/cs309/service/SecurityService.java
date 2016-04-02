package cs309.service;

import cs309.data.Event;
import cs309.data.EventInvite;
import cs309.data.User;
import cs309.repo.EventInviteRepository;
import cs309.repo.EventRepository;
import cs309.repo.RoleRepository;
import cs309.repo.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;


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

//    public boolean isAdmin(Principal principal) {
//        boolean returnedValue = false;
//        if (principal != null) {
//            User principalUser = userRepository.findUserByEmail(principal.getName());
//            EventInvite invite = roleRepository.findAll(userRepository.findUserByEmail(principal.getName())., principalUser);
//        }
//        return returnedValue;
//    }
}
