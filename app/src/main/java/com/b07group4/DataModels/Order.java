package com.b07group4.DataModels;

import com.b07group4.DataModels.Item;

import java.util.List;

public class Order {
    private String orderId;
    private String orderStatus;
    private String shopperId;
    private String storeId;

    public Order() {
    }

    // Getters and setters for each field
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
