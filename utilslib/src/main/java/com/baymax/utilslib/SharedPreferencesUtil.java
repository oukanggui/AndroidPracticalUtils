package com.baymax.utilslib;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author oukanggui
 * @date 2018/10/11
 * 描述 SharedPreferences操作工具类
 */

public class SharedPreferencesUtil {

    private static final String FILE_NAME = "sharedprefs_config";
    private static SharedPreferences mSharedPreferences;

    /**
     * 保存整型数值
     *
     * @param context 上下文
     * @param key     保存值的键key
     * @param value   保存值
     */
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value).commit();
    }

    /**
     * 获取相应key的整型数值
     *
     * @param context      上下文
     * @param key          保存值的键key
     * @param defaultValue 默认值，即是key不存在的时候的返回默认值
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getInt(key, defaultValue);
    }

    /**
     * 保存Float浮点型数值
     *
     * @param context 上下文
     * @param key     保存值的键key
     * @param value   保存值
     */
    public static void saveFloat(Context context, String key, float value) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value).commit();
    }

    /**
     * 获取相应key的Float浮点型数值
     *
     * @param context      上下文
     * @param key          保存值的键key
     * @param defaultValue 默认值，即是key不存在的时候的返回默认值
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getFloat(key, defaultValue);
    }

    /**
     * 保存Boolean数值
     *
     * @param context 上下文
     * @param key     保存值的键key
     * @param value   保存值
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value).commit();
    }

    /**
     * 获取相应key的Boolean数值
     *
     * @param context      上下文
     * @param key          保存值的键key
     * @param defaultValue 默认值，即是key不存在的时候的返回默认值
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 保存Long数值
     *
     * @param context 上下文
     * @param key     保存值的键key
     * @param value   保存值
     */
    public static void saveLong(Context context, String key, long value) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value).commit();
    }

    /**
     * 获取相应key的Long数值
     *
     * @param context      上下文
     * @param key          保存值的键key
     * @param defaultValue 默认值，即是key不存在的时候的返回默认值
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getLong(key, defaultValue);
    }

    /**
     * 保存String数值
     *
     * @param context 上下文
     * @param key     保存值的键key
     * @param value   保存值
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value).commit();
    }

    /**
     * 获取相应key的String数值
     *
     * @param context      上下文
     * @param key          保存值的键key
     * @param defaultValue 默认值，即是key不存在的时候的返回默认值
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(key, defaultValue);
    }

    /**
     * 保存Set<String>数值
     *
     * @param context 上下文
     * @param key     保存值的键key
     * @param value   保存值
     */
    public static void saveStringSet(Context context, String key, Set<String> value) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value).commit();
    }

    /**
     * 获取相应key的Set<String>数值
     *
     * @param context      上下文
     * @param key          保存值的键key
     * @param defaultValue 默认值，即是key不存在的时候的返回默认值
     */
    public static Set<String> getStringSet(Context context, String key, Set<String> defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * 清空sp文件内容
     *
     * @param context 上下文
     */
    public static void clear(Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    /**
     * 移除key对应的内容
     *
     * @param context 上下文
     * @param key     待remove key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key).commit();
    }

    /**
     * 获取SharedPreferences实例对象--单例模式
     *
     * @param context 上下文
     * @return SharedPreferences实例对象
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        if (mSharedPreferences == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (mSharedPreferences == null) {
                    mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
                }
            }
        }
        return mSharedPreferences;
    }
}
