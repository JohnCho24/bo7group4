package com.b07group4;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import com.b07group4.auth.AuthContract;
import com.b07group4.auth.home.HomePresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class HomePresenterTest {
    @Mock
    AuthContract.Home.View view;

    private HomePresenter presenter = new HomePresenter(view);

    @Test
    public void onClickAsOwner() {
        presenter.onClickAsOwner();
        verify(view).goToOwnerAuth();
    }

    @Test
    public void onClickAsShopper() {
        presenter.onClickAsShopper();
        verify(view).goToShopperAuth();
    }
}