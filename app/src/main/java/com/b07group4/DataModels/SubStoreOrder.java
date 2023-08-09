package com.b07group4.DataModels;

import java.util.List;

public class SubStoreOrder {

    private String orderStatus;
    private String productIdList;
    public SubStoreOrder(){

    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(String productIdList) {
        this.productIdList = productIdList;
    }
}
