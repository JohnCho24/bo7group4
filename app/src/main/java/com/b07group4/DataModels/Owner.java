package com.b07group4.DataModels;

public class Owner {
    String username;
    String storeID;
    String password;


    public Owner(String username, String storeID, String password) {
        this.username = username;
        this.storeID = storeID;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}