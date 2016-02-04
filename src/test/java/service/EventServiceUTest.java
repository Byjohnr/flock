package service;

import config.UnitTestBase;
import cs309.data.Event;
import cs309.repo.EventRepository;
import cs309.service.EventService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by jeffrey on 2/2/16.
 */
public class EventServiceUTest extends UnitTestBase {

    @Mock
    private EventRepository eventRepo;

    @InjectMocks
    private EventService eventService;

    @Test
    public void getEvents() {
        when(eventRepo.findAll()).thenReturn(new ArrayList<>(MockData.getMockEvents(5)));
        List<Event> events = eventService.getEvents();
        assertEquals(events.size(),5);
    }
}
