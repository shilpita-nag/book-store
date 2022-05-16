package com.sample.bookstore.db.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId", nullable = false, unique = true)
    private Long customerId;

    @Column(name = "customerName", nullable = true)
    private String customerName;

    @Column(name = "contactInfo")
    private ContactInfo contactInfo;

    public List<BookStoreOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<BookStoreOrder> orderList) {
        this.orderList = orderList;
        for(BookStoreOrder o : orderList) {
            o.setCustomer(this);
        }
    }

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<BookStoreOrder> orderList = new ArrayList<>();

    public Customer(Long customerId, String customerName, ContactInfo contactInfo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactInfo = contactInfo;
    }

    public Customer() {}
}
