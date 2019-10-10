package com.chnu.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "couriers")
public class Courier implements Serializable {
    @Id
    @Column(name = "user_id")
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_id", referencedColumnName = "transport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "Transport cannot be null")
    private Transport transport;

    @Column(name = "company_id")
    private Long companyId;

    public Long getUserId() {
        return userId;
    }

    public Courier setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Transport getTransport() {
        return transport;
    }

    public Courier setTransport(Transport transport) {
        this.transport = transport;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Courier setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
}
