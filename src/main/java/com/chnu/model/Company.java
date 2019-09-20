package com.chnu.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name")
    private  String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User ownerId;

    @Column(name = "url")
    private URL url;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company", cascade = CascadeType.REMOVE)
    private Set<Courier> couriers;

    @Column(name = "enabled")
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

    public User getOwnerId() {
        return ownerId;
    }

    public Company setOwnerId(User ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public URL getUrl() {
        return url;
    }

    public Company setUrl(URL url) {
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
