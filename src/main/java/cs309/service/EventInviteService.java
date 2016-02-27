package cs309.service;

import cs309.data.EventInvite;
import cs309.repo.EventInviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventInviteService {

    @Autowired
    private EventInviteRepository eventInviteRepository;


    public void saveEventInvite(EventInvite eventInvite) {
        eventInviteRepository.save(eventInvite);
    }

}
