package com.baymax.utils.fragment;

import android.view.View;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.DeviceUtil;
import com.baymax.utilslib.TextUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/29
 * 描述：DeviceUtil相关操作演示Fragment
 */

public class DeviceUtilFragment extends BaseContentFragment {
    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_device;
    }

    @OnClick({R.id.bt_get_screen_info, R.id.bt_get_imsi, R.id.bt_get_imei, R.id.bt_get_mac_address})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_screen_info:
                ToastUtil.showToast(mActivity, "屏幕宽度(px)：" + DeviceUtil.getScreenWidth(mActivity)
                        + "\n屏幕高度(px)：" + DeviceUtil.getScreenHeight(mActivity));
                break;
            case R.id.bt_get_imsi:
                String imsi = DeviceUtil.getImsi(mActivity);
                ToastUtil.showToast(mActivity, TextUtil.isEmpty(imsi) ? "获取IMSI为空，请确认是否申请了READ_PHONE_STATE权限" : imsi);
                break;
            case R.id.bt_get_imei:
                String imei = DeviceUtil.getImei(mActivity);
                ToastUtil.showToast(mActivity, TextUtil.isEmpty(imei) ? "获取IMEI为空，请确认是否申请了READ_PHONE_STATE权限" : imei);
                break;
            case R.id.bt_get_mac_address:
                ToastUtil.showToast(mActivity, DeviceUtil.getMacAddress());
                break;
            default:
                break;
        }

    }
}
