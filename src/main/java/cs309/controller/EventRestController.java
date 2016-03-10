package cs309.controller;

import cs309.data.EventInvite;
import cs309.data.User;
import cs309.data.Comment;
import cs309.dto.CreateEventDTO;
import cs309.dto.ErrorsDTO;
import cs309.dto.EventDTO;
import cs309.service.CommentService;
import cs309.service.EventInviteService;
import cs309.service.UserService;
import cs309.validator.CreateEventValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import cs309.data.Event;
import cs309.service.EventService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class EventRestController {

    private Logger LOG = Logger.getLogger(EventRestController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CreateEventValidator eventValidator;

    @Autowired
    private EventInviteService eventInviteService;

    @RequestMapping(value = "/api/event/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable Integer id) {
        LOG.info(eventService.getEvent(id).getCommentList());
        return eventService.getEvent(id);
    }

    @RequestMapping(value = "/api/event/{id}", method = RequestMethod.POST)
    public String updateEvent(@RequestBody Event event) {
        LOG.info(event.toString());
        eventService.saveEvent(event);
        return "/";
    }

    @RequestMapping("/api/events")
    public List<EventDTO> getEvents() {
        List<EventDTO> eventDTOs = new ArrayList<>();
        eventService.getEvents().stream().forEach(event -> eventDTOs.add(new EventDTO(event)));
        return eventDTOs;
    }

    @RequestMapping(value = "/api/event/createComment/{id}", method = RequestMethod.POST)
    public String createComment(@RequestBody String string, @PathVariable Integer id, Principal principal) {
        Comment comment = new Comment();
        Event event = eventService.getEvent(id);
        comment.setEvent(event);
        comment.setComment(string);
        comment.setDateCreated(new Date());
        comment.setOwner(userService.getUserByEmail(principal.getName()));
        commentService.saveComment(comment);
        return "/";
    }

    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public List<ErrorsDTO> createEvent(@Valid @RequestBody final CreateEventDTO createEventDTO, BindingResult result) throws IOException, ParseException {
        LOG.error(result.getFieldErrors());
        if(result.hasErrors()) {
            List<ErrorsDTO> errors = new ArrayList<>();
            result.getFieldErrors().stream().forEach(fieldError ->  errors.add(new ErrorsDTO(fieldError.getField(),fieldError.getCode())));
            return errors;
        }
//        TODO jefffreyh 2-6/16 set the user by whoever is creating the event
        Event event = eventService.saveEvent(new Event(createEventDTO, userService.getUser(1)));
        List<ErrorsDTO> noErrors = new ArrayList<>();
        noErrors.add(new ErrorsDTO("success", event.getId() + ""));
        return noErrors;
    }

    @RequestMapping(value = "/api/event/{eventId}/invites", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addInvites(@PathVariable Integer eventId, @RequestBody final Integer[] userIds, Principal principal) {
        Event event = eventService.getEvent(eventId);
        User inviter = userService.getUserByEmail(principal.getName());
        for(Integer userId : userIds) {
            User invitedUser = userService.getUser(userId);
            EventInvite eventInvite = new EventInvite(inviter,invitedUser,event);
            eventInviteService.saveEventInvite(eventInvite);
        }
    }

    @InitBinder(value = "createEventDTO")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(eventValidator);
    }
}
