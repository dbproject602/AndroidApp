package com.db.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import bean.UserBean;

public class MapPageViewModel extends ViewModel {
    private MutableLiveData<double[]> location = new MutableLiveData<>();



    public void setLocation(double latitude,double longitude) {
        this.location.setValue(new double[]{latitude,longitude});
    }
    public LiveData<double[]> getLocation() {
        return location;
    }


}
