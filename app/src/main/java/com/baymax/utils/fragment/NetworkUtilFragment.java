package com.baymax.utils.fragment;

import android.view.View;
import android.widget.Toast;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.NetworkUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/27
 * 描述：
 */

public class NetworkUtilFragment extends BaseContentFragment {

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_network;
    }

    @OnClick({R.id.bt_get_ip, R.id.bt_network_avaliable, R.id.bt_check_wifi})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_ip:
                ToastUtil.showToast(mActivity, NetworkUtil.getIp(), Toast.LENGTH_SHORT);
                break;
            case R.id.bt_network_avaliable:
                ToastUtil.showToast(mActivity, NetworkUtil.isNetworkAvailable(mActivity) ? "当前网络可用" : "当前网络不可用", Toast.LENGTH_SHORT);
                break;
            case R.id.bt_check_wifi:
                ToastUtil.showToast(mActivity, NetworkUtil.isWifiConnected(mActivity) ? "当前网络连接WiFi" : "当前网络没有连接WiFi", Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }

    }
}
