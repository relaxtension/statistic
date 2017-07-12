/**********************************************************************
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 **********************************************************************/
package com.frogshealth.statistic;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;

import com.frogshealth.photoframe.application.FrogsHealthApplication;
import com.frogshealth.photoframe.common.logger.LoggerUtil;
import com.frogshealth.photoframe.common.util.DeviceSnUtil;
import com.frogshealth.photoframe.common.util.JsonObjUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;

/**********************************************************************
 * 连接工具类。
 * @author sundi
 * @类名 ConnectionUtil
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/5
 ***********************************************************************/
public class ConnectionUtil {

    /**
     * 访问微信公众号API成功后返回该Code.
     */
    private static final int WXOPENAPI_SUCCESS_CODE = 10000;
    /**
     * 发送统计数据请求
     * @param context 上下文
     * @param url url
     * @param log 本地缓存的统计数据
     * @return 请求的结果 true:成功,false:失败
     */
    public static boolean sendPostRequest(Context context, String url, String log) {
        String token = getAccessToken(context);
        if (token == null || token.equals("")) {
            LoggerUtil.getDefaultLogger().debug("token is invalid");
            return false;
        }
        InputStream is = null;
        try {
            URL targetUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) targetUrl.openConnection();
            conn.setReadTimeout(StatisticsConstants.READ_TIME_OUT /* milliseconds */);
            conn.setConnectTimeout(StatisticsConstants.CONN_TIME_OUT /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept", "application/vnd.album.v0.1.0+json");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("ContentType", "utf-8");
            // Starts the query
            try {
                conn.connect();
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }

            DataOutputStream out = new DataOutputStream(conn
                    .getOutputStream());
            StringBuffer buffer = new StringBuffer();

            buffer.append(StatisticsConstants.KEY_DEVICE_ID);
            buffer.append("=");
            buffer.append(URLEncoder.encode(DeviceSnUtil.getDeviceSn(), "utf-8"));
            buffer.append("&");
            buffer.append(StatisticsConstants.KEY_LOG_INFO);
            buffer.append("=");
            buffer.append(URLEncoder.encode(log, "utf-8"));
//            buffer.append(log);
            buffer.append("&");
            buffer.append(StatisticsConstants.KEY_ACCESS_TOKEN);
            buffer.append("=");
            buffer.append(URLEncoder.encode(token, "utf-8"));
//            buffer.append(token);

            LoggerUtil.getDefaultLogger().debug("sendPostRequest is:" + buffer.toString());
            out.writeBytes(buffer.toString());
            out.flush();
            out.close();

            int response = -1;
            try {
                response = conn.getResponseCode();
                LoggerUtil.getDefaultLogger().debug("response is:" + response);
            } catch (SocketTimeoutException ex) {
                ex.printStackTrace();
            }

            if (response != 200) {
                return false;
            }

            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            LoggerUtil.getDefaultLogger().debug("post result is:" + contentAsString);
            JSONObject result = new JSONObject(contentAsString);
            if (result != null) {
                int code = JsonObjUtil.getIntFromJsonObj(result, "code");
                if (code == WXOPENAPI_SUCCESS_CODE) {
                    return true;
                } else {
                    String msg = JsonObjUtil.getStringFromJsonObj(result, "msg");
                    LoggerUtil.getDefaultLogger().error(msg);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 读取数据
     *
     * @param stream :stream
     * @return 读取到的数据
     * @throws IOException io异常
     */
    private static String readIt(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String contentPerLine;
        StringBuilder result = new StringBuilder();

        while ((contentPerLine = reader.readLine()) != null) {
            result.append(contentPerLine);
        }
        return result.toString();
    }


    /**
     * 取得Access Token
     * @param context 上下文
     * @return Access Token
     */
    private static String getAccessToken(Context context) {
        String token = "";
        try {
            if (context instanceof Activity) {
                FrogsHealthApplication frogsHealthApplication = (FrogsHealthApplication)((Activity)context).getApplication();
                if (frogsHealthApplication.getRemoteCall() != null) {
                    token = frogsHealthApplication.getRemoteCall().getAccessToken();
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return token;
    }

}
