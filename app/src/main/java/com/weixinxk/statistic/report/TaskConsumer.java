package com.weixinxk.statistic.report;

import com.weixinxk.statistic.task.PriorityTask;

import java.util.concurrent.PriorityBlockingQueue;

/**********************************************************************
 * 普通任务的消费线程
 *
 * @类名 TaskConsumer
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class TaskConsumer implements Runnable {

    private PriorityBlockingQueue<PriorityTask> mTaskQueue;

    public TaskConsumer(PriorityBlockingQueue<PriorityTask> taskQueue) {
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
