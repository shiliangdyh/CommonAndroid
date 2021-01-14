package com.github.commonlib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * APK 安装工具类接口
 */
public final class APKInstallUtils {
    /**
     * APK 安装工具类接口
     * @param ctx 当前上下文
     * @param packagename 包名
     * @param apkVerCode apk版本号
     * @return
     */
    private static boolean checkAPKInstalled(Context ctx, String packagename, int apkVerCode) {
        PackageManager pm = ctx.getPackageManager();
        PackageInfo apkinfo;
        try {
            apkinfo = pm.getPackageInfo(packagename, 0);
            return apkinfo != null && (apkVerCode < 0 || (apkinfo.versionCode >= apkVerCode));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
