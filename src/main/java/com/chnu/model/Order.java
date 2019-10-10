package com.chnu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id", nullable = false)
    @NotNull(message = "Customer cannot be null")
    private User customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = false)
    @NotNull(message = "Company cannot be null")
    private Company company;

    @Column(name = "description")
    private String description;

    @Column(name = "address_from")
    private String addressFrom;

    @Column(name = "address_to")
    private String addressTo;

    @Column(name = "datetime_from")
    private Date datetimeFrom;

    @Column(name = "datetime_to")
    private Date datetimeTo;

    @Column(name = "status")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id", referencedColumnName = "user_id", nullable = false)
    @NotNull(message = "Courier cannot be null")
    private Courier courier;

    public enum Status {
        TO_DO,
        IN_PROGRESS,
        DONE
    }

    public Long getOrderId() {
        return orderId;
    }

    public User getCustomer() {
        return customer;
    }

    public Company getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public Date getDatetimeFrom() {
        return datetimeFrom;
    }

    public Date getDatetimeTo() {
        return datetimeTo;
    }

    public Status getStatus() {
        return status;
    }

    public Courier getCourier() {
        return courier;
    }

    public Order setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Order setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    public Order setCompany(Company company) {
        this.company = company;
        return this;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public Order setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
        return this;
    }

    public Order setAddressTo(String addressTo) {
        this.addressTo = addressTo;
        return this;
    }

    public Order setDatetimeFrom(Date datetimeFrom) {
        this.datetimeFrom = datetimeFrom;
        return this;
    }

    public Order setDatetimeTo(Date datetimeTo) {
        this.datetimeTo = datetimeTo;
        return this;
    }

    public Order setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Order setCourier(Courier courier) {
        this.courier = courier;
        return this;
    }
}
