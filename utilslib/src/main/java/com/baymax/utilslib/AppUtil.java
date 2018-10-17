package com.baymax.utilslib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * @author oukanggui
 * @date 2018/10/11
 * 描述 APP相关信息操作工具类，获取APP诸如Package等信息
 */

public class AppUtil {

    /**
     * 获取第三方应用的app version name
     *
     * @param context
     * @param packageName 待获取应用信息的包名
     * @return app version
     */
    public static String getAppVersionName(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取应用本身的app version name
     *
     * @param context
     * @return app version name
     */
    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName());
    }

    /**
     * 获取第三方应用的app version code
     *
     * @param context
     * @param packageName 待获取应用信息的包名
     * @return app version code
     */
    public static int getAppVersionCode(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取应用本身的app version code
     *
     * @param context
     * @return app version code
     */
    public static int getAppVersionCode(Context context) {
        return getAppVersionCode(context, context.getPackageName());
    }

    /**
     * 判断APP在手机的安装状态
     *
     * @param context     上下文
     * @param packageName app的包名
     * @param versionCode app的版本号
     * @return 比较结果：
     * <br> 0 : 和本地安装的版本一样
     * <br> 1 : 比本地安装的版本要大
     * <br>-1 : 比本地安装的版本要小
     * <br>-2 : 本地没有安装或者包名和版本号格式有误
     */
    public static int checkPackageStatus(Context context, String packageName, int versionCode) {
        if (TextUtil.isEmpty(packageName)) {
            return -2;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return -2;
        } else {
            int temp = versionCode - packageInfo.versionCode;
            if (temp > 0) {
                temp = 1;
            } else if (temp < 0) {
                temp = -1;
            } else {
                temp = 0;
            }
            return temp;
        }
    }

    /**
     * 检测该包名所对应的应用是否存在,判断是否已安装
     *
     * @param context
     * @param packageName 当判断应用的包名
     * @return
     */
    public static boolean checkPackage(Context context, String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return false;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 启动第三方APP
     *
     * @param context
     * @param packageName 待启动APP的包名
     * @return true:应用可以启动，false：应用不支持打开
     */
    public static boolean openApp(Context context, String packageName) {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mainIntent.setPackage(packageName);
        final List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(mainIntent, 0);
        if (apps != null) {
            int count = apps.size();
            for (int i = 0; i < count; i++) {
                final ResolveInfo resolveInfo = apps.get(i);
                final ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (packageName.equals(activityInfo.packageName)) {
                    // matches.add(resolveInfo);
                    String className = activityInfo.name;
                    LogUtil.i("AppUtil", "startPackage: " + packageName + " & " + className);
                    ComponentName cn = new ComponentName(packageName, className);
                    final Intent it = new Intent(Intent.ACTION_MAIN);
                    it.addCategory(Intent.CATEGORY_LAUNCHER);
                    it.setComponent(cn);
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                    return true;
                }
            }
        }
        ToastUtil.showToast(context, "该应用不支持直接打开");
        return false;
    }

}
