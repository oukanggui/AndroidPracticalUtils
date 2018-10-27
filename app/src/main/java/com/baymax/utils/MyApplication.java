package com.baymax.utils;

import android.app.Application;

import com.baymax.utilslib.LogUtil;

/**
 * @author oukanggui
 * @date 2018/10/27
 * 描述：
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init(this, true, true);
    }
}
