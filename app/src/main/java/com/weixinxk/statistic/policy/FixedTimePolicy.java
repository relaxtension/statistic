package com.weixinxk.statistic.policy;

import android.content.Context;
import android.os.SystemClock;

import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.StatisticConfig;
import com.weixinxk.statistic.db.StatisticDao;
import com.weixinxk.statistic.model.ReportData;

import java.util.Calendar;
import java.util.TimeZone;

/**********************************************************************
 * 定时策略类
 *
 * @类名 FixedTimePolicy
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class FixedTimePolicy extends BasePolicy {

    public FixedTimePolicy(StatisticAgent agent) {
        super(agent);

        StatisticConfig config = agent.getConfig();

        long firstTime = SystemClock.elapsedRealtime(); // 开机之后到现在的运行时间(包括睡眠时间)
        long systemTime = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // 这里时区需要设置一下，不然会有8个小时的时间差
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.set(Calendar.HOUR_OF_DAY, config.getHour());
        calendar.set(Calendar.MINUTE, config.getMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // 选择的定时时间
        long selectTime = calendar.getTimeInMillis();
        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if (systemTime > selectTime) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            selectTime = calendar.getTimeInMillis();
        }
        // 计算现在时间到设定时间的时间差
        long time = selectTime - systemTime;
        long intervalTime = firstTime + time;
        long periodTime = 60 * 60 * 24;

        agent.start();
        agent.startReport(intervalTime, periodTime);
    }

    @Override
    public void execute(ReportData reportData) {
        Context context = mAgent.getApplication().getApplicationContext();

        // 保存采集信息到数据库
        StatisticDao statisticDao = new StatisticDao(context);
        statisticDao.insert(reportData);
    }
}
