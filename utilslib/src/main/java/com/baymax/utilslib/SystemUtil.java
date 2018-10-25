package com.baymax.utilslib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

/**
 * @author oukanggui
 * @date 2018/10/16
 * 描述 System操作工具类
 */

public class SystemUtil {
    /**
     * 检测储存卡是否可用
     *
     * @return true if the SDCard is available,otherwise false
     */
    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SDCard路径
     *
     * @return SDCard path if the SDCard is available,otherwise return null
     */
    public static String getSDCardPath() {
        if (isSDCardAvailable()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取系统型号
     *
     * @return
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取系统sdk版本号
     *
     * @return
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 调用系统浏览器打开网页
     *
     * @param context
     * @param url     待打开的url
     */
    public static void openSystemBrowser(Context context, String url) {
        if (context == null || TextUtil.isEmpty(url)) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtil.e("SystemUtil", "Open system browser exception=" + e);
        }
    }

    /**
     * 打开系统分享选择器
     *
     * @param context
     * @param title   分享主题title
     * @param text    分享内容text
     */
    public static void openSystemShareChooser(Context context, String title, String text) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        // 分享发送到数据类型
        intent.setType("text/plain");
        // 分享的主题
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        // 分享的内容
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 目标应用选择对话框的标题
        context.startActivity(Intent.createChooser(intent, title));
    }

}
