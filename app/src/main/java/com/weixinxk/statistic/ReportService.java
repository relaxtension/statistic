package com.weixinxk.statistic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**********************************************************************
 * 上报服务
 *
 * @类名 ReportService
 * @包名 com.weixinxk.statistic
 * @author zhangchi
 * @创建日期 2017/7/12
 ***********************************************************************/
public class ReportService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    
}
