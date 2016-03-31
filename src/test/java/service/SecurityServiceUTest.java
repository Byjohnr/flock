package service;

import config.UnitTestBase;
import cs309.data.Event;
import cs309.data.User;
import cs309.repo.EventInviteRepository;
import cs309.repo.EventRepository;
import cs309.service.SecurityService;
import org.junit.Test;
import org.mockito.Mock;
import util.MockData;

import java.security.Principal;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class SecurityServiceUTest extends UnitTestBase {

    @Mock
    EventInviteRepository inviteRepository;

    @Mock
    EventRepository eventRepository;

    @Mock
    SecurityService securityService;

    @Test
    public void isInvited() {
        Principal principal = mock(Principal.class);
        when(eventRepository.findOne(1)).thenReturn(MockData.getEvent(1));
        when(inviteRepository.findEventInviteByUserAndEvent(any(User.class), any(Event.class))).thenReturn(MockData.getInvite(1));
        assertEquals(securityService.isInvited(1,principal), false);
    }

}
