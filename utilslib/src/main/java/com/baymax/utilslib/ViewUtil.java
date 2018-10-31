package com.baymax.utilslib;

/**
 * @author oukanggui
 * @date 2018/10/18
 * 描述 支持View相关操作的工具类Util
 */

public class ViewUtil {

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
        long timeGap = time - lastClickTime;
        if (0 < timeGap && timeGap < interval) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
