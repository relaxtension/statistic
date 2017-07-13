package com.weixinxk.statistic.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016 
 *
 * @类名 CommonUtils
 * @包名 com.weixinxk.statistic.utils
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/11
 *
 ***********************************************************************/

public class CommonUtils {
    private static String TAG = "CommonUtil";
    /**
     * 判断是否有网络连接
     *
     * @param context 上下文
     * @return true or false
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            Log.e(TAG, "context is null");
            return false;
        }
        if (checkPermissions(context, "android.permission.INTERNET")) {
            ConnectivityManager cManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cManager == null) {
                return false;
            }
            NetworkInfo info = cManager.getActiveNetworkInfo();

            if (info != null && info.isAvailable()) {
                Log.e(TAG, "Network is available.");
                return true;
            } else {
                Log.e(TAG, "Network is not available.");
                return false;
            }

        } else {
            Log.e(TAG,
                    "android.permission.INTERNET permission should be "
                            + "added into AndroidManifest.xml.");

            return false;
        }
    }

    /**
     * 检查是否有指定的许可
     *
     * @param context 上下文
     * @param permission 许可
     * @return true or false
     */
    public static boolean checkPermissions(Context context, String permission) {
        if (context == null || permission == null || permission.equals("")) {
            Log.e(TAG, "Incorrect parameters");
            return false;
        }
        PackageManager pm = context.getPackageManager();
        return pm.checkPermission(permission, context.getPackageName())
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 取得网关IP地址
     *
     * @return 网关IP地址
     */
    public static String getHostIp() {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnum = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnum.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaceEnum.nextElement();
                Enumeration<InetAddress> inetAddressEnum = networkInterface.getInetAddresses();
                while (inetAddressEnum.hasMoreElements()) {
                    InetAddress inetAddress = inetAddressEnum.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
        } catch (Exception e) {
        }
        return null;
    }
}
