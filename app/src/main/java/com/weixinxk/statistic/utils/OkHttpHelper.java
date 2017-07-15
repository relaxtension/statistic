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
 * @类名 OkHttpHelper
 * @包名 com.weixinxk.statistic.utils
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/11
 *
 ***********************************************************************/

public class OkHttpHelper {

    private static volatile OkHttpHelper sOkHttpHelper;

    private OkHttpClient mOkHttpClient;

    private OkHttpHelper() {
    }

    private static OkHttpHelper getInstance() {
        if (sOkHttpHelper == null) {
            synchronized (OkHttpHelper.class) {
                if (sOkHttpHelper == null) {
                    sOkHttpHelper = new OkHttpHelper();
                }
            }
        }
        return sOkHttpHelper;
    }

    public static void init(OkHttpBuilder okHttpBuilder) {
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        okHttpHelper.mOkHttpClient = okHttpBuilder.build();
    }

    public static Response get(Context context, String url, Map<String, String> params) throws Exception {
        return OkHttpHelper.get(context, url, null, params);
    }

    public static Response get(Context context, String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(OkHttpUtils.getUrlWithValueEncodeParas(url, params));
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        Request request = requestBuilder.build();
        return OkHttpHelper.getInstance().mOkHttpClient.newCall(request).execute();
    }

    public static void get(Context context, String url, Map<String, String> params, Callback responseCallback) throws Exception {
        OkHttpHelper.get(context, url, null, params, responseCallback);
    }

    public static void get(Context context, String url, Map<String, String> headers, Map<String, String> params, Callback responseCallback) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(OkHttpUtils.getUrlWithValueEncodeParas(url, params));
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        Request request = requestBuilder.build();
        OkHttpHelper.getInstance().mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    public static Response post(Context context, String url, Map<String, String> params) throws Exception {
        return OkHttpHelper.post(context, url, null, params);
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
        return OkHttpHelper.getInstance().mOkHttpClient.newCall(request).execute();
    }

    public static Response post(Context context, String url, String requestContent) throws Exception {
        return OkHttpHelper.post(context, url, null, requestContent);
    }

    public static Response post(Context context, String url, Map<String, String> headers, String requestContent) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        if (!StringUtils.isEmpty(requestContent)) {
            RequestBody requestBody = RequestBody.create(OkHttpBuilder.MEDIA_TYPE_JSON, requestContent);
            requestBuilder.post(requestBody);
        }
        Request request = requestBuilder.build();
        return OkHttpHelper.getInstance().mOkHttpClient.newCall(request).execute();
    }

    public static void post(Context context, String url, Map<String, String> params, Callback responseCallback) throws Exception {
        OkHttpHelper.post(context, url, null, params, responseCallback);
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
        OkHttpHelper.getInstance().mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    public static void post(Context context, String url, String requestContent, Callback responseCallback) throws Exception {
        OkHttpHelper.post(context, url, null, requestContent, responseCallback);
    }

    public static void post(Context context, String url, Map<String, String> headers, String requestContent, Callback responseCallback) throws Exception {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (headers != null) {
            requestBuilder.headers(Headers.of(headers));
        }
        if (!StringUtils.isEmpty(requestContent)) {
            RequestBody requestBody = RequestBody.create(OkHttpBuilder.MEDIA_TYPE_JSON, requestContent);
            requestBuilder.post(requestBody);
        }
        Request request = requestBuilder.build();
        OkHttpHelper.getInstance().mOkHttpClient.newCall(request).enqueue(responseCallback);
    }
}
