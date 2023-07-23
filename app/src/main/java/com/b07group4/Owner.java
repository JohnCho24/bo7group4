package com.b07group4;

public class Owner extends Users{
    private String storeId;
    public Owner(){

    }
    public Owner(String ownerId, String password, String storeId) {
        super(ownerId, password);
        this.storeId = storeId;
    }
}
