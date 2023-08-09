package com.b07group4.auth.shopper;

import com.b07group4.auth.AuthContract;

public class LoginPresenter implements AuthContract.Login.Presenter {
    private AuthContract.Login.View loginPage;
    private AuthContract.Login.Model model;

    @Override
    public void onClickLogin() {
        model.login(loginPage.getUser(), u -> {});
    }
}
