package com.github.commonlib.base;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.commonlib.utils.StatusBarUtils;

public class SLBaseActivity extends AppCompatActivity {
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
