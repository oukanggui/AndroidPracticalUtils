package com.baymax.utilslib;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * @author oukanggui
 * @date 2018/10/15
 * 描述：Toast操作工具类，防止出现连续点击Toast时的显示问题
 */

public class ToastUtil {

    private static Toast mToast;

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
            mToast.show();
        }
    }
}
