package com.b07group4;

public class Order {
    private String orderId;
    private String shopperId;
    private String status;
    private double totalAmount;
    public Order() {

    }

    public Order(String orderId, String shopperId, String status, double totalAmount) {
        this.orderId = orderId;
        this.shopperId = shopperId;
        this.status = status;
        this.totalAmount = totalAmount;
    }
}
