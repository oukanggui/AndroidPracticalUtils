package com.baymax.utils.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView();
    }

    /**
     * 子类页面Activity重写初始化布局控件
     */
    protected abstract void initView();

    /**
     * 子类页面Activity重写该方法返回页面布局layoutid
     */
    protected abstract int getLayoutResId();
}
