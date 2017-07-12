/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.mode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/***********************************************************************
 *
 * 基本数据模块
 *
 * @类名 BaseBean
 * @包名 com.example.lisichen.statistic_db.mode
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/12
 *
 ***********************************************************************/

public class BaseBean {
    /**
     * 统计ID
     */
    private transient int mStatisticId;

    /**
     * 事件ID
     */
    private transient String mEventId;

    /**
     * 事件类型
     */
    @SerializedName("type")
    @Expose private int mEventType;

    /**
     * 消息ID
     */
    @SerializedName("msg_id")
    private String mMessageId;

    /**
     * 内容
     */
    @SerializedName("val")
    private String mContent;

    /**
     * 测量时间
     */
    private transient long mMeasureTime;

    /**
     * 统计级别
     */
    private transient int mStatisticLevel;

    public void setStatisticId(int id) {
        this.mStatisticId = id;
    }

    public int getStatisticId() {
        return mStatisticId;
    }

    public void setEventId(String id) {
        this.mEventId = id;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventType(int type) {
        this.mEventType = type;
    }

    public int getEventType() {
        return mEventType;
    }

    public void setMessageId(String messageId) {
        this.mMessageId = messageId;
    }

    public String getMessageId() {
        return mMessageId;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public void setStatisticLevel(int level) {
        this.mStatisticLevel = level;
    }

    public int getStatisticLevel() {
        return mStatisticLevel;
    }

    public void setMeasureTime(long time) {
        this.mMeasureTime = time;
    }

    public long getMeasureTime() {
        return mMeasureTime;
    }
}
