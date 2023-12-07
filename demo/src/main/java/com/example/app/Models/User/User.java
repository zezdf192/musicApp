package com.example.app.Models.User;

public class User {
    private String userName;
    private Integer userId;
    private String userEmail;
    private String userGender;

    // Constructor

    //    public User (String userName, Integer userId, String userEmail, String userGender) {
//        this.userName = userName;
//        this.userId = userId;
//        this.userEmail = userEmail;
//        this.userGender = userGender;
//    }
    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}