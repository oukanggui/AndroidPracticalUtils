package com.baymax.utils.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baymax.utils.R;
import com.baymax.utilslib.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author oukanggui
 * @date 2018/10/27
 * 描述：BaseFragment
 */

public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    Unbinder mBinder;
    public Activity mActivity;
    /**
     * 缓存Fragment view
     */
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        super.onAttach(context);
        LogUtil.i(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreateView");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(rootView);
            initView(rootView);
            initData();
        } else {
            //需要removerootView，防止View被重复添加
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBinder != null) {
            mBinder.unbind();
        }
    }

    /**
     * 子类页面Activity重写初始化布局控件
     */
    protected abstract void initView(View rootView);

    /**
     * 子类页面Activity重写初始化数据
     */
    protected abstract void initData();

    /**
     * 子类页面Activity重写该方法返回页面布局layoutid
     */
    protected abstract int getLayoutResId();

    /**
     * 子类页面Activity重写该方法返回页面布局RootView
     */
    protected void createRootViewAndBuild(LayoutInflater inflater, @Nullable ViewGroup container, int layoutResId) {
        rootView = inflater.inflate(layoutResId, container, false);
        ButterKnife.bind(rootView);
    }

    /**
     * 页面跟布局layoutid，默认为页面布局layoutid，如果需要重设，重写此方式即可
     */
    protected int getRootViewLayoutResId() {
        return getLayoutResId();
    }
}
