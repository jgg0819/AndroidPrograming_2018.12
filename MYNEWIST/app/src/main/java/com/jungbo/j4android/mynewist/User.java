package com.jungbo.j4android.mynewist;

public class User {
    String userId;
    String userPassword;
    String userName;
    String userAge;
    String userMail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;

    }

    public String getUserName() {
        return userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserMail() {
        return userMail;
    }

    public User(String userId, String userPassword, String userName, String userAge, String userMail) {

        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAge = userAge;
        this.userMail = userMail;
    }




}
