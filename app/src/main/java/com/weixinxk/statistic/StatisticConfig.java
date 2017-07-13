package com.weixinxk.statistic;

/**********************************************************************
 * 统计配置类
 *
 * @类名 StatisticConfig
 * @包名 com.weixinxk.statistic
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class StatisticConfig {

    public static final int POLICY_REAL_TIME = 1;
    public static final int POLICY_CYCLE = 2;
    public static final int POLICY_AT_TIME = 3;

    private int policy = POLICY_CYCLE;

    public StatisticConfig() {

    }


}
