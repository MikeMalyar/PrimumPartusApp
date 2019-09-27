package com.chnu.model;

import com.chnu.util.PropertiesUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.chnu.util.PropertiesUtil.getProperty;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_EMAIL, query = "select us from User us where us.email = :email")
})
public class User implements Serializable, UserDetails {

    public static final String FIND_BY_EMAIL = "findByEmail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @Column(name = "lock")
    private Date lock;

    @Column(name = "fail_attempts")
    private Integer failAttempts;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        String s = getProperty(PropertiesUtil.SYSTEM_PROPERTIES, "account.lock.minutes");
        Integer lockTimeInMinutes = 60;
        if(s != null) {
            lockTimeInMinutes = Integer.decode(s);
        }
        return lock == null || ((new Date().getTime() - lock.getTime()) / 60000 >= lockTimeInMinutes);
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public User setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public Date getLock() {
        return lock;
    }

    public User setLock(Date lock) {
        this.lock = lock;
        return this;
    }

    public Integer getFailAttempts() {
        return failAttempts;
    }

    public User setFailAttempts(Integer failAttempts) {
        this.failAttempts = failAttempts;
        return this;
    }
}
