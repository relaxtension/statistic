package com.weixinxk.statistic.task;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.db.StatisticDao;
import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.utils.CommonUtils;
import com.weixinxk.statistic.utils.OkHttpHelper;
import com.weixinxk.statistic.utils.StringUtils;

import okhttp3.Response;

/**********************************************************************
 * 实时任务类
 *
 * @类名 RealTimeTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class RealTimeTask extends PriorityTask {

    private StatisticAgent mAgent;
    private ReportData mReportData;

    public RealTimeTask(int priority, StatisticAgent agent, ReportData reportData) {
        super(priority);
        this.mAgent = agent;
        this.mReportData = reportData;
    }

    @Override
    public void run() {
        Context context = mAgent.getApplication().getApplicationContext();
        String reportUrl = mAgent.getConfig().getReportUrl();

        boolean reportSuccess = false;
        if (CommonUtils.isNetworkAvailable(context)) {
            Gson gson = new GsonBuilder().create();
            String requstContent = gson.toJson(mReportData, ReportData.class);

            try {
                Response response = OkHttpHelper.post(context, reportUrl, requstContent);
                if (response.isSuccessful()) {
                    // 删除存在的相关记录
                    reportSuccess = true;
                    String id = mReportData.getId();
                    if (!StringUtils.isEmpty(id)) {
                        StatisticDao statisticDao = new StatisticDao(context);
                        statisticDao.delete(id);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!reportSuccess) {
            // 保存采集信息到数据库
            StatisticDao statisticDao = new StatisticDao(context);
            statisticDao.insert(mReportData);
        }

        // 启动上报服务

    }
}
