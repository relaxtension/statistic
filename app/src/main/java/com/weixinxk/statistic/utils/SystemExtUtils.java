package com.weixinxk.statistic.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class SystemExtUtils {

	/**
	 * 获取设备号（为保护用户设备信息，不返回真实设备号，只返回加密后设备号，并保持唯一性）
	 * 
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return String.valueOf(telephonyManager.getDeviceId().hashCode());
	}
}
