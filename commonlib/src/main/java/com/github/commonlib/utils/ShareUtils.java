package com.github.commonlib.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.github.commonlib.R;

import java.io.File;
import java.util.List;

/**
 * 分享工具
 */
public final class ShareUtils {
    private static final String TAG = "ShareUtils";

    /**
     * <provider
     * android:name="androidx.core.content.FileProvider"
     * tools:replace="android:authorities"
     * android:authorities="${applicationId}.provider"
     * android:exported="false"
     * android:grantUriPermissions="true">
     * <meta-data
     * android:name="android.support.FILE_PROVIDER_PATHS"
     * android:resource="@xml/provider_paths" />
     * </provider>
     * 调用系统分享到新浪微博
     *
     * @param context
     * @param msg     消息内容
     * @param image   图片
     */
    public static void share2WeiBo(Context context, String msg, String image) {
        String packgeName = null;
        String luancherName = null;
        PackageManager manager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        // NOTE: Provide some data to help the Intent resolver
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Query for all activities that match my filter and request that the filter used
        //  to match is returned in the ResolveInfo
        List<ResolveInfo> infos = manager.queryIntentActivities(intent,
                PackageManager.GET_RESOLVED_FILTER);
        for (ResolveInfo info : infos) {
            ActivityInfo activityInfo = info.activityInfo;

            IntentFilter filter = info.filter;
            if (filter != null && filter.hasAction(Intent.ACTION_SEND) &&
                    filter.hasCategory(Intent.CATEGORY_DEFAULT)) {
                String appName = info.loadLabel(manager)
                        .toString();
                String name = activityInfo.name;
                String pName = activityInfo.packageName;
                Drawable icon = activityInfo
                        .loadIcon(context.getPackageManager());
                if ("com.sina.weibo".equals(pName)) {
                    packgeName = pName;
                    luancherName = name;
                    break;
                }

            }
        }

        if (!TextUtils.isEmpty(packgeName) && !TextUtils.isEmpty(luancherName)) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            Uri uri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(image));
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(new File(image));
            }
            if (uri != null) {
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                shareIntent.setType("image/*");
            }
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
            ComponentName comp = new ComponentName(packgeName, luancherName);
            shareIntent.setComponent(comp);
            //自定义选择框的标题
            //startActivity(Intent.createChooser(shareIntent, "邀请好友"));
            //系统默认标题
            if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(shareIntent);
            } else {
                Toast.makeText(context, "未安装微博", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "未安装微博", Toast.LENGTH_SHORT).show();
        }

    }

}
