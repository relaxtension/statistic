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
     * 实时策略
     */
    public static final int POLICY_REAL_TIME = 0;

    /**
     * 周期策略
     */
    public static final int POLICY_CYCLE_TIME = 1;

    /**
     * 定时策略
     */
    public static final int POLICY_FIXED_TIME = 2;

    /**
     * 默认策略
     */
    private static final int DEFAULT_POLICY = POLICY_CYCLE_TIME;

    /**
     * 周期策略的默认时间间隔（秒），1小时
     */
    private static final long DEFAULT_INTERVAL_TIME = 60 * 60;

    /**
     * 定时策略的默认小时数
     */
    private static final int DEFAULT_HOUR = 22;

    /**
     * 定时策略的默认分钟数
     */
    private static final int DEFAULT_MINUTE = 0;

    /**
     * 默认服务器地址
     */
    private static final String DEFAULT_URL = "http://publicobject.com/helloworld.txt";

    /**
     * 默认请求连接超时时间（秒）
     */
    private static final long DEFAULT_CONN_TIMEOUT = 30L;

    /**
     * 默认请求读取超时时间（秒）
     */
    private static final long DEFAULT_READ_TIMEOUT = 30L;

    /**
     * 默认请求写入超时时间（秒）
     */
    private static final long DEFAULT_WRITE_TIMEOUT = 30L;

    /**
     * 统计策略
     */
    private int mPolicy = DEFAULT_POLICY;

    /**
     * 周期策略的时间间隔（秒）
     */
    private long mIntervalTime = DEFAULT_INTERVAL_TIME;

    /**
     * 定时策略的小时数
     */
    private int mHour = DEFAULT_HOUR;

    /**
     * 定时策略的分钟数
     */
    private int mMinute = DEFAULT_MINUTE;

    /**
     * 上报地址
     */
    private String mUrl = DEFAULT_URL;

    /**
     * 请求连接超时时间（秒）
     */
    private long mConnTimeout = DEFAULT_CONN_TIMEOUT;

    /**
     * 请求读取超时时间（秒）
     */
    private long mReadTimeout = DEFAULT_READ_TIMEOUT;

    /**
     * 请求写入超时时间（秒）
     */
    private long mWriteTimeout = DEFAULT_WRITE_TIMEOUT;

    public int getPolicy() {
        return mPolicy;
    }

    public StatisticConfig setPolicy(int policy) {
        this.mPolicy = policy;
        return this;
    }

    public long getIntervalTime() {
        return mIntervalTime;
    }

    public StatisticConfig setIntervalTime(long intervalTime) {
        this.mIntervalTime = intervalTime;
        return this;
    }

    public int getHour() {
        return mHour;
    }

    public StatisticConfig setHour(int hour) {
        this.mHour = hour;
        return this;
    }

    public int getMinute() {
        return mMinute;
    }

    public StatisticConfig setMinute(int minute) {
        this.mMinute = minute;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public StatisticConfig setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public long getConnTimeout() {
        return mConnTimeout;
    }

    public StatisticConfig setConnTimeout(long connTimeout) {
        this.mConnTimeout = connTimeout;
        return this;
    }

    public long getReadTimeout() {
        return mReadTimeout;
    }

    public StatisticConfig setReadTimeout(long readTimeout) {
        this.mReadTimeout = readTimeout;
        return this;
    }

    public long getWriteTimeout() {
        return mWriteTimeout;
    }

    public StatisticConfig setWriteTimeout(long writeTimeout) {
        this.mWriteTimeout = writeTimeout;
        return this;
    }
}
