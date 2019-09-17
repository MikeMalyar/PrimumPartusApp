package com.chnu.wrapper;

public class UserLoginWrapper {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public UserLoginWrapper setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginWrapper setPassword(String password) {
        this.password = password;
        return this;
    }
}
