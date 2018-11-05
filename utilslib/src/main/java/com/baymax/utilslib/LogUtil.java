package com.baymax.utilslib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author oukanggui
 * @date 2018/10/15
 * 描述：Log打印相关操作工具类，提供日志开关控制
 */

public class LogUtil {
    private final static String TAG = "LogUtil";
    private static Context mContext;
    /**
     * log文件存在的文件目录
     */
    private final static String LOG_DIRNAME = "log-dir";
    /**
     * log文件存在的文件名
     */
    private final static String LOG_FILENAME = "test_log";
    /**
     * log文件保存为.txt文本格式
     */
    private final static String LOG_FILEEXT = ".txt";
    /**
     * log文件保存路径
     */
    private static String LOG_PATH_SDCARD_DIR;
    /**
     * log文件File对象
     */
    private static File mLogFile;
    /**
     * 开关1：是否打印日志，默认关闭
     */
    public static boolean mIsShowLog = false;
    /**
     * 开关2：日志是否写到文件，默认关闭
     */
    public static boolean mIsWriteToFile = false;
    /**
     * 待写日志的队列
     */
    private static LinkedBlockingQueue<LogItem> gLogQueue;
    /**
     * 写日志线程
     */
    private static Thread gWriteLogThread;
    /**
     * 也就是10M
     */
    private final static long LOGFILE_LIMIT = 10 * 1024 * 1024L;
    private final static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private final static SimpleDateFormat DATEFORMAT1 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

    /**
     * 初始化日志操作--应在Application中进行初始化
     *
     * @param context
     * @param isShowLog     是否打印日志
     * @param isWriteToFile 是否把日志写到文件（需要读写文件的权限）
     */
    public static void init(Context context, boolean isShowLog, boolean isWriteToFile) {
        mContext = context.getApplicationContext();
        mIsShowLog = isShowLog;
        mIsWriteToFile = isWriteToFile;
        if (mIsShowLog && mIsWriteToFile) {
            // 判断SD卡是否可用
            if (SystemUtil.isSDCardAvailable()) {
                //SD卡可用：SDCard/Log--或者可以定义到自己应用名称的目录下
                LOG_PATH_SDCARD_DIR = SystemUtil.getSDCardPath() + File.separator + LOG_DIRNAME;
            } else {
                //SD卡不可用：data/data/应用的包名/files/
                LOG_PATH_SDCARD_DIR = mContext.getFilesDir().getAbsolutePath();
            }
            createLogFile();
        }
    }

    static class LogItem {
        String level;
        String tag;
        String msg;

        LogItem(String level, String tag, String msg) {
            this.level = level;
            this.tag = tag;
            this.msg = msg;
        }
    }

