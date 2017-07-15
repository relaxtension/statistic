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
 * 基础数据
 *
 * @类名 BaseData
 * @包名 com.weixinxk.statistic.model
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/12
 *
 ***********************************************************************/

public class BaseData {
    /**
     * 统计ID
     */
    private transient String mId;

    /**
     * 事件ID
     */
    private transient String mEventId;

    /**
     * 事件类型
     */
    @SerializedName("type")
    private String mEventType;

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
     * 优先级
     */
    private transient String mPriority;

    /**
     * 触发时间
     */
    private transient String mTriggerTime;

    public void setId(String id) {
        this.mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setEventId(String id) {
        this.mEventId = id;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventType(String type) {
        this.mEventType = type;
    }

    public String getEventType() {
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

    public void setPriority(String priority) {
        this.mPriority = priority;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setTriggerTime(String time) {
        this.mTriggerTime = time;
    }

    public String getTriggerTime() {
        return mTriggerTime;
    }

}
