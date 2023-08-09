package com.b07group4.DBHandler;

import com.b07group4.DataModels.User;
import com.b07group4.auth.AuthContract;

public class AuthModel implements AuthContract.Login.Model, AuthContract.Register.Model {
    @Override
    public void login(User u, DBCallback<User> cb) {

    }

    @Override
    public void register(User u, DBCallback<User> cb) {

    }
}
