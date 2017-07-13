/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/***********************************************************************
 *
 * 上传数据
 *
 * @类名 ReportData
 * @包名 com.weixinxk.statistic.model
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/12
 *
 ***********************************************************************/

public class ReportData extends BaseData {

    @SerializedName("device_id")
    private String mDeviceId;

    @SerializedName("ip_addr")
    private String mIpAddr;

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public void setIpAddr(String ipAddr) {
        this.mIpAddr = ipAddr;
    }

    public String getIpAddr() {
        return mIpAddr;
    }
}
