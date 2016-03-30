package service;


import config.UnitTestBase;
import cs309.data.EventInvite;
import cs309.repo.EventInviteRepository;
import cs309.service.EventInviteService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

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

    @Test
    public void eventInvteExists() {
        when(eventInviteRepository.userInviteExists(1,1234)).thenReturn(true);
        boolean boo = eventInviteService.eventInviteExists(1,1234);
        assertTrue(boo);
    }

}
