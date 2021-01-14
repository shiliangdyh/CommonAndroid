package com.github.sl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.github.commonlib.base.SLBaseActivity;
import com.github.commonlib.utils.FileUtils;
import com.github.commonlib.utils.LogUtils;
import com.github.commonlib.utils.ShareUtils;
import com.github.commonlib.utils.StatusBarUtils;

import java.io.File;

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

        ShareUtils.share2WeiBo(this, "消息内容", saveBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new)));
    }


    /**
     * bitmap保存为图片
     */
    private String saveBitmap(Bitmap bitmap) {
        String shareImagePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/bitmapCache/";
        File imageDir = new File(shareImagePath);
        FileUtils.deleteFolder(imageDir);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        File imageFile = new File(shareImagePath, "hebao_logo.png");
        shareImagePath = imageFile.getAbsolutePath();
        FileUtils.saveBitmap(bitmap, shareImagePath);
        return shareImagePath;
    }
}
