package com.libokai.pojo;

import java.util.Date;

public class News {
    private Integer id;

    private Date createdOn;

    private String title;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    public News(String title, String content) {
        this.createdOn = new Date();
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}