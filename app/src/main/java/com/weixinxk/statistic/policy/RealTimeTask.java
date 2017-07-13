package com.weixinxk.statistic.policy;

import android.content.Context;
import android.os.Bundle;

import com.frogshealth.statistic.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.utils.CommonUtils;
import com.weixinxk.statistic.utils.OkHttpUtils;

import java.net.ResponseCache;

import okhttp3.Response;

/**********************************************************************
 * 实时策略类
 *
 * @类名 RealTimeTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class RealTimeTask extends StatisticTask {

    public RealTimeTask(Context context, String url, ReportData reportData) {
        super(context, url, reportData);

    }

    @Override
    public void init() {
        // nothing
    }

    @Override
    public void run() {
        if (CommonUtils.isNetworkAvailable(context)) {
            String ipAddr = CommonUtils.getHostIp();
            reportData.setIpAddr(ipAddr);

            Gson gson = new GsonBuilder().create();
            String requstContent = gson.toJson(reportData, ReportData.class);

            boolean requestSuccess = false;
            try {
                Response response = OkHttpUtils.post(context, url, requstContent);
                if (response.isSuccessful()) {
                    requestSuccess = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (requestSuccess) {
                // 删除存在的相关记录

            } else {
                // 保存到数据库
            }
        } else {
            // 保存采集信息到数据库

        }
        // 启动上报服务

    }
}
