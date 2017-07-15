package com.weixinxk.statistic.utils;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class OkHttpBuilder {

    public static final String TAG = OkHttpBuilder.class.getSimpleName();

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static final long DEFAULT_SECONDS = 30L;

    private long connectTimeout = DEFAULT_SECONDS; // 连接超时时间 - 60秒
    private long readTimeOut = DEFAULT_SECONDS; // 读取超时时间
    private long writeTimeOut = DEFAULT_SECONDS; // 写入超时时间
    private List<Interceptor> interceptors = new ArrayList<>();

    public OkHttpBuilder() {
        this.interceptors.add(this.getLogInterceptor());
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public OkHttpBuilder setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public long getReadTimeOut() {
        return readTimeOut;
    }

    public OkHttpBuilder setReadTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public long getWriteTimeOut() {
        return writeTimeOut;
    }

    public OkHttpBuilder setWriteTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public OkHttpBuilder addInterceptors(List<Interceptor> interceptorList) {
        this.interceptors.addAll(interceptorList);
        return this;
    }

    public OkHttpBuilder addInterceptor(Interceptor interceptor) {
        this.interceptors.add(interceptor);
        return this;
    }

    private Interceptor getLogInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // 构建请求地址
                HttpUrl url = request.url();

                // 构建请求头
                Headers headers = new Headers.Builder()
                        .add("Content-Type", "application/x-www-form-urlencoded")
                        .add("Accept", "application/vnd.album.v0.1.0+json")
                        .add("Accept-Charset", "utf-8")
                        .add("ContentType", "utf-8")
                        .build();
                StringBuilder headerBuilder = new StringBuilder();
                for (int i = 0; i < headers.size(); i++) {
                    if (i == 0) {
                        headerBuilder.append(headers.name(i) + ": " + headers.value(i));
                    } else {
                        headerBuilder.append(" | " + headers.name(i) + ": " + headers.value(i));
                    }
                }

                // 构建请求体
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                String requestContent = IOUtils.read(buffer.inputStream());
                RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, requestContent);

                // 构建新请求
                Request newRequest = request.newBuilder()
                        .url(url)
                        .headers(headers)
                        .post(requestBody)
                        .build();

                // 打印请求数据
                StringBuilder requestLogBuilder = new StringBuilder();
                requestLogBuilder.append("请求地址：" + newRequest.url().toString() + "\r\n");
                requestLogBuilder.append("请求方式：" + newRequest.method() + "\r\n");
                requestLogBuilder.append("请求头：" + headerBuilder.toString() + "\r\n");
                requestLogBuilder.append("请求体：" + requestContent + "\r\n");
                Log.i(TAG, requestLogBuilder.toString());

                // 网络请求，取得响应数据
                Response response = chain.proceed(newRequest);

                // 构建响应体
                String responseContent = response.body().string();
                ResponseBody responseBody = ResponseBody.create(MEDIA_TYPE_JSON, responseContent);
                // 构建新响应
                Response newResponse = response.newBuilder()
                        .body(responseBody)
                        .build();

                // 打印响应数据
                StringBuilder responseLogBuilder = new StringBuilder();
                responseLogBuilder.append("响应码：" + newResponse.code() + "\r\n");
                responseLogBuilder.append("响应体：" + responseContent + "\r\n");
                Log.i(TAG, responseLogBuilder.toString());

                return newResponse;
            }
        };
    }

    public OkHttpClient build() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeOut, TimeUnit.SECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.SECONDS);
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }
}
