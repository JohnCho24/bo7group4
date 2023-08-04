package com.b07group4.DataModels;

import java.util.List;

public class Order {

    public enum OrderStatus {
        IN_CART,
        IN_PROCESS,
        DONE
    };

    private String orderId;
    private String shopperId;
    private OrderStatus status;
    private List<Product> items;

    public Order() {}

    public Order(String orderId, String shopperId, OrderStatus status, double totalAmount, List<Product> items) {
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
}
