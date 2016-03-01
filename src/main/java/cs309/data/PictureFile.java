package cs309.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "picture_file")
public class PictureFile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "event_id")
    private Integer eventId;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    public PictureFile(String fileName, byte[] picture) {
        this.fileName = fileName;
        this.picture = picture;
    }

    public PictureFile(String fileName, Integer userId, Integer eventId, byte[] picture) {
        this.fileName = fileName;
        this.userId = userId;
        this.eventId = eventId;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "PictureFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", userId=" + userId +
                ", eventId=" + eventId +
                '}';
    }
}
