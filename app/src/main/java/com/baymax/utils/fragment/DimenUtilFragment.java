package com.baymax.utils.fragment;

import android.view.View;
import android.widget.EditText;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.DimenUtil;
import com.baymax.utilslib.TextUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/29
 * 描述：DimenUtil操作演示Fragment
 */

public class DimenUtilFragment extends BaseContentFragment {
    @BindView(R.id.et_dimen_input)
    EditText etInput;

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_dimen;
    }

    @OnClick({R.id.bt_dp2px, R.id.bt_px2dp, R.id.bt_sp2px, R.id.bt_px2sp})
    public void onViewClick(View view) {
        //检测输入的数值是否合规
        String input = etInput.getText().toString();
        if (TextUtil.isEmpty(input)) {
            ToastUtil.showToast(mActivity, "请输入带转换数值");
            return;
        }
        float inputValue;
        try {
            inputValue = Float.parseFloat(input);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            ToastUtil.showToast(mActivity, "请输入有效数值");
            return;
        }
        int resultValue;
        switch (view.getId()) {
            case R.id.bt_dp2px:
                resultValue = DimenUtil.dp2px(mActivity, inputValue);
                ToastUtil.showToast(mActivity, inputValue + "dp=" + resultValue + "px");
                break;
            case R.id.bt_px2dp:
                resultValue = DimenUtil.px2dp(mActivity, inputValue);
                ToastUtil.showToast(mActivity, inputValue + "px=" + resultValue + "dp");
                break;
            case R.id.bt_sp2px:
                resultValue = DimenUtil.sp2px(mActivity, inputValue);
                ToastUtil.showToast(mActivity, inputValue + "sp=" + resultValue + "px");
                break;
            case R.id.bt_px2sp:
                resultValue = DimenUtil.px2sp(mActivity, inputValue);
                ToastUtil.showToast(mActivity, inputValue + "px=" + resultValue + "sp");
                break;
            default:
                break;
        }

    }
}
