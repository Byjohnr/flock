package validator;

import config.UnitTestBase;
import cs309.dto.CreateEventDTO;
import cs309.validator.CreateEventValidator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateEventValidatorUTest extends UnitTestBase {

    private Errors errors;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private CreateEventValidator createEventValidator;

    @Before
    public void setup() {
        Map<String,String> map = new HashMap<>();
        errors = new MapBindingResult(map, CreateEventDTO.class.getName());
    }

    @Test
    public void testSupports() {
        boolean boo = createEventValidator.supports(CreateEventDTO.class);
        assertTrue(boo);
    }

    @Test
    public void validEvent() {
        CreateEventDTO event = new CreateEventDTO();
        event.setAddress("Address");
        event.setEventName("blub");
        event.setDescription("description");
        event.setStartDate("01 May, 1945 05:13 am");
        event.setType(1);
        createEventValidator.validate(event,errors);
        assertEquals(0,errors.getErrorCount());
    }

    @Test
    public void emptyFields() {
        CreateEventDTO event = new CreateEventDTO();
        event.setAddress("");
        event.setEventName("");
        event.setDescription("");
        event.setStartDate("");
        event.setType(1);
        createEventValidator.validate(event,errors);
        assertEquals(5,errors.getErrorCount());

    }

    @Test
    public void tooLongFields() {
        CreateEventDTO event = new CreateEventDTO();
        event.setAddress(StringUtils.leftPad("*",401));
        event.setEventName(StringUtils.leftPad("*",101));
        event.setDescription(StringUtils.leftPad("*",2001));
        event.setStartDate("01 May, 1945 05:13 am");
        event.setType(1);
        createEventValidator.validate(event,errors);
        assertEquals(3,errors.getErrorCount());
    }

    @Test
    public void invalidFields() {
        CreateEventDTO event = new CreateEventDTO();
        event.setAddress("Address");
        event.setEventName("blub");
        event.setDescription("eek");
        event.setStartDate("bobber");
        event.setStartDate("02 May, 1945 05:13 am");
        event.setEndDate("01 May, 1945 05:13 am");
        event.setType(70);
        createEventValidator.validate(event,errors);
        assertEquals(2,errors.getErrorCount());

    }

}
