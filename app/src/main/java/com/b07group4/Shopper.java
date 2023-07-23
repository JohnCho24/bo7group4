package com.b07group4;

public class Shopper extends Users {
    private String shopperId;
    private String password;
    public Shopper(){

    }
    public Shopper(String shopperId, String password){
        super(shopperId, password);
    }
}
