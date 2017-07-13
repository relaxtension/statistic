/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.utils;

import android.content.Context;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/***********************************************************************
 *
 * 网络请求工具
 *
 * @类名 OkHttpUtils
 * @包名 com.weixinxk.statistic.utils
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/11
 *
 ***********************************************************************/

public class OkHttpUtils {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient mOkHttpClient;

    static {
        mOkHttpClient = new OkHttpBuilder()
                .setConnectTimeout(10) //设置连接超时时间
                .setReadTimeOut(30) //设置读取超时时间
                .setWriteTimeOut(30) //设置写入超时时间
                .build();
    }

    public static String joinParasWithEncodedValue(Map<String, String> parasMap) {
        StringBuilder paras = new StringBuilder("");
        if (parasMap != null && parasMap.size() > 0) {
            Iterator<Map.Entry<String, String>> ite = parasMap.entrySet().iterator();
            try {
                while (ite.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) ite.next();
                    paras.append(entry.getKey()).append("=").append(StringUtils.utf8Encode(entry.getValue()));
                    if (ite.hasNext()) {
                        paras.append("&");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paras.toString();
    }

    public static String getUrlWithValueEncodeParas(String url, Map<String, String> parasMap) {
        StringBuilder urlWithParas = new StringBuilder(StringUtils.isEmpty(url) ? "" : url);
        String paras = joinParasWithEncodedValue(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            urlWithParas.append("?").append(paras);
        }
        return urlWithParas.toString();
    }

    public static Response get(Context context, String url, Map<String, String> params) throws Exception {
        return OkHttpUtils.get(context, url, null, params);
    }

    public static Response get(Context context, String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(OkHttpUtils.getUrlWithValueEncodeParas(url, params));
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        Request request = requestBuilder.build();
        return mOkHttpClient.newCall(request).execute();
    }

    public static void get(Context context, String url, Map<String, String> params, Callback responseCallback) throws Exception {
        OkHttpUtils.get(context, url, null, params, responseCallback);
    }

    public static void get(Context context, String url, Map<String, String> headers, Map<String, String> params, Callback responseCallback) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(OkHttpUtils.getUrlWithValueEncodeParas(url, params));
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        Request request = requestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    public static Response post(Context context, String url, Map<String, String> params) throws Exception {
        return OkHttpUtils.post(context, url, null, params);
    }

    public static Response post(Context context, String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        if (params != null) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                formBodyBuilder.add(param.getKey(), param.getValue());
            }
            RequestBody requestBody = formBodyBuilder.build();
            requestBuilder.post(requestBody);
        }
        Request request = requestBuilder.build();
        return mOkHttpClient.newCall(request).execute();
    }

    public static Response post(Context context, String url, String requestContent) throws Exception {
        return OkHttpUtils.post(context, url, null, requestContent);
    }

    public static Response post(Context context, String url, Map<String, String> headers, String requestContent) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        if (!StringUtils.isEmpty(requestContent)) {
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, requestContent);
            requestBuilder.post(requestBody);
        }
        Request request = requestBuilder.build();
        return mOkHttpClient.newCall(request).execute();
    }

    public static void post(Context context, String url, Map<String, String> params, Callback responseCallback) throws Exception {
        OkHttpUtils.post(context, url, null, params, responseCallback);
    }

    public static void post(Context context, String url, Map<String, String> headers, Map<String, String> params, Callback responseCallback) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        if (params != null) {
            FormBody.Builder requestBodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                requestBodyBuilder.add(param.getKey(), param.getValue());
            }
            RequestBody requestBody = requestBodyBuilder.build();
            requestBuilder.post(requestBody);
        }
        Request request = requestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    public static void post(Context context, String url, String requestContent, Callback responseCallback) throws Exception {
        OkHttpUtils.post(context, url, null, requestContent, responseCallback);
    }

    public static void post(Context context, String url, Map<String, String> headers, String requestContent, Callback responseCallback) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        if (!StringUtils.isEmpty(requestContent)) {
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, requestContent);
            requestBuilder.post(requestBody);
        }
        Request request = requestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }
}
