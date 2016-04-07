package cs309.service;

import cs309.data.Event;
import cs309.data.EventInvite;
import cs309.repo.*;
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
    ConnectionRepository connectionRepository;

    @Autowired
    RoleRepository roleRepository;

    public boolean isInvited(Integer id, Principal principal) {
        Event event = eventRepository.findOne(id);
        EventInvite invite = inviteRepository.findEventInviteByUserAndEvent(userRepository.findUserByEmail(principal.getName()), event);
        Boolean connected = connectionRepository.isConnected(event.getCreator(), userRepository.findUserByEmail(principal.getName()));
        LOG.info(invite);
        if (invite != null || event.getType() == event.OPEN) {
            return true;
        }
        if (event.getType() == 2 && connected == true) {
            return true;
        }
        return false;
    }

}
