package com.baymax.utils.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.LogUtil;
import com.baymax.utilslib.ToastUtil;

import java.io.File;

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
                try {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //需要intent需要加入下面flags,否则无法打开文件
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Uri uri = getUriForFile(mActivity, LogUtil.getCurrentLogFile());
                    intent.setDataAndType(uri, "text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast(mActivity, "手机上没有可打开该类型文件的软件");
                }
                break;
            default:
                break;
        }
    }

    private static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.baymax.utils.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

}
