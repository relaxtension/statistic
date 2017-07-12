package com.weixinxk.statistic.policy;

import android.os.Bundle;

/**********************************************************************
 * 实时策略类
 *
 * @类名 RealTimeTask
 * @包名 com.weixinxk.statistic.policy
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class RealTimeTask extends StatisticTask {

    public RealTimeTask(int priority) {
        super(priority);

    }

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
            判断是否联网。
            如没联网，则保存采集信息到数据库。
            如已联网，则发送高优先级网络请求。
            请求成功，则删除存在的相关记录；请求失败或出错，则保存到数据库。
            完成后执行上报服务。
        */

    }
}
