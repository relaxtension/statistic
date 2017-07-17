package com.weixinxk.statistic;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.policy.BasePolicy;
import com.weixinxk.statistic.policy.CycleTimePolicy;
import com.weixinxk.statistic.policy.RealTimePolicy;
import com.weixinxk.statistic.report.ReportTaskConsumer;
import com.weixinxk.statistic.report.ReportTaskProducer;
import com.weixinxk.statistic.report.TaskConsumer;
import com.weixinxk.statistic.task.PriorityTask;
import com.weixinxk.statistic.task.ReportTask;
import com.weixinxk.statistic.utils.CommonUtils;
import com.weixinxk.statistic.utils.EventMatcher;
import com.weixinxk.statistic.utils.OkHttpBuilder;
import com.weixinxk.statistic.utils.OkHttpHelper;
import com.weixinxk.statistic.utils.StringUtils;
import com.weixinxk.statistic.utils.SystemExtUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**********************************************************************
 * 统计代理类
 *
 * @类名 StatisticManager
 * @包名 com.weixinxk.statistic
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class StatisticAgent {

    private static volatile StatisticAgent sStatisticAgent;
    private Application mApplication;
    private StatisticConfig mConfig = new StatisticConfig();
    private BasePolicy mPolicy;

    private ExecutorService mExecutorService;
    private PriorityBlockingQueue<PriorityTask> mTaskQueue;
    private ScheduledExecutorService mReportExecutorService;
    private ArrayBlockingQueue<ReportTask> mReportTaskQueue;

    private StatisticAgent() {
        mExecutorService = Executors.newFixedThreadPool(2);
        mTaskQueue = new PriorityBlockingQueue<PriorityTask>();
        mReportExecutorService = Executors.newScheduledThreadPool(2);
        mReportTaskQueue = new ArrayBlockingQueue<ReportTask>(10);
    }

    private static StatisticAgent getInstance() {
        if (sStatisticAgent == null) {
            synchronized (StatisticAgent.class) {
                if (sStatisticAgent == null) {
                    sStatisticAgent = new StatisticAgent();
                }
            }
        }
        return sStatisticAgent;
    }

    public static void init(Application application, StatisticConfig config) {
        OkHttpBuilder okHttpBuilder = new OkHttpBuilder()
                .setConnectTimeout(config.getConnTimeout())
                .setReadTimeOut(config.getReadTimeout())
                .setWriteTimeOut(config.getWriteTimeout());
        OkHttpHelper.init(okHttpBuilder);

        StatisticAgent agent = StatisticAgent.getInstance();
        agent.mApplication = application;
        agent.mConfig = config;

        // 依据策略初始化
        switch (config.getPolicy()) {
            case StatisticConfig.POLICY_REAL_TIME:
                agent.mPolicy = new RealTimePolicy(agent);
                break;
            case StatisticConfig.POLICY_CYCLE_TIME:
                agent.mPolicy = new CycleTimePolicy(agent);
                break;
            case StatisticConfig.POLICY_FIXED_TIME:

                break;
            default:
                break;
        }
    }

    public Application getApplication() {
        return mApplication;
    }

    public StatisticConfig getConfig() {
        return mConfig;
    }

    public PriorityBlockingQueue<PriorityTask> getTaskQueue() {
        return mTaskQueue;
    }

    public ArrayBlockingQueue<ReportTask> getReportTaskQueue() {
        return mReportTaskQueue;
    }

    public void start() {
        mExecutorService.execute(new TaskConsumer(mTaskQueue));
    }

    public void stop() {
        mExecutorService.shutdown();
    }

    public boolean isRunning() {
        if (mExecutorService == null) {
            return false;
        }
        return mExecutorService.isShutdown() || mExecutorService.isTerminated();
    }

    public void startReport() {
        mReportExecutorService.execute(new ReportTaskConsumer(mReportTaskQueue));
        mReportExecutorService.execute(new ReportTaskProducer(this, mReportTaskQueue));
    }

    public void startReport(long initialDelay, long period) {
        mReportExecutorService.execute(new ReportTaskConsumer(mReportTaskQueue));
        mReportExecutorService.scheduleAtFixedRate(new ReportTaskProducer(this, mReportTaskQueue), initialDelay, period, TimeUnit.SECONDS);
    }

    public void stopReport() {
        mReportExecutorService.shutdown();
    }

    public boolean isReportRunning() {
        if (mReportExecutorService == null) {
            return false;
        }
        return mReportExecutorService.isShutdown() || mReportExecutorService.isTerminated();
    }

    public static void onEvent(Context context, @NonNull String eventId) {
        StatisticAgent.onEvent(context, eventId, null, null);
    }

    public static void onEvent(Context context, @NonNull String eventId, String messageId) {
        StatisticAgent.onEvent(context, eventId, messageId, null);
    }

    public static void onEvent(Context context, @NonNull String eventId, String messageId, String content) {
        // 判断 context 是否注册


        ReportData reportData = new ReportData();
        reportData.setEventId(eventId);
        reportData.setEventType(String.valueOf(EventMatcher.getEventType(eventId)));
        if (!StringUtils.isEmpty(messageId)) {
            reportData.setMessageId(messageId);
        }
        if (!StringUtils.isEmpty(content)) {
            reportData.setContent(content);
        }
        // TO DO 优先级
        reportData.setTriggerTime(String.valueOf(System.currentTimeMillis()));
        reportData.setDeviceId(SystemExtUtils.getDeviceId(context));
        if (CommonUtils.isNetworkAvailable(context)) {
            reportData.setIpAddr(CommonUtils.getHostIp());
        }

        // 依据策略执行
        StatisticAgent statisticAgent = StatisticAgent.getInstance();
        if (statisticAgent.mPolicy != null) {
            statisticAgent.mPolicy.execute(reportData);
        }
    }

}
