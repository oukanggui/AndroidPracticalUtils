package com.baymax.utilslib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author oukanggui
 * @date 2018/10/15
 * 描述：网络操作相关工具类
 */

public class NetworkUtil {

    /**
     * 获取网络IP  eg：192.168.191.88
     *
     * @return 网络IP，没有的话返回""
     */
    public static String getIp() {
        String ip = null;
        try {
            Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) enumeration.nextElement();
                Enumeration enumr = networkInterface.getInetAddresses();
                while (enumr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip == null ? "" : ip;
    }

    /**
     * 判断当前网络是否可用，需要权限：android.permission.ACCESS_NETWORK_STATE
     *
     * @return true if the network is available ,otherwise,false
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isAvailable());
    }

    /**
     * 判断当前网络是否连接WiFi,需要权限：android.permission.ACCESS_NETWORK_STATE
     *
     * @return true if the network is available ,otherwise,false
     */
    public static boolean isWifiConnected(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI);
    }

}
