package com.chnu.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name", nullable = false)
    private  String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User owner;

    @Column(name = "url")
    private String url;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.REMOVE)
    private Set<Courier> couriers;

    @Column(name = "enabled", nullable = false)
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

    public Set<Courier> getCouriers() {
        return couriers;
    }

    public Company setCouriers(Set<Courier> couriers) {
        this.couriers = couriers;
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
