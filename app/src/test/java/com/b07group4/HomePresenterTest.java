package com.b07group4;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import com.b07group4.auth.AuthContract;
import com.b07group4.auth.home.HomePage;
import com.b07group4.auth.home.HomePresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class HomePresenterTest {
    @Mock
    private AuthContract.Home.View view;



    @Test
    public void onClickAsOwner() {
        HomePresenter presenter = new HomePresenter(view);
        presenter.onClickAsOwner();
        verify(view).goToOwnerAuth();
    }

    @Test
    public void onClickAsShopper() {
        HomePresenter presenter = new HomePresenter(view);
        presenter.onClickAsShopper();
        verify(view).goToShopperAuth();
    }
}