package com.baymax.utils.fragment;

import android.view.View;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.AppUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/29
 * 描述：AppUtil使用演示Fragment
 */

public class AppUtilFragment extends BaseContentFragment {
    private static final String PACKAGE_NAME_WEIXIN = "com.tencent.mm";

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_app;
    }

    @OnClick({R.id.bt_get_versioninfo_shelf, R.id.bt_get_versioninfo, R.id.bt_get_install_status, R.id.bt_open_app})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_versioninfo_shelf:
                /**
                 * AppUtil.getAppVersionName(Context context)
                 * AppUtil.getAppVersionCode(Context context)
                 * */
                ToastUtil.showToast(mActivity, "版本名称：" + AppUtil.getAppVersionName(mActivity) + "\n版本号：" + AppUtil.getAppVersionCode(mActivity));
                break;
            case R.id.bt_get_versioninfo:
                /**
                 * AppUtil.getAppVersionName(Context context, String packageName)
                 * AppUtil.getAppVersionCode(Context context, String packageName)
                 * */
                ToastUtil.showToast(mActivity, "版本名称：" + AppUtil.getAppVersionName(mActivity, PACKAGE_NAME_WEIXIN) +
                        "\n版本号：" + AppUtil.getAppVersionCode(mActivity, PACKAGE_NAME_WEIXIN));
                break;
            case R.id.bt_get_install_status:
                /**
                 * AppUtil.checkPackageInstalled(Context context, String packageName) or
                 * AppUtil.checkPackageStatus(Context context, String packageName, int versionCode)
                 * */
                boolean isInstalled = AppUtil.checkPackageInstalled(mActivity, PACKAGE_NAME_WEIXIN);
                ToastUtil.showToast(mActivity, isInstalled ? "微信已安装" : "微信还没有安装");
                break;
            case R.id.bt_open_app:
                /**
                 * AppUtil.openApp(Context context, String packageName)
                 * */
                AppUtil.openApp(mActivity, PACKAGE_NAME_WEIXIN);
                break;
            default:
                break;
        }

    }
}
