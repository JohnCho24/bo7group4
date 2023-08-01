package com.b07group4;

public class Order {

    String orderId;
    String shopperId;
    String status;
    String storeId;

    public Order(String orderId, String shopperId, String status, String storeId) {
        this.orderId = orderId;
        this.shopperId = shopperId;
        this.status = status;
        this.storeId = storeId;
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

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}