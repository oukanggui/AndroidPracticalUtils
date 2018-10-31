package com.baymax.utils.fragment;

import android.view.View;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.ToastUtil;
import com.baymax.utilslib.ViewUtil;

import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/31
 * 描述 ViewUtil操作说明类
 */

public class ViewUtilFragment extends BaseContentFragment {
    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_view;
    }

    @OnClick({R.id.bt_click_double_check, R.id.bt_click_double_check_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_click_double_check:
                if (ViewUtil.isFastDoubleClick(800)) {
                    ToastUtil.showToast(mActivity, "是快速点击,点击无效");
                } else {
                    ToastUtil.showToast(mActivity, "不是快速点击,点击有效");
                }
                break;
            case R.id.bt_click_double_check_reset:
                if (ViewUtil.isFastDoubleClick(800, true)) {
                    ToastUtil.showToast(mActivity, "是快速点击,点击无效");
                } else {
                    ToastUtil.showToast(mActivity, "不是快速点击,点击有效");
                }
                break;
            default:
                break;
        }
    }
}
