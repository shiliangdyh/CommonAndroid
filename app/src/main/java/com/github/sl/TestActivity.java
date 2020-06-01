package com.github.sl;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.commonlib.base.SLBaseActivity;

public class TestActivity extends SLBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
