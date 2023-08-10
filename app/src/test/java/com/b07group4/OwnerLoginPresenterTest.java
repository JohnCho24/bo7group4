package com.b07group4;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.b07group4.DBHandler.DBCallback;
import com.b07group4.DataModels.User;
import com.b07group4.auth.AuthContract;
import com.b07group4.auth.owner.LoginPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class OwnerLoginPresenterTest {
    @Mock
    private AuthContract.Login.View view;

    @Mock
    private AuthContract.Login.Model model;

    private LoginPresenter presenter = new LoginPresenter(view, model);


    @Test
    public void testLoginSuccess() {
        User testUser = new User();
        when(view.getUser()).thenReturn(testUser);

        presenter.onClickLogin();

        verify(view).showLoading();
        verify(model).login(eq(testUser), any(DBCallback.class));
        verify(view).hideLoading();
        verify(view).onSuccess();
    }

    @Test
    public void testLoginFailure() {
        when(view.getUser()).thenReturn(new User());

        presenter.onClickLogin();

        verify(view).showLoading();
        verify(model).login(any(User.class), any(DBCallback.class));
        verify(view).hideLoading();
        verify(view).onFailure();
    }
}