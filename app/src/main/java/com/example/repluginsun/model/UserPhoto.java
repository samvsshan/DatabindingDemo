package com.example.repluginsun.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class UserPhoto extends BaseObservable {
    private String userPhotoUrl;

    @Bindable
    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }
}
