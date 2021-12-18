package com.libokai.pojo;

import java.util.List;

public class Field {
    private Integer id;

    private String title;

    private Integer admId;

    private Integer state;

    private User adm;
    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAdmId() {
        return admId;
    }

    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public User getAdm() {
        return adm;
    }

    public void setAdm(User adm) {
        this.adm = adm;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}