package cs309.dto;

/**
 * Created by jeffrey on 2/13/16.
 */
public class ErrorsDTO {
    private String fieldId;
    private String errorMessage;

    public ErrorsDTO() {

    }

    public ErrorsDTO(String fieldId, String errorMessage) {
        this.fieldId = fieldId;
        this.errorMessage = errorMessage;
    }

    public String getFieldId() {

        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
