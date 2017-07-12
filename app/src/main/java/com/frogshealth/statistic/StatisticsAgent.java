package com.frogshealth.statistic;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.frogshealth.photoframe.common.logger.LoggerUtil;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**********************************************************************
 * 统计相关代理类
 * @author sundi
 * @类名 StatisticsAgent
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/1
 ***********************************************************************/
public class StatisticsAgent {
    /**
     * Handler
     */
    private static Handler sHandler;

    /**
     * 计时器
     */
    private static ScheduledExecutorService sScheduledExecutorService = null;
    /**
     * 上下文弱引用
     */
    private static WeakReference<Context> sContextWeakReference;
    /**
     * 是否初始化标志
     */
    private static boolean sInit = false;

    static {
        HandlerThread handlerThread = new HandlerThread("StatisticsAgent");
        handlerThread.start();
        sHandler = new Handler(handlerThread.getLooper());
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        sInit = true;
        updateContext(context);
        init();
    }

    /**
     * 初始化
     */
    private static void init() {
        LoggerUtil.getDefaultLogger().info("Call init()");
        postCacheLog();
        startTimer();
    }

    /**
     * 上传缓存
     */
    private static void postCacheLog() {
        LoggerUtil.getDefaultLogger().info("Call postCacheLog()");
        if (CommonUtil.isNetworkAvailable(sContextWeakReference.get())) {
            Runnable runnable = new UploadCacheLog(sContextWeakReference.get());
            sHandler.post(runnable);
        }
    }


    /**
     * 启动计时器，上传缓存数据。
     */
    private static void startTimer() {
        sScheduledExecutorService = Executors.newScheduledThreadPool(1);
        sScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Runnable runnable = new UploadCacheLog(sContextWeakReference.get());
                sHandler.post(runnable);
                LoggerUtil.getDefaultLogger().info("startTimer()");
            }
        }, StatisticsConstants.DEFAULT_INTERVAL_TIME, StatisticsConstants.DEFAULT_INTERVAL_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 更新上下文引用
     * @param context 上下文
     */
    private static void updateContext(Context context) {
        sContextWeakReference = new WeakReference<Context>(context);
        context = null;
    }

    /**
     * 发送event事件数据
     *
     * @param context 上下文
     * @param event_id 事件ID
     */
    public static void onEvent(Context context, final String event_id) {
        onEvent(context, IdMatcher.getPageId(event_id) ,event_id, 1);
    }

    /**
     * 发送event事件数据
     *
     * @param context 上下文
     * @param event_id 事件ID
     * @param acc 计数
     */
    public static void onEvent(Context context, final String event_id, final int acc) {
        onEvent(context, IdMatcher.getPageId(event_id) ,event_id, acc);
    }

    /**
     * 发送event事件数据
     *
     * @param context 上下文
     * @param page_id 页面ID
     * @param event_id 事件ID
     */
    public static void onEvent(Context context, final String page_id, final String event_id) {
        onEvent(context, page_id ,event_id, 1);
    }

    /**
     * 发送event事件数据
     *
     * @param context 上下文
     * @param event_id 事件ID
     * @param page_id 页面ID
     * @param acc 计数
     */
    public static void onEvent(Context context, final String page_id , final String event_id, final int acc) {
        if (!sInit) {
            LoggerUtil.getDefaultLogger().error("sdk is not init!");
            return;
        }
        if (event_id == null || event_id.length() == 0) {
            LoggerUtil.getDefaultLogger().error("Valid event_id is required");
            return;
        }

        if (page_id == null || page_id.length() == 0) {
            LoggerUtil.getDefaultLogger().error("Valid page_id is required");
            return;
        }

        if (acc < 1) {
            LoggerUtil.getDefaultLogger().error("Event acc should be greater than zero");
            return;
        }
        updateContext(context);
        Runnable runnable = new Runnable() {
            public void run() {
                LoggerUtil.getDefaultLogger().debug("Call onEvent(context,event_id)");
                EventManager eventManager = new EventManager(sContextWeakReference.get(), page_id,event_id, acc);
                eventManager.postEvent();
            }
        };
        sHandler.post(runnable);
    }

    /**
     * Activity画面启动时调用，用于更新context
     * @param context 上下文
     */
    public static void onResume(Context context) {
        if (!sInit) {
            LoggerUtil.getDefaultLogger().error("sdk is not init!");
            return;
        }
        updateContext(context);
    }
}
