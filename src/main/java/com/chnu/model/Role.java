package com.chnu.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Override
    public String getAuthority() {
        return "ROLE_" + role.toUpperCase();
    }

    public Long getRoleId() {
        return roleId;
    }

    public Role setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Role setRole(String role) {
        this.role = role;
        return this;
    }
}
