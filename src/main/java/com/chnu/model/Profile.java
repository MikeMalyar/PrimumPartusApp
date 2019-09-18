package com.chnu.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profiles")
@NamedQueries({
        @NamedQuery(name = Profile.FIND_BY_PROFILE, query = "select p from Profile p where p.userId = :user_id")
})
public class Profile {

    public static final String FIND_BY_PROFILE="findByProfile";

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private  String lastName;

    @Column(name="dob")
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
