package cs309.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    private String picture;

    public PictureFile() {
    }

    public PictureFile(String fileName, String picture) {
        this.fileName = fileName;
        this.picture = picture;
    }

    public PictureFile(String fileName, Integer userId, Integer eventId, String picture) {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PictureFile that = (PictureFile) o;

        return new EqualsBuilder()
                .append(fileName, that.fileName)
                .append(userId, that.userId)
                .append(eventId, that.eventId)
                .append(picture, that.picture)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fileName)
                .append(userId)
                .append(eventId)
                .append(picture)
                .toHashCode();
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
