package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.UnitTestBase;
import cs309.controller.EventRestController;
import cs309.data.*;
import cs309.dto.CreateEventDTO;
import cs309.service.*;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.MockData;

import java.security.Principal;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class EventRestControllerUTest extends UnitTestBase {
    private MockMvc mockMvc;

    @Mock
    private EventInviteService eventInviteService;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @InjectMocks
    private EventRestController eventController;

    @Mock
    private CommentService commentService;

    @Mock
    private RoleService roleService;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(eventController).build();
    }

    @Test
    public void getEvent() throws Exception {
        when(eventService.getEvent(1)).thenReturn(MockData.getEvent(1));
        this.mockMvc.perform(get("/api/event/1"))
                .andExpect(status().isOk());
                Event event = eventService.getEvent(1);
                assertEquals(event.getEventName(), "event1");
    }

    @Test
    public void getEvents() throws Exception {
        Principal principal = mock(Principal.class);
        User user = new User();
        user.setEvents(MockData.getEventInvites(4));
        when(userService.getUserByEmail(principal.getName())).thenReturn(user);

        this.mockMvc.perform(get("/api/events").accept(MediaType.APPLICATION_JSON).principal(principal))

                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", Matchers.hasSize(4)));
    }

    @Test
    public void createEvent() throws Exception {
        Principal principal = mock(Principal.class);
        ObjectMapper mapper = new ObjectMapper();
        when(eventService.saveEvent(any(Event.class))).thenReturn(MockData.getEvent(1));
        CreateEventDTO eventDTO = new CreateEventDTO();
        String string = mapper.writeValueAsString(eventDTO);
        when(userService.getUser(1)).thenReturn(mock(User.class));
        this.mockMvc.perform(post("/api/create")
                .principal(principal)
                .content(string)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(eventService, times(1)).saveEvent(any(Event.class));
    }

    @Test
    public void editEvent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        when(eventService.saveEvent(any(Event.class))).thenReturn(MockData.getEvent(1));
        Event event = new Event();
        String string = mapper.writeValueAsString(event);
        this.mockMvc.perform(post("/api/event/1")
                .content(string)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(eventService, times(1)).saveEvent(any(Event.class));
    }

    @Test
    public void addInvites() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String ids = mapper.writeValueAsString(new int[]{1,2,3});

        mockMvc.perform(post("/api/event/1/invites").principal(mock(Principal.class))
        .content(ids)
        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(eventInviteService, times(3)).saveEventInvite(any(EventInvite.class));
    }

    @Test
    public void getAttending() throws Exception {
        Principal principal = mock(Principal.class);
        when(eventInviteService.getEventInvite(any(User.class), any(Event.class))).thenReturn(MockData.getInvite(1));
        mockMvc.perform(get("/api/event/getAttending/1")
                .principal(principal))
                .andExpect(status().isOk());
                EventInvite invite = eventInviteService.getEventInvite(userService.getUserByEmail(principal.getName()), eventService.getEvent(1));
                assertEquals(invite.getInviteStatus().toString(), "1");

    }

    @Test
    public void setAttending() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Principal principal = mock(Principal.class);
        when(eventInviteService.getEventInvite(any(User.class), any(Event.class))).thenReturn(MockData.getInvite(1));
        when(eventInviteService.saveEventInvite(any(EventInvite.class))).thenReturn(MockData.getInvite(1));
        EventInvite invite = new EventInvite();
        String string = mapper.writeValueAsString(invite);
        when(userService.getUserByEmail(principal.getName())).thenReturn(mock(User.class));
        this.mockMvc.perform(post("/api/event/setAttending/1")
                .content(string)
                .principal(principal)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(eventInviteService, times(1)).saveEventInvite(any(EventInvite.class));
    }

    @Test
    public void createComment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Principal principal = mock(Principal.class);
        when(commentService.saveComment(any(Comment.class))).thenReturn(MockData.getComment(1));
        Comment comment = new Comment();
        String string = mapper.writeValueAsString(comment);
        when(userService.getUserByEmail(principal.getName())).thenReturn(mock(User.class));
        this.mockMvc.perform(post("/api/event/createComment/1")
                .content(string)
                .principal(principal)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(commentService, times(1)).saveComment(any(Comment.class));
    }

    @Test
    public void addEventAdmins() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String ids = mapper.writeValueAsString(new int[]{1,2,3});
        when(userService.getUser(anyInt())).thenReturn(MockData.getUser(2));

        mockMvc.perform(post("/api/event/1/admins").principal(mock(Principal.class))
                .content(ids)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(roleService, times(3)).createRole("email2", Role.EVENT_ADMIN, 1);
    }

    @Test
    public void isEventAdmin() throws Exception {
        Principal principal = mock(Principal.class);
        when(roleService.getRole(principal.getName(), "ROLE_EVENT_ADMIN", 1)).thenReturn(MockData.getEventAdmin(1));
        mockMvc.perform(get("/api/event/isEventAdmin/1")
                .principal(principal))
                .andExpect(status().isOk());
        verify(roleService, times(1)).getRole(principal.getName(), "ROLE_EVENT_ADMIN", 1);
    }

    @Test
    public void joinEvent() throws Exception {
        Principal principal = mock(Principal.class);
        when(eventService.getEvent(anyInt())).thenReturn(MockData.getEvent(1));
        when(eventInviteService.saveEventInvite(any(EventInvite.class))).thenReturn(MockData.getInvite(1));
        when(userService.getUserByEmail(principal.getName())).thenReturn(mock(User.class));
        this.mockMvc.perform(post("/api/event/join/1")
                .principal(principal)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(eventInviteService, times(1)).saveEventInvite(any(EventInvite.class));
    }
}
