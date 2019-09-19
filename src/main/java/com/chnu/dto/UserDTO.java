package com.chnu.dto;

import com.chnu.model.User;

public class UserDTO {

    private Long userId;
    private String email;
    private String phoneNumber;
    private String role;
    private Boolean enabled;
    private Boolean correctCredentials;

    public static UserDTO fromUser(User user) {
        return new UserDTO()
                .setEmail(user.getEmail())
                .setUserId(user.getUserId())
                .setPhoneNumber(user.getPhoneNumber())
                .setRole(user.getRole().getRole())
                .setEnabled(user.getEnabled());
    }

    public Long getUserId() {
        return userId;
    }

    public UserDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public UserDTO setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Boolean getCorrectCredentials() {
        return correctCredentials;
    }

    public UserDTO setCorrectCredentials(Boolean correctCredentials) {
        this.correctCredentials = correctCredentials;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
