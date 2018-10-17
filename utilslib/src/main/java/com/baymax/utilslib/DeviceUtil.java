package com.baymax.utilslib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * @author oukanggui
 * @date 2018/10/11
 * 描述 设备操作工具类
 */

public class DeviceUtil {

    /**
     * 获取屏幕的宽度
     *
     * @param context context
     * @return 屏幕宽度（px）
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param context context
     * @return 屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


    /**
     * 获取SIM卡IMSI序列号，需要权限：Manifest.permission.READ_PHONE_STATE
     *
     * @param context
     * @return
     */
    public static String getImsi(Context context) {
        String imsi = null;
        if (SystemUtil.getSDKVersion() < Build.VERSION_CODES.M || context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imsi = tm.getSubscriberId();
        }
        //imsi获取失败使用默认值
        if (TextUtil.isEmpty(imsi)) {
            imsi = "";
        }
        return imsi;
    }

    /**
     * 获取设备IMEI码，需要权限：Manifest.permission.READ_PHONE_STATE
     *
     * @param context
     * @return ip
     */
    public static String getImei(Context context) {
        String imei = null;
        if (SystemUtil.getSDKVersion() < Build.VERSION_CODES.M || context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                imei = tm.getDeviceId();
            } catch (Exception e) {
                imei = null;
            }
        }
        if (TextUtil.isEmpty(imei)) {
            imei = "";
        }
        return imei;
    }

    /**
     * 获取设备Mac地址，需要权限：android.permission.ACCESS_WIFI_STATE
     *
     * @param context
     * @return mac address
     */
    public static String getMacAddress(Context context) {
        String mac = null;
        //先通过WiFi信息获取
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr == null ? null : wifiMgr.getConnectionInfo();
        if (wifiInfo != null) {
            mac = wifiInfo.getMacAddress();
        }
        if (TextUtil.isEmpty(mac)) {
            NetworkInterface networkInterface = null;
            try {
                networkInterface = NetworkInterface.getByName("wlan0");
                if (networkInterface != null) {
                    byte[] macBytes = networkInterface.getHardwareAddress();
                    StringBuilder macSb = new StringBuilder();
                    if (macBytes != null) {
                        byte[] macBytesTmp = macBytes;
                        int macBytesLength = macBytes.length;
                        for (int i = 0; i < macBytesLength; ++i) {
                            byte value = macBytesTmp[i];
                            if (macSb.length() > 0) {
                                macSb.append(':');
                            }
                            int var = value & 255;
                            macSb.append(String.format("%02X", new Object[]{Integer.valueOf(var)}));
                        }
                    }
                    mac = macSb.toString();
                }
            } catch (SocketException se) {
                se.printStackTrace();
            }
        }
        return mac;
    }
}
