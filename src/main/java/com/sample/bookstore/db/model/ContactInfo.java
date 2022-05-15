package com.sample.bookstore.db.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInfo {

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Column(name = "address")//, nullable = false)
    private String address;

    @Column(name = "phoneNumber")//, nullable = false)
    private String phoneNumber;

    @Column(name = "emailAddres")//, nullable = false)
    private String emailAddress;
}
