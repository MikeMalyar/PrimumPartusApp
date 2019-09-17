package com.chnu.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "couriers")
public class Courier implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_id", referencedColumnName = "transport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Transport transport;

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
}
