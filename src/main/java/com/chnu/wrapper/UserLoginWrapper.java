package com.chnu.wrapper;

import javax.validation.constraints.NotBlank;

public class UserLoginWrapper {

    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
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
