package com.chnu.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transports")
public class Transport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_id")
    private Long transportId;

    @Column(name = "description")
    private String description;

    @Column(name = "min_weight", nullable = false)
    private float min_weight;

    @Column(name = "max_weight", nullable = false)
    private float max_weight;

    @Column(name = "max_volume", nullable = false)
    private float max_volume;

    public long getTransportId() {
        return transportId;
    }

    public Transport setTransportId(long transportId) {
        this.transportId = transportId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transport setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getMin_weight() {
        return min_weight;
    }

    public Transport setMin_weight(float min_weight) {
        this.min_weight = min_weight;
        return this;
    }

    public float getMax_weight() {
        return max_weight;
    }

    public Transport setMax_weight(float max_weight) {
        this.max_weight = max_weight;
        return this;
    }

    public float getMax_volume() {
        return max_volume;
    }

    public Transport setMax_volume(float max_volume) {
        this.max_volume = max_volume;
        return this;
    }
}
