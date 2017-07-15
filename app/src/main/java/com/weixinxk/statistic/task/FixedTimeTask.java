package com.weixinxk.statistic.task;

import android.content.Context;

import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.db.StatisticDao;
import com.weixinxk.statistic.model.ReportData;

/**********************************************************************
 * 定时任务类
 *
 * @类名 FixedTimeTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class FixedTimeTask extends PriorityTask {

    private StatisticAgent mAgent;
    private ReportData mReportData;

    public FixedTimeTask(int priority, StatisticAgent agent, ReportData reportData) {
        super(priority);
        this.mAgent = agent;
        this.mReportData = reportData;
    }

    @Override
    public void run() {
        Context context = mAgent.getApplication().getApplicationContext();

        // 保存采集信息到数据库
        StatisticDao statisticDao = new StatisticDao(context);
        statisticDao.insert(mReportData);
    }
}
