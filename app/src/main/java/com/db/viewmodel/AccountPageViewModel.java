package com.db.viewmodel;

import android.arch.lifecycle.ViewModel;

import bean.UserBean;

public class AccountPageViewModel extends ViewModel {
    private static UserBean userBean = new UserBean("null","null","null","null","null");
    public static UserBean getUserBean() {
        return userBean;
    }

    public static void setUserBean(UserBean userBean) {
        AccountPageViewModel.userBean = userBean;
    }
}
