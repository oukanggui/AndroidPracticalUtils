package com.baymax.utilslib;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author oukanggui
 * @date 2018/10/16
 * 描述 软键盘操作工具类
 */

public class SoftInputUtil {
    /**
     * 显示软键盘
     *
     * @param context
     * @param view    接收输入内容的View
     */
    public static void showSoftInput(Context context, View view) {
        try {
            view.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null && inputManager.isActive(view)) {
                inputManager.showSoftInput(view, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     * @param view    接收输入内容的View
     */
    public static void hideSoftInput(Context context, View view) {
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null && inputManager.isActive(view)) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (inputManager != null && inputManager.isActive() && view != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
