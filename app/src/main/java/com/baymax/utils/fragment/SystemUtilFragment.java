package com.baymax.utils.fragment;

import android.view.View;
import android.widget.EditText;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.SystemUtil;
import com.baymax.utilslib.TextUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/29
 * 描述：SystemUtil操作说明Fragment
 */

public class SystemUtilFragment extends BaseContentFragment {
    @BindView(R.id.et_share_title)
    EditText etTitle;
    @BindView(R.id.et_share_text)
    EditText etText;

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_system;
    }

    @OnClick({R.id.bt_check_sdcard, R.id.bt_get_sd_path, R.id.bt_get_sys_model, R.id.bt_get_sys_version,
            R.id.bt_get_sys_sdk_version, R.id.bt_open_sys_broswer, R.id.bt_open_share_chooser})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_check_sdcard:
                ToastUtil.showToast(mActivity, SystemUtil.isSDCardAvailable() ? "SD卡可用" : "SD卡不可用");
                break;
            case R.id.bt_get_sd_path:
                ToastUtil.showToast(mActivity, SystemUtil.getSDCardPath());
                break;
            case R.id.bt_get_sys_model:
                ToastUtil.showToast(mActivity, SystemUtil.getSystemModel());
                break;
            case R.id.bt_get_sys_version:
                ToastUtil.showToast(mActivity, SystemUtil.getSystemVersion());
                break;
            case R.id.bt_get_sys_sdk_version:
                ToastUtil.showToast(mActivity, SystemUtil.getSDKVersion() + "");
                break;
            case R.id.bt_open_sys_broswer:
                SystemUtil.openSystemBrowser(mActivity, "http://www.baidu.com");
                break;
            case R.id.bt_open_share_chooser:
                if (TextUtil.isEmpty(etTitle.getText().toString())) {
                    ToastUtil.showToast(mActivity, "分享标题不能为空！");
                    return;
                }
                if (TextUtil.isEmpty(etText.getText().toString())) {
                    ToastUtil.showToast(mActivity, "分享内容不能为空！");
                    return;
                }
                SystemUtil.openSystemShareChooser(mActivity, etTitle.getText().toString(), etText.getText().toString());
                break;
            default:
                break;
        }

    }
}
