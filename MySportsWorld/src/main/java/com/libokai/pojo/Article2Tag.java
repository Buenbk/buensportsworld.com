package com.libokai.pojo;

public class Article2Tag {
    private Integer articleId;

    private Integer tagId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Article2Tag(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}