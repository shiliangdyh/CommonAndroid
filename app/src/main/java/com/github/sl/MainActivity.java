package com.github.sl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.commonlib.base.SLBaseActivity;
import com.github.commonlib.utils.FileUtils;
import com.github.commonlib.utils.LogUtils;
import com.github.commonlib.utils.ShareUtils;
import com.github.commonlib.utils.StatusBarUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

        ShareUtils.share2WeiBo(this, "消息内容", saveBitmap(FileUtils.getExternalFileDir(this, "null"), BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new)));
    }


    private int i = 0;
    /**
     * bitmap保存为图片
     */
    private String saveBitmap(File file, Bitmap bitmap) {
        String shareImagePath = file.getAbsolutePath();
        File imageDir = new File(shareImagePath);
        FileUtils.deleteFolder(imageDir);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        File imageFile = new File(shareImagePath, "hebao_logo.png");
        shareImagePath = imageFile.getAbsolutePath();
        String s = FileUtils.saveBitmap(bitmap, shareImagePath);
        Log.d(TAG, "saveBitmap: success " + i++ + s) ;
        ImageView imageView = (ImageView) findViewById(R.id.iv_img);
        imageView.setImageBitmap(BitmapFactory.decodeFile(s));
        return shareImagePath;
    }
}
