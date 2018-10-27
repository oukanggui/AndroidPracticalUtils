package com.baymax.utils.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseTitleBarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseTitleBarActivity {

    @BindView(R.id.tl_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager mViewPager;

    private CustomFragmentPagerAdapter mPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();

    @Override
    protected void initView() {
        showBackView(false);
        setTitle("Android实用工具集");
        initData();
    }

    @Override
    protected void initData() {
        initTitles();
        initFragments();
        initViewPager();
    }

    private void initTitles() {
        mTitles.clear();
        mTitles.add("AppUtil");
        mTitles.add("DeviceUtil");
        mTitles.add("DimenUtil");
        mTitles.add("JsonUtil");
        mTitles.add("LogUtil");
        mTitles.add("NetworkUtil");
        mTitles.add("RunnableUtil");
        mTitles.add("SharedPreferencesUtil");
        mTitles.add("SoftInputUtil");
        mTitles.add("SystemUtil");
        mTitles.add("TextUtil");
        mTitles.add("TimeUtil");
        mTitles.add("ToastUtil");
        mTitles.add("ViewUtil");
    }

    private void initFragments() {
        mFragments.clear();
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
        mFragments.add(new Fragment());
    }

    private void initViewPager() {
        mPagerAdapter = new CustomFragmentPagerAdapter(MainActivity.this.getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    private static class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;
        private List<String> mTitles;

        public CustomFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments == null ? null : mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        /***
         *  为Tabayout设置主题名称
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles == null ? "" + position : mTitles.get(position);
        }

    }
}
