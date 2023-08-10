package com.b07group4;

import static org.junit.Assert.*;

import com.b07group4.auth.AuthContract;
import com.b07group4.auth.shopper.LoginPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShopperLoginPresenterTest {
    @Mock
    private AuthContract.Login.View view;

    @Mock
    private AuthContract.Login.Model model;

    private LoginPresenter presenter = new LoginPresenter(view, model);

    @Test
    public void onClickLogin() {
    }
}