    public static void i(String tag, String msg) {
        if (mIsShowLog) {
            Log.i(tag, msg == null ? "" : msg);
        }
        writeLogFile("INFO", tag, msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (mIsShowLog) {
            Log.i(tag, msg == null ? "" : msg, tr);
        }
        writeLogFile("INFO", tag, msg);
    }

    public static void d(String tag, String msg) {
        if (mIsShowLog) {
            Log.d(tag, msg == null ? "" : msg);
        }
        writeLogFile("DEBUG", tag, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (mIsShowLog) {
            Log.d(tag, msg == null ? "" : msg, tr);
        }
        writeLogFile("DEBUG", tag, msg);
    }

    public static void e(String tag, String msg) {
        if (mIsShowLog) {
            Log.e(tag, msg == null ? "" : msg);
        }
        writeLogFile("ERROR", tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (mIsShowLog) {
            Log.e(tag, msg == null ? "" : msg, tr);
        }
        writeLogFile("ERROR", tag, msg);
    }

    public static void v(String tag, String msg) {
        if (mIsShowLog) {
            Log.v(tag, msg == null ? "" : msg);
        }
        writeLogFile("VERBOSE", tag, msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (mIsShowLog) {
            Log.v(tag, msg == null ? "" : msg, tr);
        }
        writeLogFile("VERBOSE", tag, msg);
    }

    public static void w(String tag, String msg) {
        if (mIsShowLog) {
            Log.w(tag, msg == null ? "" : msg);
        }
        writeLogFile("WARN", tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (mIsShowLog) {
            Log.w(tag, msg == null ? "" : msg, tr);
        }
        writeLogFile("WARN", tag, msg);
    }

    private static void createLogFile() {
        if (mIsWriteToFile && mIsShowLog) {
            synchronized (LOG_FILENAME) {
                if (mLogFile == null) {
                    try {
                        if (!SystemUtil.isSDCardAvailable()) {
                            return;
                        }
                        File file = new File(LOG_PATH_SDCARD_DIR);
                        if (!file.isDirectory()) {
                            boolean isCreateDirOk = file.mkdirs();
                            if (!isCreateDirOk) {
                                return;
                            }
                        }
                        mLogFile = new File(LOG_PATH_SDCARD_DIR
                                + File.separator + LOG_FILENAME + "(" + Process.myPid() + ")"
                                + LOG_FILEEXT);
                        if (!mLogFile.exists()) {
                            LogUtil.d(TAG, "Create the file:" + LOG_FILENAME);
                            mLogFile.createNewFile();
                        }
                        LogUtil.d(TAG, "Process.myPid()2:" + mLogFile.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (mLogFile.isFile()) {
                        if (mLogFile.length() > LOGFILE_LIMIT) {
                            StringBuffer sb = new StringBuffer(LOG_PATH_SDCARD_DIR
                                    + File.separator);
                            sb.append(LOG_FILENAME + "(" + Process.myPid() + ")");
                            sb.append(DATEFORMAT1.format(new Date()));
                            sb.append(LOG_FILEEXT);
                            mLogFile.renameTo(new File(sb.toString()));
                            sb = new StringBuffer(LOG_PATH_SDCARD_DIR
                                    + File.separator);
                            sb.append(LOG_FILENAME + "(" + Process.myPid() + ")");
                            sb.append(LOG_FILEEXT);
                            mLogFile = new File(sb.toString());
                            sb = null;
                            if (!mLogFile.exists()) {
                                LogUtil.d(TAG, "Create the file:" + LOG_FILENAME + LOG_FILEEXT);
                                try {
                                    mLogFile.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                if (gLogQueue == null && mLogFile != null) {
                    gLogQueue = new LinkedBlockingQueue<LogItem>();
                    gWriteLogThread = new WriteLogThread();
                    gWriteLogThread.start();
                }
            }
        }
    }

    private static void writeLogFile(String level, String tag, String msg) {

        if (mIsWriteToFile && mIsShowLog && isWritePermissionGranted()) {
            if (gLogQueue != null) {
                try {
                    gLogQueue.put(new LogItem(level, tag, msg));
                } catch (InterruptedException e) {
                    Log.e(tag, "writeLogFile error,reason=" + e);
                }
            }
        }
    }

    /**
     * 写文件是需要判断是够具有写文件权限，以免由于权限不足导致崩溃问题
     */
    private static boolean isWritePermissionGranted() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        return mContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static File getCurrentLogFile() {
        return mLogFile;
    }

    private static class WriteLogThread extends Thread {
        @Override
        public void run() {
            if (mLogFile == null || gLogQueue == null) {
                return;
            }
            setName("logThread");
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile(mLogFile, "rw");
                raf.seek(mLogFile.length());
                StringBuffer sb = new StringBuffer();
                LogItem item = null;
                while (!isInterrupted()) {
                    item = gLogQueue.take();
                    sb.setLength(0);
                    synchronized (DATEFORMAT) {
                        sb.append(DATEFORMAT.format(new Date()));
                    }
                    sb.append(": ");
                    sb.append(item.level);
                    sb.append(": ");
                    sb.append(item.tag);
                    sb.append(": ");
                    sb.append(item.msg);
                    sb.append("\n");
                    raf.write(sb.toString().getBytes("UTF-8"));
                    //动态创建新的日志
                    if (raf.length() > LOGFILE_LIMIT) {
                        File oldFile = mLogFile;
                        createLogFile();
                        if (oldFile != mLogFile) {
                            raf.close();
                            raf = new RandomAccessFile(mLogFile, "rw");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
