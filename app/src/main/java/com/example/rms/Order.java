package com.example.rms;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String orderId;
    private String customerUsername;
    private ArrayList<CartItem> items;
    private double totalAmount;
    private String status; // PENDING, APPROVED, DELIVERED, CANCELLED
    private String date;

    public Order(String orderId, String customerUsername, ArrayList<CartItem> items, double totalAmount, String date) {
        this.orderId = orderId;
        this.customerUsername = customerUsername;
        this.items = new ArrayList<>(items);
        this.totalAmount = totalAmount;
        this.status = "PENDING";
        this.date = date;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerUsername() { return customerUsername; }
    public ArrayList<CartItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDate() { return date; }
}
