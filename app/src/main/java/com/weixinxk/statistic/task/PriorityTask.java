package com.weixinxk.statistic.task;

import android.support.annotation.NonNull;

/**********************************************************************
 * 优先级任务抽象类
 *
 * @类名 PriorityTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/15
 ***********************************************************************/
public abstract class PriorityTask implements BaseTask, Comparable<PriorityTask> {

    protected int mPriority;

    public PriorityTask(int priority) {
        this.mPriority = priority;
    }

    @Override
    public int compareTo(@NonNull PriorityTask o) {
        if (mPriority < o.mPriority) {
            return -1;
        } else {
            if (mPriority > o.mPriority) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
