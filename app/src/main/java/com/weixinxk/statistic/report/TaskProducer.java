package com.weixinxk.statistic.report;

import android.content.Context;

import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.task.PriorityTask;
import com.weixinxk.statistic.task.RealTimeTask;

import java.util.concurrent.PriorityBlockingQueue;

/**********************************************************************
 * 普通任务的生产线程
 *
 * @类名 TaskProducer
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/15
 ***********************************************************************/
public class TaskProducer implements Runnable {

    private StatisticAgent mAgent;
    private PriorityBlockingQueue<PriorityTask> mTaskQueue;
    private ReportData mReportData;

    public TaskProducer(StatisticAgent agent, PriorityBlockingQueue<PriorityTask> taskQueue, ReportData reportData) {
        this.mAgent = agent;
        this.mTaskQueue = taskQueue;
        this.mReportData = reportData;
    }

    @Override
    public void run() {
        if (mAgent == null) {
            return;
        }

        int priority = 0;
        try {
            priority = Integer.parseInt(mReportData.getPriority());
        } catch (Exception e) {
            e.printStackTrace();
        }

        PriorityTask task = new RealTimeTask(priority, mAgent, mReportData);
        mTaskQueue.offer(task);
    }
}
