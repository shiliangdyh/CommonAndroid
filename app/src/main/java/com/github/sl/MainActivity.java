package com.github.sl;

import android.os.Bundle;
import android.view.View;

import com.github.commonlib.base.SLBaseActivity;
import com.github.commonlib.utils.LogUtils;

public class MainActivity extends SLBaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.init(this);
        LogUtils.d(TAG, "onCreate: ");


    }

    public void onClick(View view) {

    }

}
