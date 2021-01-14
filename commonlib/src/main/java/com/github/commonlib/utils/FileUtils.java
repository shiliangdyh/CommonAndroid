package com.github.commonlib.utils;

import android.graphics.Bitmap;

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
                for (File file1 : files) {
                    if (file.isFile()) {
                        file.delete();
                    } else {
                        deleteFolder(file1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String saveBitmap(Bitmap bm, String path)
    {
        File file = new File(path);
        try {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            if (file.exists()){
                file.delete();
            }

            FileOutputStream out = new FileOutputStream(file);
            if (bm.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();

    }
}
