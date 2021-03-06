package com.chnu.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Min weight cannot be null")
    @Min(value = 0)
    private float minWeight;

    @Column(name = "max_weight", nullable = false)
    @NotNull(message = "Max weight cannot be null")
    @Min(value = 0)
    private float maxWeight;

    @Column(name = "max_volume", nullable = false)
    @NotNull(message = "Max volume cannot be null")
    @Min(value = 0)
    private float maxVolume;

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

    public float getMinWeight() {
        return minWeight;
    }

    public Transport setMinWeight(float minWeight) {
        this.minWeight = minWeight;
        return this;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public Transport setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public float getMaxVolume() {
        return maxVolume;
    }

    public Transport setMaxVolume(float maxVolume) {
        this.maxVolume = maxVolume;
        return this;
    }
}
