package com.b07group4.DataModels;

public class Shopper extends User {
    private String currOrderID;
    public Shopper(){
    }

    public Shopper(String shopperId, String password, String currOrderID){
        super(shopperId, password);
        this.currOrderID = currOrderID;
    }

    public String getCurrOrderID() {
        return currOrderID;
    }

    public void setCurrOrderID(String currOrderID) {
        this.currOrderID = currOrderID;
    }
}