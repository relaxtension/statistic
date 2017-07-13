/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.utils;

/***********************************************************************
 *
 * 统计相关常量配置
 *
 * @类名 StatisticConfig
 * @包名 com.example.lisichen.statistic_db
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/10
 *
 ***********************************************************************/

public class StatisticConfig {
    /**
     * 默认服务器地址
     */
    public static final String DEFAULT_URL = "http://publicobject.com/helloworld.txt";
    /**
     * 读取超时时长
     */
    public static final int READ_TIME_OUT = 30000;

    /**
     * 连接超时时长
     */
    public static final int CONN_TIME_OUT = 30000;

    /**
     * 实时策略
     */
    public static final int STRATEGY_REAL_TIME = 0;

    /**
     * 固定时间策略
     */
    public static final int STRATEGY_FIXED_TIME = 1;

    /**
     * 周期时间策略
     */
    public static final int STRATEGY_CIRCLE_TIME = 2;

    /**
     * 默认周期，1小时
     */
    public static final long DEFAULT_PERIODIC_TIME = 60 * 60 * 1000;

    /**
     * 服务器地址
     */
    private String mServerUrl = "";

    /**
     * 周期时间(ms)
     */
    private long mPeriodicTime = -1;

    /**
     * 时间策略
     */
    private int mTimeStrategy = -1;


    public void setServerUrl(String url) {
        this.mServerUrl = url;
    }

    public String getServerUrl() {
        if(mServerUrl == null || mServerUrl.isEmpty()) {
            return DEFAULT_URL;
        }
        return mServerUrl;
    }

    public void setPeriodicTime(long time) {
        this.mPeriodicTime = time;
    }

    public long getPeriodicTime() {
        if(mPeriodicTime == -1) {
            return DEFAULT_PERIODIC_TIME;
        }
        return mPeriodicTime;
    }

    public void setTimeStrategy(int strategy) {
        this.mTimeStrategy = strategy;
    }

    public int getTimeStrategy() {
        return mTimeStrategy;
    }
}
