package com.baymax.utils.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.SoftInputUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/29
 * 描述：SoftInputUtil操作演示Fragment
 */

public class SoftInputUtilFragment extends BaseContentFragment {
    @BindView(R.id.et_input)
    EditText etInput;

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_softinput;
    }

    @OnClick({R.id.bt_show, R.id.bt_hide})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.bt_show:
                etInput.setVisibility(View.VISIBLE);
                SoftInputUtil.showSoftInput(mActivity, etInput);
                break;
            case R.id.bt_hide:
                etInput.setVisibility(View.GONE);
                etInput.setText("");
                SoftInputUtil.hideSoftInput(mActivity, etInput);
                break;
            default:
                break;
        }

    }
}
