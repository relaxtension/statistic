package com.weixinxk.statistic.util;

import android.support.v4.util.ArrayMap;

/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016 
 *
 * @类名 IdMatcher
 * @包名 com.example.lisichen.statistic_db.util
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/12
 *
 ***********************************************************************/

public class IdMatcher {

    /**
     * 推送成功事件id
     */
    public static final String EVENT_ID_PUSH_MESSAGE_SUCCESS = "push_success";

    /**
     * 撤销成功事件id
     */
    public static final String EVENT_ID_REVOKE_MESSAGE_SUCCESS = "revoke_success";

    /**
     * 阅读消息事件id
     */
    public static final String EVENT_ID_READ_MESSAGE = "read_message";

    /**
     * 激活事件id
     */
    public static final String EVENT_ID_ACTIVATION = "activation";

    /**
     * 启动应用事件id
     */
    public static final String EVENT_ID_START_APP = "start_app";

    /**
     * 传送照片事件id
     */
    public static final String EVENT_ID_SEND_PHOTO = "send_photo";

    /**
     * 绑定用户数事件id
     */
    public static final String EVENT_ID_BIND_USER_COUNT = "bind_user_count";

    /**
     * 推送成功事件类型
     */
    public static final int EVENT_TYPE_PUSH_MESSAGE_SUCCESS = 1;

    /**
     * 撤销成功事件类型
     */
    public static final int EVENT_TYPE_REVOKE_MESSAGE_SUCCESS = 2;

    /**
     * 阅读消息事件类型
     */
    public static final int EVENT_TYPE_READ_MESSAGE = 3;

    /**
     * 激活事件类型
     */
    public static final int EVENT_TYPE_ACTIVATION = 4;

    /**
     * 启动应用事件类型
     */
    public static final int EVENT_TYPE_START_APP = 5;

    /**
     * 传送照片事件类型
     */
    public static final int EVENT_TYPE_SEND_PHOTO = 6;

    /**
     * 绑定用户数事件类型
     */
    public static final int EVENT_TYPE_BIND_USER_COUNT = 7;
    /**
     * 事件ID和类型匹配器
     */
    private static ArrayMap<String, Integer> sEventTypeMatcher = new ArrayMap<>();
    static {
        sEventTypeMatcher.put(EVENT_ID_PUSH_MESSAGE_SUCCESS, EVENT_TYPE_PUSH_MESSAGE_SUCCESS);
        sEventTypeMatcher.put(EVENT_ID_REVOKE_MESSAGE_SUCCESS, EVENT_TYPE_REVOKE_MESSAGE_SUCCESS);
        sEventTypeMatcher.put(EVENT_ID_READ_MESSAGE, EVENT_TYPE_READ_MESSAGE);
        sEventTypeMatcher.put(EVENT_ID_ACTIVATION, EVENT_TYPE_ACTIVATION);
        sEventTypeMatcher.put(EVENT_ID_START_APP, EVENT_TYPE_START_APP);
        sEventTypeMatcher.put(EVENT_ID_SEND_PHOTO, EVENT_TYPE_SEND_PHOTO);
        sEventTypeMatcher.put(EVENT_ID_BIND_USER_COUNT, EVENT_TYPE_BIND_USER_COUNT);
    }


    public int getEventType(String eventId) {
        if (eventId == null) {
            return -1;
        }
        return sEventTypeMatcher.get(eventId);
    }
}
