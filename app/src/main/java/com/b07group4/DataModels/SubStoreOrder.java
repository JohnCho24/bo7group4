package com.b07group4.DataModels;

import java.util.List;

public class SubStoreOrder {

    private String orderStatus;
    private List<Product> productList;
    public SubStoreOrder(){

    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
