/**********************************************************************
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 **********************************************************************/
package com.frogshealth.statistic;

import android.content.Context;

import com.frogshealth.photoframe.common.logger.LoggerUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**********************************************************************
 * 事件处理管理类
 * @author sundi
 * @类名 EventManager
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/1
 ***********************************************************************/
public class EventManager {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 事件ID
     */
    private String mEventId;

    /**
     * 页面ID
     */
    private String mPageId;

    public EventManager(Context context, final String page_id, final String event_id, final int acc) {
        mContext = context;
        mEventId = event_id;
        mPageId = page_id;
    }

    /**
     * 保存事件,一定时间间隔后上传
     */
    public void postEvent() {
        JSONObject jsonObject = prepareEvent();
        CommonUtil.saveInfoToFile(mContext,StatisticsConstants.TYPE_EVENT, jsonObject);
    }

    /**
     * 生成事件对象
     * @return 事件对象
     */
    private JSONObject prepareEvent() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(StatisticsConstants.KEY_TIME, System.currentTimeMillis());
//            jsonObject.put(StatisticsConstants.KEY_DEVICE_ID, DeviceSnUtil.getDeviceSn());
            jsonObject.put(StatisticsConstants.KEY_PAGE_ID, mPageId);
            jsonObject.put(StatisticsConstants.KEY_EVENT_ID, mEventId);
            jsonObject.put(StatisticsConstants.KEY_VIEW_TYPE, IdMatcher.getTargetViewType(mEventId));
            jsonObject.put(StatisticsConstants.KEY_OPERATION_TYPE, IdMatcher.getOperation(mEventId));
//            jsonObject.put(StatisticsConstants.KEY_ACC, mAcc);
//            jsonObject.put(StatisticsConstants.KEY_VERSION, CommonUtil.getCurVersionName(mContext));
//            jsonObject.put(StatisticsConstants.KEY_APP_ID, StatisticsConstants.VALUE_APP_ID);
        } catch (JSONException e) {
            LoggerUtil.getDefaultLogger().error(e.getMessage());
        }
        return jsonObject;
    }



}
