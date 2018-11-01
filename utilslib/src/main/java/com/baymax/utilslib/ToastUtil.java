package com.baymax.utilslib;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * @author oukanggui
 * @date 2018/10/15
 * 描述：Toast操作工具类，防止出现连续点击Toast时的显示问题
 */

public class ToastUtil {
    private static final String TAG = "ToastUtil";
    private static Toast mToast;
    private static Field sField_TN;
    private static Field sField_TN_Handler;
    private static boolean sIsHookFieldInit = false;
    private static final String FIELD_NAME_TN = "mTN";
    private static final String FIELD_NAME_HANDLER = "mHandler";

    /**
     * 非阻塞式显示Toast,防止出现连续点击Toast时的显示问题
     * @param context  context，可以是Application或Activity对象
     * @param text     Toast上提示的消息
     * @param duration Toast展示时长控制，有两个值：Toast.LENGTH_SHORT（2秒）、Toast.LENGTH_LONG（3.5秒）
     */
    public static void showToast(final Context context, final CharSequence text, final int duration) {
        ToastRunnable toastRunnable = new ToastRunnable(context, text, duration);
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity != null && !activity.isFinishing()) {
                activity.runOnUiThread(toastRunnable);
            }
        } else {
            Handler handler = new Handler(context.getMainLooper());
            handler.post(toastRunnable);
        }
    }

    /**
     * 非阻塞试显示Toast,默认时长控制为Toast.LENGTH_SHORT（2秒）
     * @param context  context，可以是Application或Activity对象
     * @param text     Toast上提示的消息
     */
    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 取消Toast的显示
     */
    public static void cancelToast() {
        Looper looper = Looper.getMainLooper();
        if (looper.getThread() == Thread.currentThread()) {
            mToast.cancel();
        } else {
            new Handler(looper).post(new Runnable() {
                @Override
                public void run() {
                    mToast.cancel();
                }
            });
        }
    }

    /**
     * Hook Toast,修复在7.x手机上跑monkey的时候，Toast低概率出现BadTokenException的异常
     *
     * @param toast
     */
    private static void hookToast(Toast toast) {
        if (!isNeedHook()) {
            return;
        }
        try {
            if (!sIsHookFieldInit) {
                sField_TN = Toast.class.getDeclaredField(FIELD_NAME_TN);
                sField_TN.setAccessible(true);
                sField_TN_Handler = sField_TN.getType().getDeclaredField(FIELD_NAME_HANDLER);
                sField_TN_Handler.setAccessible(true);
                sIsHookFieldInit = true;
            }
            Object tn = sField_TN.get(toast);
            Handler originHandler = (Handler) sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn, new SafelyHandlerWarpper(originHandler));
        } catch (Exception e) {
            LogUtil.e(TAG, "Hook toast exception=" + e);
        }
    }

    /**
     * 判断Toast是否需要Hook，目前只针对7.x(api = 24/25)版本系统手机进行hook
     *
     * @return true for need hook to fit system bug,false for don't need hook
     */
    private static boolean isNeedHook() {
        return SystemUtil.getSDKVersion() == Build.VERSION_CODES.N_MR1 ||
                SystemUtil.getSDKVersion() == Build.VERSION_CODES.N;
    }

    private static class ToastRunnable implements Runnable {
        private Context context;
        private CharSequence text;
        private int duration;

        public ToastRunnable(Context context, CharSequence text, int duration) {
            this.context = context;
            this.text = text;
            this.duration = duration;
        }

        @Override
        public void run() {
            if (mToast == null) {
                mToast = Toast.makeText(context, text, duration);
            } else {
                mToast.setText(text);
                mToast.setDuration(duration);
            }
            hookToast(mToast);
            mToast.show();
        }
    }

    private static class SafelyHandlerWarpper extends Handler {
        private Handler originHandler;

        public SafelyHandlerWarpper(Handler originHandler) {
            this.originHandler = originHandler;
        }

        @Override
        public void dispatchMessage(Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
                LogUtil.e(TAG, "Catch system toast exception=" + e);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            //需要委托给原Handler执行
            if (originHandler != null) {
                originHandler.handleMessage(msg);
            }
        }
    }
}
