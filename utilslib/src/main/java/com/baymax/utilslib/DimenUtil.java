package com.baymax.utilslib;

import android.content.Context;

/**
 * @author oukanggui
 * @date 2018/10/16
 * 描述 Dimen尺寸转换工具类,支持dp与px、sp与px之间的互换等
 */

public class DimenUtil {

    /**
     * 转换dp为px
     *
     * @param context
     * @param dipValue
     * @return px
     */
    public static int dp2px(Context context, int dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f * (dipValue >= 0 ? 1 : -1));
    }

    /**
     * 转换px为dp
     *
     * @param context
     * @param pxValue
     * @return dp
     */
    public static int px2dp(Context context, int pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f * (pxValue >= 0 ? 1 : -1));
    }

    /**
     * 转换sp为px
     *
     * @param context
     * @param spValue
     * @return px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 转换px为sp
     *
     * @param context
     * @param pxValue
     * @return sp
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}
