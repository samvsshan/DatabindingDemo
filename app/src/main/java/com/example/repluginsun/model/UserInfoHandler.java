package com.example.repluginsun.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

public class UserInfoHandler extends BaseObservable {
    public void startHostActivity(Context context,String activityName) {
        //插件调用宿主程序的界面
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context.getPackageName(),activityName));
        context.startActivity(intent);
    }
}
