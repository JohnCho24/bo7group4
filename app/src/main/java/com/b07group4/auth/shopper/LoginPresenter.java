package com.b07group4.auth.shopper;

import com.b07group4.auth.AuthContract;

public class LoginPresenter implements AuthContract.Login.Presenter {
    private AuthContract.Login.View loginPage;
    private AuthContract.Login.Model model;

    public LoginPresenter(AuthContract.Login.View loginPage, AuthContract.Login.Model model) {
        this.loginPage = loginPage;
        this.model = model;
    }

    @Override
    public void onClickLogin() {
        loginPage.showLoading();

        model.login(loginPage.getUser(), u -> {
            loginPage.hideLoading();
            if (u != null) {
                loginPage.onSuccess();
            } else {
                loginPage.onFailure();
            }
        });
    }
}
