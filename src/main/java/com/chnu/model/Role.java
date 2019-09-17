package com.chnu.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = Role.FIND_BY_ROLE, query = "select r from Role r where r.role = :role")
})
public class Role implements GrantedAuthority {

    public static final String FIND_BY_ROLE = "findByRole";

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
