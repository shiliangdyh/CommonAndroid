package com.github.sl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.commonlib.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.init(this);
        LogUtils.d(TAG, "onCreate: ");
    }
}
