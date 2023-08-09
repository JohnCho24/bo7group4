package com.b07group4.auth.shopper;

import com.b07group4.auth.AuthContract;

public class RegisterPresenter implements AuthContract.Register.Presenter {
    private AuthContract.Register.View registerPage;
    private AuthContract.Register.Model model;

    public RegisterPresenter(AuthContract.Register.View registerPage, AuthContract.Register.Model model) {
        this.registerPage = registerPage;
        this.model = model;
    }

    @Override
    public void onClickRegister() {
        registerPage.showLoading();

        model.register(registerPage.getUser(), u -> {
            registerPage.hideLoading();
            if (u != null) {
                registerPage.onSuccess();
            } else {
                registerPage.onFailure();
            }
        });
    }
}
