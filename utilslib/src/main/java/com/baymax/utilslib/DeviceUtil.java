package com.baymax.utilslib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

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
     * 获取SIM卡IMSI序列号，需要权限：Manifest.permission.READ_PHONE_STATE，否则返回空
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
     * 获取设备IMEI码，需要权限：Manifest.permission.READ_PHONE_STATE，否则返回空
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
     * 获取设备Mac地址，eg 58:02:03:BD:98:63
     * 需要权限：android.permission.ACCESS_WIFI_STATE
     *
     * @return mac address
     */
    public static String getMacAddress() {
        //先尝试从本地IP地址中获取
        String mac = getLocalMacAddressFromIp();
        if (mac == null) {
            //获取不到，再从网络接口中获取
            mac = getMacAddressFromNetInterfece();
        }
        if (mac == null) {
            mac = "";
        }
        return mac;
    }

    /**
     * 根据IP地址获取MAC地址
     *
     * @return MAC地址
     */
    private static String getLocalMacAddressFromIp() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getLocalNetIpAddress();
            if (ip == null) {
                return null;
            }
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            if (b == null) {
                return null;
            }
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strMacAddr;
    }

    /**
     * 获取移动设备本地IP
     *
     * @return 本地IP
     */
    private static InetAddress getLocalNetIpAddress() {
        InetAddress ip = null;
        try {
            Enumeration<NetworkInterface> netInterface = NetworkInterface.getNetworkInterfaces();
            if (netInterface == null) {
                return null;
            }
            while (netInterface.hasMoreElements()) {
                NetworkInterface ni = netInterface.nextElement();
                Enumeration<InetAddress> enIp = ni.getInetAddresses();
                if (enIp == null) {
                    return null;
                }
                while (enIp.hasMoreElements()) {
                    ip = enIp.nextElement();
                    if (!ip.isLoopbackAddress() && ip instanceof Inet4Address) {
                        break;
                    } else {
                        ip = null;
                    }
                }
                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 通过网络接口取Mac
     *
     * @return Mac
     */
    private static String getMacAddressFromNetInterfece() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
