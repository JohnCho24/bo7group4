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
import com.b07group4.auth.owner.RegisterPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class OwnerRegisterPresenterTest {

    @Mock
    private AuthContract.Register.View view;

    @Mock
    private AuthContract.Register.Model model;



    @Test
    public void testRegisterSuccess() {
        RegisterPresenter presenter = new RegisterPresenter(view, model);
        User testUser = new User();
        when(view.getUser()).thenReturn(testUser);
        doAnswer((Answer<Void>) invocation -> {
            DBCallback<User> callback = invocation.getArgument(1);
            callback.OnData(testUser);
            return null;
        }).when(model).register(any(User.class), any(DBCallback.class));

        presenter.onClickRegister();

        verify(view).showLoading();
        verify(model).register(eq(testUser), any(DBCallback.class));
        verify(view).hideLoading();
        verify(view).onSuccess(testUser);
    }

    @Test
    public void testRegisterFailure() {
        RegisterPresenter presenter = new RegisterPresenter(view, model);
        when(view.getUser()).thenReturn(new User());
        doAnswer((Answer<Void>) invocation -> {
            DBCallback<User> callback = invocation.getArgument(1);
            callback.OnData(null);
            return null;
        }).when(model).register(any(User.class), any(DBCallback.class));

        presenter.onClickRegister();

        verify(view).showLoading();
        verify(model).register(any(User.class), any(DBCallback.class));
        verify(view).hideLoading();
        verify(view).onFailure();
    }
}
