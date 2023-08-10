package com.b07group4;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.b07group4.DBHandler.DBCallback;
import com.b07group4.DataModels.User;
import com.b07group4.auth.AuthContract;
import com.b07group4.auth.owner.LoginPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)

public class OwnerLoginPresenterTest {
    @Mock
    private AuthContract.Login.View view;

    @Mock
    private AuthContract.Login.Model model;



    @Test
    public void testLoginSuccess() {
        LoginPresenter presenter = new LoginPresenter(view, model);
        User testUser = new User();
        when(view.getUser()).thenReturn(testUser);
        doAnswer((Answer<Void>) invocation -> {
            DBCallback<User> callback = invocation.getArgument(1);
            callback.OnData(testUser);
            return null;
        }).when(model).login(any(User.class), any(DBCallback.class));

        presenter.onClickLogin();

        verify(view).showLoading();
        verify(model).login(eq(testUser), any(DBCallback.class));
        verify(view).hideLoading();
        verify(view).onSuccess(testUser);
    }

    @Test
    public void testLoginFailure() {
        LoginPresenter presenter = new LoginPresenter(view, model);
        when(view.getUser()).thenReturn(new User());
        doAnswer((Answer<Void>) invocation -> {
            DBCallback<User> callback = invocation.getArgument(1);
            callback.OnData(null);
            return null;
        }).when(model).login(any(User.class), any(DBCallback.class));

        presenter.onClickLogin();

        verify(view).showLoading();
        verify(model).login(any(User.class), any(DBCallback.class));
        verify(view).hideLoading();
        verify(view).onFailure();
    }
}