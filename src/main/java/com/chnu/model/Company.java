package com.chnu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Company name cannot be blank")
    private  String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull(message = "Owner cannot be null")
    private User owner;

    @Column(name = "url")
    private String url;

    @Column(name = "enabled", nullable = false)
    @NotNull(message = "Enable field cannot be null")
    private Boolean enabled;

    public Long getCompanyId() {
        return companyId;
    }

    public Company setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Company setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Company setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Company setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
