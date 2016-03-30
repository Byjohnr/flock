package service;


import config.UnitTestBase;
import cs309.data.Event;
import cs309.data.EventInvite;
import cs309.data.User;
import cs309.repo.EventInviteRepository;
import cs309.service.EventInviteService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

public class EventInviteServiceUTest extends UnitTestBase {

    @Mock
    private EventInviteRepository eventInviteRepository;

    @InjectMocks
    private EventInviteService eventInviteService;

    @Test
    public void saveEventInvite() {
        EventInvite eventInvite = mock(EventInvite.class);
        eventInviteService.saveEventInvite(eventInvite);
        verify(eventInviteRepository, times(1)).save(eventInvite);
    }

    public void getEventInvite() {
        when(eventInviteRepository.findEventInviteByUserAndEvent(mock(User.class), mock(Event.class))).thenReturn(MockData.getInvite(1));
        EventInvite invite = eventInviteService.getEventInvite(mock(User.class), mock(Event.class));
        assertNotNull(invite);
    }

    @Test
    public void eventInvteExists() {
        when(eventInviteRepository.userInviteExists(1,1234)).thenReturn(true);
        boolean boo = eventInviteService.eventInviteExists(1,1234);
        assertTrue(boo);
    }

}
