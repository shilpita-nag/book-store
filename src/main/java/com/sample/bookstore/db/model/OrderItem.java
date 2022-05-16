package com.sample.bookstore.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "orderItem")
public class OrderItem {

    public OrderItem() {
    }

    public OrderItem(long orderItemId, long itemId, int itemQuantity) {
        this.orderItemId = orderItemId;
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BookStoreOrder getBookStoreOrder() {
        return bookStoreOrder;
    }

    public void setBookStoreOrder(BookStoreOrder bookStoreOrder) {
        this.bookStoreOrder = bookStoreOrder;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BookStoreOrder bookStoreOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderItemId;

    @Column(name = "itemId")
    private long itemId;

    @Column(name = "quantity")
    private int itemQuantity;
}
