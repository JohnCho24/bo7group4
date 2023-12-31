package com.b07group4.auth.owner;

import com.b07group4.auth.AuthContract;

public class LoginPresenter implements AuthContract.Login.Presenter {
    private AuthContract.Login.View loginPage;
    private AuthContract.Login.Model model;

    public LoginPresenter(AuthContract.Login.View v, AuthContract.Login.Model m) {
        loginPage = v;
        model = m;
    }

    @Override
    public void onClickLogin() {
        loginPage.showLoading();

        model.login(loginPage.getUser(), u -> {
            loginPage.hideLoading();
            if (u != null) {
                loginPage.onSuccess(u);
            } else {
                loginPage.onFailure();
            }
        });
    }
}
