package com.weixinxk.statistic.policy;

import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.report.TaskConsumer;
import com.weixinxk.statistic.task.PriorityTask;
import com.weixinxk.statistic.task.RealTimeTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**********************************************************************
 * 实时策略类
 *
 * @类名 RealTimePolicy
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public class RealTimePolicy extends BasePolicy {

    private PriorityBlockingQueue<PriorityTask> mTaskQueue;

    public RealTimePolicy(StatisticAgent agent) {
        super(agent);
        this.mTaskQueue = agent.getTaskQueue();

        agent.start();
    }

    @Override
    public void execute(ReportData reportData) {
        int priority = 0;
        try {
            priority = Integer.parseInt(reportData.getPriority());
        } catch (Exception e) {
            e.printStackTrace();
        }

        PriorityTask task = new RealTimeTask(priority, mAgent, reportData);
        mTaskQueue.offer(task);

        // 立即执行上报服务
        mAgent.startReport();
    }
}
