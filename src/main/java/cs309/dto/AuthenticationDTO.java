package cs309.dto;

public class AuthenticationDTO {
    private Integer userId;
    private String authorityLevel;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(String authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    @Override
    public String toString() {
        return "AuthenticationDTO{" +
                "userId=" + userId +
                ", authorityLevel='" + authorityLevel + '\'' +
                '}';
    }
}
