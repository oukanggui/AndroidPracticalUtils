package com.baymax.utilslib;

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
    public static String getSysModel() {
        return Build.MODEL;
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static String getSysVersion() {
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

}
