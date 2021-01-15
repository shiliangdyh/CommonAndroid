package com.github.commonlib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public final class FileUtils {
    /**
     * 删除文件夹中的文件
     *
     * @param file
     */
    public static void deleteFolder(File file) {
        try {
            if (file != null && file.isFile()) {
                file.delete();
            }
            if (file != null && file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null && files.length > 0) {
                    for (File file1 : files) {
                        if (file.isFile()) {
                            file.delete();
                        } else {
                            deleteFolder(file1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String saveBitmap(Bitmap bm, String path)
    {
        FileOutputStream out = null;
        try {
            File file = new File(path);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            if (file.exists()){
                file.delete();
            }

            out = new FileOutputStream(file);
            if (bm.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }finally {
            CloseUtils.close(out);
        }

    }

    /**
     * 获取Android私有files目录
     * data/data/package_name/files/
     * 应用卸载后删除
     * 不需要权限
     *
     * @param context
     * @return
     */
    public static File getPrivateFileDir(Context context) {
        return context.getFilesDir();
    }

    /**
     * 获取Android私有缓存目录
     * data/data/package_name/cache/
     * 应用卸载后删除
     * 不需要权限
     *
     * @param context
     * @return
     */
    public static File getPrivateCacheDir(Context context) {
        return context.getCacheDir();
    }

    /**
     * 获取Android私有根目录
     * data/data/package_name/
     * 应用卸载后删除
     * 不需要权限
     *
     * @param context
     * @return
     */
    public static File getPrivateRootDir(Context context) {
        return context.getFilesDir().getParentFile();
    }

    /**
     * 获取Android SD卡目录
     * Android/data/package_name/files/{@param relativePath}/
     * 应用卸载后删除
     * 不需要权限
     *
     * @param context
     * @param relativePath
     * @return
     */
    public static File getExternalFileDir(Context context, @NonNull String relativePath) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return context.getExternalFilesDir(relativePath);
        }
        return new File(getPrivateFileDir(context), relativePath);
    }

    /**
     * 获取Android SD卡目录
     * Android/data/package_name/cache/
     * 应用卸载后删除
     * 不需要权限
     *
     * @param context
     * @return
     */
    public static File getExternalCacheDir(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return context.getExternalCacheDir();
        } else {
            return getPrivateCacheDir(context);
        }
    }


    /**
     * 获取Android SD卡目录
     * 10.0中 获取到的是 Android/sandbox/package_name/relativePath/   10.0沙盒机制，卸载删除
     * 10.0以下获取到的是 sd根目录/relativePath/ 卸载存在
     * 需要权限
     *
     * @param context
     * @param relativePath
     * @return
     */
    public static File getExternalPublicStorageDir(Context context, @NonNull String relativePath) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //10.0
                return Environment.getExternalStoragePublicDirectory(relativePath);
            } else {
                return new File(Environment.getExternalStorageDirectory(), relativePath);
            }
        }
        return getExternalFileDir(context, relativePath);
    }
}
