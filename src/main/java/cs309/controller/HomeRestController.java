package cs309.controller;

import cs309.data.Event;
import cs309.dto.CreateEventDTO;
import cs309.dto.ErrorsDTO;
import cs309.dto.EventDTO;
import cs309.service.EventService;
import cs309.service.UserService;
import cs309.validator.CreateEventValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jeffrey on 1/29/16.
 */
@RestController
@RequestMapping("/api")
public class HomeRestController {

    private Logger LOG = Logger.getLogger(HomeRestController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private CreateEventValidator eventValidator;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/events")
    public List<EventDTO> getEvents() {
        List<EventDTO> eventDTOs = new ArrayList<>();
        eventService.getEvents().stream().forEach(event -> eventDTOs.add(new EventDTO(event)));
        return eventDTOs;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<ErrorsDTO> createEvent(@Valid @RequestBody final CreateEventDTO createEventDTO, BindingResult result) throws IOException, ParseException {
        LOG.error(result.getFieldErrors());
        if(result.hasErrors()) {
            List<ErrorsDTO> errors = new ArrayList<>();
            result.getFieldErrors().stream().forEach(fieldError ->  errors.add(new ErrorsDTO(fieldError.getField(),fieldError.getCode())));
            return errors;
        }
//        TODO jefffreyh 2-6/16 set the user by whoever is creating the event
        eventService.saveEvent(new Event(createEventDTO, userService.getUser(1)));
//        TODO jeffreyh 2/7/16 return url to event just created when the event page is created
        return new ArrayList<>();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(eventValidator);
    }
}
