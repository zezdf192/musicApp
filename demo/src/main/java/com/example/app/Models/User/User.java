    package com.example.app.Models.User;

    public class User {
        String userName;
        Integer userId;
        String userEmail;
        String userGender;
        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public String getUserName() {
            return userName;
        }

        public Integer getUserId() {
            return userId;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public String getUserGender() {
            return userGender;
        }
    }
