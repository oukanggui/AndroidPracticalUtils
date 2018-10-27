package com.baymax.utils.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    Unbinder mBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mBinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
        }
    }

    /**
     * 子类页面Activity重写初始化布局控件
     */
    protected abstract void initView();

    /**
     * 子类页面Activity重写初始化数据
     */
    protected abstract void initData();

    /**
     * 子类页面Activity重写该方法返回页面布局layoutid
     */
    protected abstract int getLayoutResId();


}
