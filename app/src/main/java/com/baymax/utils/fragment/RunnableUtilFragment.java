package com.baymax.utils.fragment;

import android.content.Context;
import android.view.View;

import com.baymax.utils.R;
import com.baymax.utils.base.BaseContentFragment;
import com.baymax.utilslib.RunnableUtil;
import com.baymax.utilslib.ToastUtil;

import butterknife.OnClick;

/**
 * @author oukanggui
 * @date 2018/10/31
 * 描述 RunnableUtil操作使用说明类
 */

public class RunnableUtilFragment extends BaseContentFragment {
    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_runnable;
    }

    @OnClick({R.id.bt_run_thread1_wait, R.id.bt_run_thread2_wait, R.id.bt_run_thread3_instant})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_run_thread1_wait:
                ThreadRunnable threadRunnable1 = new ThreadRunnable(mActivity, "thread-1-wait");
                RunnableUtil.runTask(threadRunnable1);
                break;
            case R.id.bt_run_thread2_wait:
                ThreadRunnable threadRunnable2 = new ThreadRunnable(mActivity, "thread-2-wait");
                RunnableUtil.runTask(threadRunnable2);
                break;
            case R.id.bt_run_thread3_instant:
                ThreadRunnable threadRunnable3 = new ThreadRunnable(mActivity, "thread-3-instant");
                RunnableUtil.runTask(threadRunnable3, true);
                break;
            default:
                break;
        }
    }

    private static class ThreadRunnable implements Runnable {
        private String name;
        private Context context;

        public ThreadRunnable(Context context, String name) {
            this.name = name;
            this.context = context;
        }

        @Override
        public void run() {
            ToastUtil.showToast(context, "线程【" + name + "】准备执行");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ToastUtil.showToast(context, "线程【" + name + "】已执行完成");
        }
    }
}
