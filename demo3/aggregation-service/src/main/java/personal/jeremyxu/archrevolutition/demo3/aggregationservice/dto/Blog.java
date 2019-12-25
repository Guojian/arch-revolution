package personal.jeremyxu.archrevolutition.demo3.aggregationservice.dto;

import java.util.Date;
import java.util.Objects;

/**
 * Created by jeremyxu on 2019/3/26.
 */
public class Blog {
    private int id;
    private String title;
    private String content;
    private Date pubDate;
    private Integer userId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blogDTO = (Blog) o;
        return id == blogDTO.id &&
                Objects.equals(title, blogDTO.title) &&
                Objects.equals(content, blogDTO.content) &&
                Objects.equals(pubDate, blogDTO.pubDate) &&
                Objects.equals(userId, blogDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, pubDate, userId);
    }
}
