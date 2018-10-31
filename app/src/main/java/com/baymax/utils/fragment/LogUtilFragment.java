package com.baymax.utils.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.LogUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/31
 * 描述 LogUtil功能操作演示Fragment
 */

public class LogUtilFragment extends BaseContentFragment {
    @BindView(R.id.bt_review)
    Button btnReview;

    @Override
    protected void initView(View rootView) {
        btnReview.setEnabled(false);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_log;
    }

    @OnClick({R.id.bt_log, R.id.bt_review})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_log:
                LogUtil.i("baymax", "Hello World");
                LogUtil.d("baymax", "Hello World");
                LogUtil.v("baymax", "Hello World");
                LogUtil.w("baymax", "Hello World");
                LogUtil.e("baymax", "Hello World");
                btnReview.setEnabled(true);
                break;
            case R.id.bt_review:
                if (!btnReview.isEnabled()) {
                    ToastUtil.showToast(mActivity, "请先打印日志");
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = Uri.fromFile(LogUtil.getCurrentLogFile());
                intent.setDataAndType(uri, "text/plain");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
