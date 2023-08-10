package com.b07group4.DataModels;

import java.util.List;

public class SubStoreOrder {
    private Order.OrderStatus orderStatus;
    private String productIdList;
    public SubStoreOrder(){

    }

    public String getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(String productIdList) {
        this.productIdList = productIdList;
    }

    public Order.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Order.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
