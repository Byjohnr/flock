package cs309.dto;

import cs309.data.User;

public class NotificationDTO {
    private String url;
    private String message;
    private Integer id;
    private Integer type;
    private Integer typeId;
    private User creator;

    public NotificationDTO (String url, String message, Integer id, Integer type, Integer typeId, User creator) {
        this.url = url;
        this.message = message;
        this.id = id;
        this.type = type;
        this.typeId = typeId;
        this.creator = creator;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getType() {return type;}

    public void setType(Integer type) {this.type = type;}

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}

