package com.baymax.utils.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utils.base.BaseTitleBarActivity;
import com.baymax.utils.fragment.AppUtilFragment;
import com.baymax.utils.fragment.DeviceUtilFragment;
import com.baymax.utils.fragment.DimenUtilFragment;
import com.baymax.utils.fragment.JsonUtilFragment;
import com.baymax.utils.fragment.NetworkUtilFragment;
import com.baymax.utils.fragment.RunnableUtilFragment;
import com.baymax.utils.fragment.SoftInputUtilFragment;
import com.baymax.utils.fragment.SystemUtilFragment;
import com.baymax.utils.fragment.TextUtilFragment;
import com.baymax.utils.fragment.TimeUtilFragment;
import com.baymax.utils.fragment.ViewUtilFragment;

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
    }

    @Override
    protected void initData() {
        initTitles();
        initFragments();
        initViewPager();
    }

    private void initTitles() {
        mTitles.clear();
        mTitles.add("NetworkUtil");
        mTitles.add("AppUtil");
        mTitles.add("DeviceUtil");
        mTitles.add("DimenUtil");
        mTitles.add("SoftInputUtil");
        mTitles.add("SystemUtil");
        mTitles.add("JsonUtil");
        mTitles.add("TextUtil");
        mTitles.add("TimeUtil");
        mTitles.add("ViewUtil");
        mTitles.add("RunnableUtil");
        mTitles.add("LogUtil");
        mTitles.add("SharedPreferencesUtil");
        mTitles.add("ToastUtil");
    }

    private void initFragments() {
        mFragments.clear();
        Fragment networkUtilFragment = new NetworkUtilFragment();
        setIntroduction(networkUtilFragment, getString(R.string.introduction_networkutil));

        Fragment appUtilFragment = new AppUtilFragment();
        setIntroduction(appUtilFragment, getString(R.string.introduction_apputil));

        Fragment deviceUtilFragment = new DeviceUtilFragment();
        setIntroduction(deviceUtilFragment, getString(R.string.introduction_deviceutil));

        Fragment dimenUtilFragment = new DimenUtilFragment();
        setIntroduction(dimenUtilFragment, getString(R.string.introduction_dimenutil));

        Fragment softinputUtilFragment = new SoftInputUtilFragment();
        setIntroduction(softinputUtilFragment, getString(R.string.introduction_softinpututil));

        Fragment systemUtilFragment = new SystemUtilFragment();
        setIntroduction(systemUtilFragment, getString(R.string.introduction_systemutil));

        Fragment jsonUtilFragment = new JsonUtilFragment();
        setIntroduction(jsonUtilFragment, getString(R.string.introduction_jsonutil));

        Fragment textUtilFragment = new TextUtilFragment();
        setIntroduction(textUtilFragment, getString(R.string.introduction_textutil));

        Fragment timeUtilFragment = new TimeUtilFragment();
        setIntroduction(timeUtilFragment, getString(R.string.introduction_timeutil));

        Fragment viewUtilFragment = new ViewUtilFragment();
        setIntroduction(viewUtilFragment, getString(R.string.introduction_viewutil));

        Fragment runnableUtilFragment = new RunnableUtilFragment();
        setIntroduction(runnableUtilFragment, getString(R.string.introduction_runnableutil));

        Fragment networkFragment11 = new NetworkUtilFragment();
        setIntroduction(networkFragment11, "网络工具操作类NetworkUtil，目前支持功能如下：\n" +
                "1、获取网络IP地址\n" +
                "2、判断网络是否可用\n" +
                "3、判断当前网络是否连接WiFi");
        Fragment networkFragment12 = new NetworkUtilFragment();
        setIntroduction(networkFragment12, "网络工具操作类NetworkUtil，目前支持功能如下：\n" +
                "1、获取网络IP地址\n" +
                "2、判断网络是否可用\n" +
                "3、判断当前网络是否连接WiFi");
        mFragments.add(networkUtilFragment);
        mFragments.add(appUtilFragment);
        mFragments.add(deviceUtilFragment);
        mFragments.add(dimenUtilFragment);
        mFragments.add(softinputUtilFragment);
        mFragments.add(systemUtilFragment);
        mFragments.add(jsonUtilFragment);
        mFragments.add(textUtilFragment);
        mFragments.add(timeUtilFragment);
        mFragments.add(viewUtilFragment);
        mFragments.add(runnableUtilFragment);
        mFragments.add(networkFragment11);
        mFragments.add(networkFragment12);
    }

    private void initViewPager() {
        mPagerAdapter = new CustomFragmentPagerAdapter(MainActivity.this.getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mPagerAdapter);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void setIntroduction(Fragment fragment, String strIntroduction) {
        if (fragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString(BaseContentFragment.KEY_INTRODUCTION, strIntroduction);
            fragment.setArguments(bundle);
        }
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
