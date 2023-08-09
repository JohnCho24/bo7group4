package com.b07group4.auth.home;

import com.b07group4.auth.AuthContract;

public class HomePresenter implements AuthContract.Home.Presenter {
    AuthContract.Home.View homepage;

    @Override
    public void onClickAsOwner() {
        homepage.goToOwnerAuth();
    }

    @Override
    public void onClickAsShopper() {
        homepage.goToShopperAuth();
    }
}
