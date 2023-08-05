package com.b07group4.DataModels;

import java.util.List;

public class Order {

    public enum OrderStatus {
        IN_CART,
        IN_PROCESS,
        DONE
    };

    private String orderId;
    private String orderStatus;
    private String shopperId;
    private String storeId;
    private OrderStatus status;
    private List<Product> items;

    public Order() {}

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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
