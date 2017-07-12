package com.weixinxk.statistic.policy;

import android.support.annotation.NonNull;

import com.weixinxk.statistic.task.ProgressSender;
import com.weixinxk.statistic.task.TaskResult;

/**********************************************************************
 * 周期策略类
 *
 * @类名 CycleTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class CycleTask implements StatisticTask {

    @Override
    public void init() {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void run() {

        /*
            保存采集信息到数据库。
            当定时器到时执行上报服务。
         */

    }

    @Override
    public int compareTo(@NonNull StatisticTask o) {
        return 0;
    }
}
