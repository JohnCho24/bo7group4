package com.b07group4.DataModels;

import com.b07group4.DataModels.Item;

import java.util.List;

public class Order {
    private String orderId;
    private String shopperId;
    private String status;
    private List<Item> items;
    public Order() {

    }

    public Order(String orderId, String shopperId, String status, double totalAmount, List<Item> items) {
        this.orderId = orderId;
        this.shopperId = shopperId;
        this.status = status;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
