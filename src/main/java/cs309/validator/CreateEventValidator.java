package cs309.validator;


import cs309.dto.CreateEventDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by jeffrey on 2/13/16.
 */
@Component
public class CreateEventValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CreateEventDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateEventDTO eventDTO = (CreateEventDTO) target;
        errors.rejectValue("description", "createEvent.description.empty");
    }


}
