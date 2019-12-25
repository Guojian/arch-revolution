package personal.jeremyxu.archrevolutition.demo5.aggregationservice.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by jeremyxu on 2019/3/26.
 */
public class BlogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;
    private Integer userId;
    private String nickname;
    private String firstName;
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogDTO blogDTO = (BlogDTO) o;
        return id == blogDTO.id &&
                Objects.equals(title, blogDTO.title) &&
                Objects.equals(content, blogDTO.content) &&
                Objects.equals(pubDate, blogDTO.pubDate) &&
                Objects.equals(userId, blogDTO.userId) &&
                Objects.equals(nickname, blogDTO.nickname) &&
                Objects.equals(firstName, blogDTO.firstName) &&
                Objects.equals(lastName, blogDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, pubDate, userId, nickname, firstName, lastName);
    }
}
