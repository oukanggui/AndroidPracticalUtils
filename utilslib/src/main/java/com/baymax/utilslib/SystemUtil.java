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


    private static long lastClickTime;

    /**
     * 防止控件被快速点击多次照成重复事件
     *
     * @return true：判定为两次点击之间为快速点击，否为，false
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(800);
    }

    /**
     * 防止控件被快速点击多次照成重复事件
     *
     * @param isReset 需要是要重置（由于记录上次点击时间是采用static的形式，防止在两个不同控件之间快速点击存在误判）
     * @return true：判定为两次点击之间为快速点击，否为，false
     */
    public static boolean isFastDoubleClick(boolean isReset) {
        return isFastDoubleClick(800, isReset);
    }

    /**
     * 防止控件被快速点击多次照成重复事件
     *
     * @param interval 两次点击之间的时间间隔
     * @return true：判定为两次点击之间为快速点击，否为，false
     */
    public static boolean isFastDoubleClick(long interval) {
        return isFastDoubleClick(interval, false);
    }

    /**
     * 防止控件被快速点击多次照成重复事件
     *
     * @param interval 两次点击之间的时间间隔
     * @param isReset  需要是要重置（由于记录上次点击时间是采用static的形式，防止在两个不同控件之间快速点击存在误判）
     * @return true：判定为两次点击之间为快速点击，否为，false
     */
    public static boolean isFastDoubleClick(long interval, boolean isReset) {
        if (isReset) {
            lastClickTime = 0;
        }
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < interval) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
