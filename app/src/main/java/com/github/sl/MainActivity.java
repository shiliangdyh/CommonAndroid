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
        ImageView imageView = (ImageView) findViewById(R.id.iv_img);

//        saveBitmap(FileUtils.getPrivateFileDir(this), BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new));
//        saveBitmap(FileUtils.getPrivateCacheDir(this), BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new));
//        saveBitmap(FileUtils.getPrivateRootDir(this), BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new));
//        saveBitmap(FileUtils.getExternalCacheDir(this), BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new));
//        saveBitmap(FileUtils.getPrivateRootDir(this), BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new));
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new);

//        imageView.setImageBitmap(bitmap);
        try {

            File test = new File(FileUtils.getExternalPublicStorageDir(this, ""), "shiliang");
            if (!test.exists()) {
                test.mkdirs();
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new);
            String s = saveBitmap(test, bitmap);

            imageView.setImageBitmap(BitmapFactory.decodeFile(s));

            File file = new File(test, "test.txt");
            FileOutputStream outputStream = null;
            if (file.exists()) {
                outputStream = new FileOutputStream(file, true);
            }else {
                outputStream = new FileOutputStream(file);
            }
            outputStream.write("hello\r\n".getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onClick(View view) {

        ShareUtils.share2WeiBo(this, "消息内容", saveBitmap(FileUtils.getExternalCacheDir(this), BitmapFactory.decodeResource(getResources(), R.drawable.about_icon_new)));
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
        return shareImagePath;
    }
}
