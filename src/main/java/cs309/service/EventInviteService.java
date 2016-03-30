package cs309.service;

import cs309.data.EventInvite;
import cs309.repo.EventInviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventInviteService {

    @Autowired
    private EventInviteRepository eventInviteRepository;


    @Transactional
    public void saveEventInvite(EventInvite eventInvite) {
        eventInviteRepository.save(eventInvite);
    }

    public boolean eventInviteExists(int eventId, int userId) {
        return eventInviteRepository.userInviteExists(eventId, userId);
    }

}
