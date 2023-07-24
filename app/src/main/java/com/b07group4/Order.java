package com.b07group4;

import java.util.Map;

public class Order {
    private String orderId;
    private String shopperId;
    private String status;
    private double totalAmount;
    private Map<String, Item> items;
    public Order() {

    }

    public Order(String orderId, String shopperId, String status, double totalAmount, Map<String, Item> items) {
        this.orderId = orderId;
        this.shopperId = shopperId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = items;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String customerId) {
        this.shopperId = customerId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }
}
