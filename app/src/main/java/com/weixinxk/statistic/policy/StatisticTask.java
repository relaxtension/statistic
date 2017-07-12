package com.weixinxk.statistic.policy;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.weixinxk.statistic.task.Task;

/**********************************************************************
 * 统计策略接口
 *
 * @类名 StatisticTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public abstract class StatisticTask implements Runnable, Comparable<StatisticTask> {

    private final int priority;
    // protected BaseBean baseBean;

    public StatisticTask(String eventId, Bundle bundle, @NonNull int priority) {

        this.priority = priority;
    }

    public abstract void init();

    public abstract String getId();

    @Override
    public int compareTo(@NonNull StatisticTask o) {
        if (priority < o.priority) {
            return -1;
        } else {
            if (priority > o.priority) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
