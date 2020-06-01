package com.github.sl;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.github.commonlib.utils.StatusBarUtils;

public class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        super.setContentView(view);
        setImmersiveStatusBar(view);
    }

    @Override
    public void setContentView(View view) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
