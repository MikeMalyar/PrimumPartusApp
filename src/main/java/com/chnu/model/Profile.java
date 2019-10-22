package com.chnu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "user_id")
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @Column(name="first_name")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Column(name="last_name")
    @NotBlank(message = "Last name cannot be blank")
    private  String lastName;

    @Column(name="dob")
    @NotNull(message = "Date of birth cannot be null")
    private Date dob;

    public Long getUserId() {
        return userId;
    }

    public Profile setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;

    }

    public Profile setFirstName(String firstName) {
        this.firstName = firstName;
        return  this;
    }

    public String getLastName() {
        return lastName;
    }

    public Profile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getDob() {
        return dob;
    }

    public Profile setDob(Date dob) {
        this.dob = dob;
        return this;
    }
}
