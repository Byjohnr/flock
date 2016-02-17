package cs309.validator;


import cs309.dto.CreateEventDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class CreateEventValidator implements Validator {

    @Autowired
    MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CreateEventDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateEventDTO eventDTO = (CreateEventDTO) target;

        if(StringUtils.isBlank(eventDTO.getEventName())) {
            errors.rejectValue("eventName", messageSource.getMessage("createEvent.eventName.empty", null, Locale.ENGLISH));

        } else if (eventDTO.getEventName().length() > 100) {
            errors.rejectValue("eventName", messageSource.getMessage("createEvent.eventName.tooLong", null, Locale.ENGLISH));
        }
        if(StringUtils.isBlank(eventDTO.getDescription())) {
            errors.rejectValue("description", messageSource.getMessage("createEvent.description.empty", null, Locale.ENGLISH));
        } else if (eventDTO.getDescription().length() > 2000) {
            errors.rejectValue("description", messageSource.getMessage("createEvent.description.tooLong", null, Locale.ENGLISH));
        }
        if(StringUtils.isBlank(eventDTO.getAddress())) {
            errors.rejectValue("address", messageSource.getMessage("createEvent.address.empty", null, Locale.ENGLISH));
        } else if (eventDTO.getAddress().length() > 400) {
            errors.rejectValue("address", messageSource.getMessage("createEvent.address.tooLong", null, Locale.ENGLISH));

        }
        if(StringUtils.isBlank(eventDTO.getStartDate())) {
            errors.rejectValue("startDate", messageSource.getMessage("createEvent.startDate.empty", null, Locale.ENGLISH));
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy HH:mm a");
        Date start = null;
        Date end = null;
        try {
            start = dateFormat.parse(eventDTO.getStartDate());
        } catch (ParseException e) {
            errors.rejectValue("startDate", messageSource.getMessage("createEvent.startDate.invalid", null, Locale.ENGLISH));
        }
        if(StringUtils.isNotBlank(eventDTO.getEndDate())) {
            try {
                end = dateFormat.parse(eventDTO.getEndDate());
            } catch (ParseException e) {
                errors.rejectValue("endDate", messageSource.getMessage("createEvent.endDate.invalid", null, Locale.ENGLISH));
            }
        }

        if(end != null && start != null && start.after(end)) {
            errors.rejectValue("startDate", messageSource.getMessage("createEvent.startDate.afterEnd", null, Locale.ENGLISH));
        }

        if(eventDTO.getType() > 3 || eventDTO.getType() < 1) {
            errors.rejectValue("type", messageSource.getMessage("createEvent.type.invalid", null, Locale.ENGLISH));
        }
    }
}
