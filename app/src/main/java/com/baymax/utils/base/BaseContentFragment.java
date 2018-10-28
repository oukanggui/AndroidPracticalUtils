package com.baymax.utils.base;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baymax.utils.R;


/**
 * @author oukanggui
 * @date 2018/10/27
 * 描述：
 */

public abstract class BaseContentFragment extends BaseFragment {
    protected TextView mContentView;
    protected FrameLayout mContainer;
    public static final String KEY_INTRODUCTION = "KEY_INTRODUCTION";

    @Override
    protected void initData() {
        if (mContentView != null && getArguments() != null) {
            mContentView.setText(getArguments().getString(KEY_INTRODUCTION, "暂无介绍"));
        }
    }

    @Override
    protected View createRootView(LayoutInflater inflater, @Nullable ViewGroup container, int layoutResId) {
        View viewRoot = inflater.inflate(layoutResId, container, false);
        mContentView = viewRoot.findViewById(R.id.tv_introduction);
        mContainer = viewRoot.findViewById(R.id.fl_container);
        //获取子布局
        View contentView = inflater.inflate(getLayoutResId(), null, false);
        mContainer.addView(contentView);
        return viewRoot;
    }

    @Override
    protected int getRootViewLayoutResId() {
        return R.layout.fragment_base_content;
    }
}
