package com.weixinxk.statistic.report;

import android.content.Context;
import android.database.Cursor;

import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.consts.DbConsts;
import com.weixinxk.statistic.db.StatisticDao;
import com.weixinxk.statistic.model.ReportData;
import com.weixinxk.statistic.task.ReportTask;
import com.weixinxk.statistic.utils.CommonUtils;
import com.weixinxk.statistic.utils.SystemExtUtils;

import java.util.concurrent.ArrayBlockingQueue;

/**********************************************************************
 * 上报服务的生产线程
 *
 * @类名 ReportTaskProducer
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/15
 ***********************************************************************/
public class ReportTaskProducer implements Runnable {

    private StatisticAgent mAgent;
    private ArrayBlockingQueue<ReportTask> mTaskQueue;

    public ReportTaskProducer(StatisticAgent agent, ArrayBlockingQueue<ReportTask> taskQueue) {
        this.mAgent = agent;
        this.mTaskQueue = taskQueue;
    }

    @Override
    public void run() {
        if (mAgent == null) {
            return;
        }

        Context context = mAgent.getApplication().getApplicationContext();

        StatisticDao statisticDao = new StatisticDao(context);
        Cursor cursor = statisticDao.query(System.currentTimeMillis());
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(DbConsts.STATISTIC_ID));
            String eventId = cursor.getString(cursor.getColumnIndex(DbConsts.STATISTIC_EVENT_ID));
            String eventType =  cursor.getString(cursor.getColumnIndex(DbConsts.STATISTIC_EVENT_TYPE));
            String messageId =  cursor.getString(cursor.getColumnIndex(DbConsts.STATISTIC_MESSAGE_ID));
            String content =  cursor.getString(cursor.getColumnIndex(DbConsts.STATISTIC_CONTENT));
            String priority =  cursor.getString(cursor.getColumnIndex(DbConsts.STATISTIC_PRIORITY));
            String triggerTime =  cursor.getString(cursor.getColumnIndex(DbConsts.STATISTIC_TRIGGER_TIME));

            ReportData reportData = new ReportData();
            reportData.setId(id);
            reportData.setEventId(eventId);
            reportData.setEventType(eventType);
            reportData.setMessageId(messageId);
            reportData.setContent(content);
            reportData.setPriority(priority);
            reportData.setTriggerTime(triggerTime);
            reportData.setDeviceId(SystemExtUtils.getDeviceId(context));
            if (CommonUtils.isNetworkAvailable(context)) {
                reportData.setIpAddr(CommonUtils.getHostIp());
            }

            try {
                ReportTask reportTask = new ReportTask(mAgent, reportData);
                mTaskQueue.put(reportTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
