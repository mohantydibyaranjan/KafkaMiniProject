package com.example.producer.model;

public class Order {
    private long id;
    private String productName;
    private int quantity;

    // Default constructor
    public Order() {
    }

    // Constructor with all fields
    public Order(long id, String productName, int quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order(id=" + id +
                ", productName=" + productName +
                ", quantity=" + quantity +
                ')';
    }
}
