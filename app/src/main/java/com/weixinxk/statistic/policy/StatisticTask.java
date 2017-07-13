package com.weixinxk.statistic.policy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.task.Task;

/**********************************************************************
 * 统计策略接口
 *
 * @类名 StatisticTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public abstract class StatisticTask implements Runnable, Comparable<StatisticTask> {

    protected Context context;
    protected String url;
    protected ReportData reportData;
    private final int priority;

    public StatisticTask(Context context, String url, ReportData reportData) {
        this.reportData = reportData;
        this.url = url;
        this.priority = reportData.getPriority();
    }

    public abstract void init();

    public ReportData getReportData() {
        return reportData;
    }

    @Override
    public int compareTo(@NonNull StatisticTask o) {
        if (priority < o.priority) {
            return -1;
        } else {
            if (priority > o.priority) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
