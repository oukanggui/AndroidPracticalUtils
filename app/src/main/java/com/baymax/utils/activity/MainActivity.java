package com.baymax.utils.activity;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseTitleBarActivity;

public class MainActivity extends BaseTitleBarActivity {

    @Override
    protected void initView() {
        showBackView(false);
        setTitle("Android使用工具集");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
