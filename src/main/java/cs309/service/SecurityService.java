package cs309.service;

import cs309.data.Connection;
import cs309.data.Event;
import cs309.data.EventInvite;
import cs309.repo.ConnectionRepository;
import cs309.repo.EventInviteRepository;
import cs309.repo.EventRepository;
import cs309.repo.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Created by chasekoehler on 3/23/16.
 */

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
