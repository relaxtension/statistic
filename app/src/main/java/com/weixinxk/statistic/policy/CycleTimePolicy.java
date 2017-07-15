package com.weixinxk.statistic.policy;

import android.content.Context;

import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.StatisticConfig;
import com.weixinxk.statistic.db.StatisticDao;
import com.weixinxk.statistic.model.ReportData;

/**********************************************************************
 * 周期策略类
 *
 * @类名 CycleTimePolicy
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class CycleTimePolicy extends BasePolicy {

    public CycleTimePolicy(StatisticAgent agent) {
        super(agent);

        StatisticConfig config = agent.getConfig();
        long intervalTime = config.getIntervalTime();

        agent.start();
        agent.startReport(intervalTime, intervalTime);
    }

    @Override
    public void execute(ReportData reportData) {
        Context context = mAgent.getApplication().getApplicationContext();

        // 保存采集信息到数据库
        StatisticDao statisticDao = new StatisticDao(context);
        statisticDao.insert(reportData);
    }
}
