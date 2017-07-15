package com.weixinxk.statistic.policy;

import android.content.Context;

import com.weixinxk.statistic.StatisticAgent;
import com.weixinxk.statistic.model.ReportData;

/**********************************************************************
 * 基础策略接口
 *
 * @类名 BasePolicy
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/14
 ***********************************************************************/
public abstract class BasePolicy {

    protected StatisticAgent mAgent;

    public BasePolicy(StatisticAgent agent) {
        this.mAgent = agent;
    }

    public abstract void execute(ReportData reportData);
}
