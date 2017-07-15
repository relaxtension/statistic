package com.weixinxk.statistic.report;

import com.weixinxk.statistic.task.ReportTask;

import java.util.concurrent.ArrayBlockingQueue;

/**********************************************************************
 * 上报服务的消费线程
 *
 * @类名 ReportTaskConsumer
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class ReportTaskConsumer implements Runnable {

    private ArrayBlockingQueue<ReportTask> mTaskQueue;

    public ReportTaskConsumer(ArrayBlockingQueue<ReportTask> taskQueue) {
        this.mTaskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                mTaskQueue.take().run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
