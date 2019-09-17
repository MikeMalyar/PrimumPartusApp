package com.chnu.wrapper;

public class UserRegistrationWrapper {

    private String email;
    private String password;
    private String confirmPassword;
    private String role;

    public String getEmail() {
        return email;
    }

    public UserRegistrationWrapper setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationWrapper setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationWrapper setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRegistrationWrapper setRole(String role) {
        this.role = role;
        return this;
    }
}
