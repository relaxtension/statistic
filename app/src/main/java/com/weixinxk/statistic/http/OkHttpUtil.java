/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.http;

import android.util.Log;

import com.example.lisichen.statistic_db.consts.StatisticConsts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***********************************************************************
 *
 * 网络请求工具
 *
 * @类名 OkHttpUtil
 * @包名 com.example.lisichen.statistic_db.http
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/11
 *
 ***********************************************************************/

public class OkHttpUtil {

    private static OkHttpUtil mOkHttpUtil;
    private static OkHttpClient mOkHttpClient;
    private static Request mRequest;
    public OkHttpUtil() {
        init();
    }

    public static OkHttpUtil getInstance() {
        if(mOkHttpClient == null) {
            mOkHttpUtil = new OkHttpUtil();
        }
        return mOkHttpUtil;
    }

    private void init() {
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
                .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
                .build();

        mRequest = new Request.Builder()
                .url(StatisticConsts.EVENT_URL)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/vnd.album.v0.1.0+json")
                .addHeader("Accept-Charset", "utf-8")
                .addHeader("ContentType", "utf-8")
                .build();
    }


    /**
     * 同步加载
     * @throws Exception
     */
    public void execute() throws Exception {
        Request request = new Request.Builder()
                .url(StatisticConsts.EVENT_URL)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/vnd.album.v0.1.0+json")
                .addHeader("Accept-Charset", "utf-8")
                .addHeader("ContentType", "utf-8")
                .build();
        Response response = mOkHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            Log.e("Lsc===Lsc", "code:" + response.code());
            Log.e("Lsc===Lsc", "body:" + response.body().string());
        }
    }


    /**
     * 异步加载
     */
    public void enqueue(Callback responseCallback){
        mOkHttpClient.newCall(mRequest).enqueue(responseCallback);
    }

    public static void okHttpPost() throws Exception {

    }

    /**
     * 请求回调
     */
    public static class OKHttpListCallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if(response.code() == 200) {
                Log.e("Lsc===Lsc", "code main:" + response.code());
            }
        }
    }

}
