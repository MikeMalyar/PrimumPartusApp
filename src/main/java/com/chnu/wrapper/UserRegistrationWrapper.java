package com.chnu.wrapper;

import javax.validation.constraints.NotBlank;

public class UserRegistrationWrapper {

    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "Confirm password cannot be blank")
    private String confirmPassword;
    @NotBlank(message = "Role cannot be blank")
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
