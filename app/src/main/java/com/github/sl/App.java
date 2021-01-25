package com.github.sl;

import android.app.Application;
import android.content.Context;

import com.github.commonlib.utils.LogUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LogUtils.init(this);
    }
}
