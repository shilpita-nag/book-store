package com.sample.bookstore.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookStoreOrder")
public class BookStoreOrder {

    public BookStoreOrder(long customerId, LocalDate orderDate, String paymentMode, float orderAmount) {
        this.orderId = customerId;
        this.orderDate = orderDate;
        this.paymentMode = paymentMode;
        this.orderAmount = orderAmount;
    }

    public BookStoreOrder() {}

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId",nullable = false,unique = true)
    private long orderId;

    @Column(name = "orderDate")
    @CreationTimestamp
    private LocalDate orderDate;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Column(name = "paymentMode")
    private String paymentMode;

    @Column(name = "orderAmount")
    private float orderAmount;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        for(OrderItem o : orderItems) {
            o.setBookStoreOrder(this);
        }
    }

    @OneToMany(mappedBy = "bookStoreOrder", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customerId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;
}
