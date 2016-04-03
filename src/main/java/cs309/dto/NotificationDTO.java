package cs309.dto;

public class NotificationDTO {
    private String url;
    private String message;
    private Integer id;

    public NotificationDTO (String url, String message, Integer id) {
        this.url=url;
        this.message=message;
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

