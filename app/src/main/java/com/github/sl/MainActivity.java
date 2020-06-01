package com.github.sl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.github.commonlib.utils.LogUtils;
import com.github.commonlib.utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.init(this);
        LogUtils.d(TAG, "onCreate: ");
    }

    public void onClick(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setImmersiveStatusBar(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        super.setContentView(view);
        setImmersiveStatusBar(view);
    }
    /**
     *  设置沉浸式状态栏
     * @param view
     */
    protected void setImmersiveStatusBar(View view){
        if (!StatusBarUtils.setLightMode(this)){
            StatusBarUtils.setColor(this, Color.WHITE);
        }else {
            StatusBarUtils.setColorNoTranslucent(this, Color.WHITE);
        }
        StatusBarUtils.setFitsSystemWindows(view, true);
    }
}
