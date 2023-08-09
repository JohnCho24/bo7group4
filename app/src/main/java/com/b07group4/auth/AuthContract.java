package com.b07group4.auth;

import com.b07group4.DataModels.User;
import com.b07group4.mvp_stuffs.Contract;

public interface AuthContract extends Contract {
    interface Home {
        interface View extends Contract.View {}

        interface Presenter extends Contract.Presenter {
            void onClickAsOwner();
            void onClickAsShopper();
        }

        interface Model extends Contract.Model {}
    }

    interface Login {
        interface Model extends Contract.Model {
            void login(User u);
        }

        interface View extends Contract.View {
            void showLoading();
            void hideLoading();
            void showSuccess();
            void hideSuccess();
            void showFailure();
            void hideFailure();
            User getUser();
        }

        interface Presenter extends Contract.Presenter {
            void onClickLogin();
        }
    }

    interface Register {
        interface Model extends Contract.Model {
            void register(User u);
        }

        interface View extends Contract.View {
            void showLoading();
            void hideLoading();
            void showSuccess();
            void hideSuccess();
            void showFailure();
            void hideFailure();
            User getUser();
        }

        interface Presenter extends Contract.Presenter {
            void onClickRegister();
        }
    }
}
