package com.weixinxk.statistic.task;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.db.StatisticDao;
import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.task.BaseTask;
import com.weixinxk.statistic.utils.CommonUtils;
import com.weixinxk.statistic.utils.OkHttpHelper;
import com.weixinxk.statistic.utils.StringUtils;

import okhttp3.Response;

/**********************************************************************
 * 上报任务类
 *
 * @类名 ReportTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class ReportTask implements BaseTask {

    private StatisticAgent mAgent;
    private ReportData mReportData;

    public ReportTask(StatisticAgent agent, ReportData reportData) {
        this.mAgent = agent;
        this.mReportData = reportData;
    }

    @Override
    public void run() {
        Context context = mAgent.getApplication().getApplicationContext();
        String reportUrl = mAgent.getConfig().getReportUrl();

        if (!CommonUtils.isNetworkAvailable(context)) {
            return;
        }

        Gson gson = new GsonBuilder().create();
        String requstContent = gson.toJson(mReportData, ReportData.class);

        try {
            Response response = OkHttpHelper.post(context, reportUrl, requstContent);
            if (response.isSuccessful()) {
                // 删除存在的相关记录
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
}
