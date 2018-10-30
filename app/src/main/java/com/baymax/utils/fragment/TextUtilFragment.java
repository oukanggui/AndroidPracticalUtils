package com.baymax.utils.fragment;

import android.view.View;
import android.widget.EditText;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.TextUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/30
 * 描述：TextUtil操作演示Fragment
 */

public class TextUtilFragment extends BaseContentFragment {

    @BindView(R.id.et_input)
    EditText etInput;

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_text;
    }

    @OnClick({R.id.bt_empty, R.id.bt_blank, R.id.bt_phone, R.id.bt_email})
    public void onViewClick(View view) {
        String input = etInput.getText().toString();
        switch (view.getId()) {
            case R.id.bt_empty:
                if (TextUtil.isEmpty(input)) {
                    ToastUtil.showToast(mActivity, "输入内容为空");
                } else {
                    ToastUtil.showToast(mActivity, "输入内容不为空");
                }
                break;
            case R.id.bt_blank:
                if (TextUtil.isBlank(input)) {
                    ToastUtil.showToast(mActivity, "输入内容全为空格");
                } else {
                    ToastUtil.showToast(mActivity, "输入内容不全为空格");
                }
                break;
            case R.id.bt_phone:
                if (TextUtil.isPhoneNumber(input)) {
                    ToastUtil.showToast(mActivity, "输入内容是手机号码");
                } else {
                    ToastUtil.showToast(mActivity, "手机号码格式输入有误");
                }
                break;
            case R.id.bt_email:
                if (TextUtil.isEmail(input)) {
                    ToastUtil.showToast(mActivity, "输入内容是邮箱");
                } else {
                    ToastUtil.showToast(mActivity, "邮箱格式输入有误");
                }
                break;
            default:
                break;
        }

    }
}
