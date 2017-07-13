package com.weixinxk.statistic;

import android.content.Context;
import android.support.annotation.NonNull;

import com.frogshealth.statistic.IdMatcher;
import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.policy.StatisticTask;
import com.weixinxk.statistic.utils.EventMatcher;
import com.weixinxk.statistic.utils.StringUtils;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**********************************************************************
 * 统计管理类
 *
 * @类名 StatisticManager
 * @包名 com.weixinxk.statistic
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class StatisticManager {

    private Context context;
    private StatisticConfig statisticConfig;

    private ExecutorService threadPool;
    private PriorityBlockingQueue<StatisticTask> taskQueue;

    public StatisticManager(Context context) {
        this.context = context;
        this.taskQueue = new PriorityBlockingQueue<StatisticTask>();
    }

    public void start() {
        threadPool = Executors.newSingleThreadExecutor();
        while (!taskQueue.isEmpty()) {
            StatisticTask task = taskQueue.poll();
            if (task != null) {
                threadPool.execute(task);
            }
        }
    }

    public void stop() {
        threadPool.shutdown();
    }

    public boolean isRunning() {
        if (threadPool == null) {
            return false;
        }
        return threadPool.isShutdown() || threadPool.isTerminated();
    }

    public boolean hasTask(String id) {
        Iterator<StatisticTask> iterator = taskQueue.iterator();
        while (iterator.hasNext()) {
            StatisticTask task = iterator.next();
            if (id.equals(task.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean addTask(String id) {
//        if (this.hasTask(id)) {
//            return false;
//        }
//        return taskQueue.offer(new DownloadThread(context, ++id, url));
        return false;
    }

    public static void onEvent(Context context, @NonNull String eventId, String messageId, String content) {
        ReportData reportData = new ReportData();
        reportData.setEventId(eventId);
        reportData.setEventType(EventMatcher.getEventType(eventId));
        if (!StringUtils.isEmpty(messageId)) {
            reportData.setMessageId(messageId);
        }
        if (!StringUtils.isEmpty(content)) {
            reportData.setContent(content);
        }


        onEvent(context, IdMatcher.getPageId(event_id) ,event_id, 1);
    }

}
