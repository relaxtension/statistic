package com.weixinxk.statistic.policy;

import android.content.Context;
import android.support.annotation.NonNull;

import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.task.ProgressSender;
import com.weixinxk.statistic.task.TaskResult;

/**********************************************************************
 * 定时策略类
 *
 * @类名 AtTimeTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class AtTimeTask extends StatisticTask {

    public AtTimeTask(Context context, String url, ReportData reportData) {
        super(context, url, reportData);
    }

    @Override
    public void init() {
        // 设置AlarmManager

    }

    @Override
    public void run() {
        /*
            保存采集信息到数据库。
            当定时器到时执行上报服务。
        */
    }
}
