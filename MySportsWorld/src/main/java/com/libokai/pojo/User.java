package com.libokai.pojo;

public class User {
    private Integer id;

    private String username;

    private String email;

    private String bio;

    private String userImg;

    private EmailPassword emailPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public EmailPassword getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(EmailPassword emailPassword) {
        this.emailPassword = emailPassword;
    }
}