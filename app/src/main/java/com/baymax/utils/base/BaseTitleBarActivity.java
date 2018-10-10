package com.baymax.utils.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baymax.utils.R;


public class BaseTitleBarActivity extends AppCompatActivity {

    private FrameLayout mContainer;
    private View mBackView;
    private TextView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base_titlebar);
        if (layoutResID != R.layout.activity_base_titlebar) {
            View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
            mContainer = (FrameLayout) findViewById(R.id.fl_container);
            mContainer.addView(contentView);
        }
        initTitleLayout();
    }

    @Override
    public void setContentView(View view) {
        setContentView(R.layout.activity_base_titlebar);
        mContainer = (FrameLayout) findViewById(R.id.fl_container);
        mContainer.addView(view);
    }

    /**
     * 初始化头部Title布局
     */
    private void initTitleLayout() {
        mBackView = findViewById(R.id.iv_back);
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置Title
     *
     * @param title 显示的Title
     */
    public void setTitle(String title) {
        if (mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    /**
     * 设置Title是否显示
     *
     * @param isShow 是否显示
     */
    public void showTitle(boolean isShow) {
        setViewVisibility(mTitleView, isShow);
    }

    /**
     * 设置返回BackView是否显示
     *
     * @param isShow 是否显示
     */
    public void showBackView(boolean isShow) {
        setViewVisibility(mBackView, isShow);
    }

    /**
     * 设置View是否显示
     *
     * @param view   待处理的View
     * @param isShow 是否显示
     */
    private void setViewVisibility(View view, boolean isShow) {
        if (view != null) {
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

}
