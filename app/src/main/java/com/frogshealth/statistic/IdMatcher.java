/**********************************************************************
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 **********************************************************************/
package com.frogshealth.statistic;

import android.util.ArrayMap;

import com.frogshealth.photoframe.R;

/**********************************************************************
 * ID匹配器,用于维护画面对象和事件ID的关系
 *
 * @author sundi
 * @类名 IdMatcher
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/6
 ***********************************************************************/
public class IdMatcher {
    /**
     * 全屏照片页面
     */
    public static final String PAGE_FULL_IMAGE = "page_full_image";

    /**
     * 点击照片
     */
    public static final String EVENT_CLICK_IMAGE = "event_click_image";

    /**
     * 点击用户头像
     */
    public static final String EVENT_HEAD_PORTRAIT = "event_head_portrait";

    /**
     * 点赞
     */
    public static final String EVENT_LIKED = "event_liked";

    /**
     * 评论
     */
    public static final String EVENT_COMMENT = "event_comment";

    /**
     * 查看健康数据
     */
    public static final String EVENT_HEALTH_DATA = "event_health_data";

    /**
     * 浏览照片缩略图
     */
    public static final String EVENT_THUMBNAIL_IMAGE = "event_thumbnail_image";

    /**
     * 点击社工按钮
     */
    public static final String EVENT_SOCIAL_WORKER = "event_social_worker";

    /**
     * 点击环境按钮
     */
    public static final String EVENT_ENVIRONMENT = "event_environment";

    /**
     * 相片向右滑动事件
     */
    public static final String EVENT_IMAGE_MOVE_RIGHT = "event_image_move_right";

    /**
     * 相片向左滑动事件
     */
    public static final String EVENT_IMAGE_MOVE_LEFT = "event_image_move_left";

    /**
     * 相片向上滑动事件
     */
    public static final String EVENT_IMAGE_MOVE_UP = "event_image_move_up";

    /**
     * 相片向下滑动事件
     */
    public static final String EVENT_IMAGE_MOVE_DOWN = "event_image_move_down";

    /**
     * 健康数据页面
     */
    public static final String PAGE_HEALTH_DATA = "page_health_data";

    /**
     * 点击所有人按钮
     */
    public static final String EVENT_ALL_USER = "event_all_user";

    /**
     * 查看血氧数据
     */
    public static final String EVENT_SPO2H = "event_spo2h";

    /**
     * 查看血压数据
     */
    public static final String EVENT_BLOOD_PRESSURE = "event_blood_pressure";

    /**
     * 查看体重数据
     */
    public static final String EVENT_WEIGHT = "event_weight";

    /**
     * 查看血糖数据
     */
    public static final String EVENT_BLOOD_GLUCOSE = "event_blood_glucose";

    /**
     * 查看生活环境数据
     */
    public static final String EVENT_ENVIRONMENT_CARD = "event_environment_card";

    /**
     * 查看水温数据
     */
    public static final String EVENT_WATER_CARD = "event_water_card";

    /**
     * 查看耳温计数据
     */
    public static final String EVENT_EAR_THERMOMETER = "event_ear_thermometer";

    /**
     * 查看推送数据
     */
    public static final String EVENT_PUSH_MESSAGE_CARD = "event_push_message_card";

    /**
     * 快捷评论照片页面
     */
    public static final String PAGE_QUICK_COMMENT = "page_quick_comment";

    /**
     * 点击快捷评论内容:好想和你一起
     */
    public static final String EVENT_COMMENT_1_1 = "event_comment_1_1";

    /**
     * 点击快捷评论内容:又变漂亮了
     */
    public static final String EVENT_COMMENT_1_2 = "event_comment_1_2";

    /**
     * 点击快捷评论内容:一家人真好
     */
    public static final String EVENT_COMMENT_2_1 = "event_comment_2_1";

    /**
     * 点击快捷评论内容:要健健康康的
     */
    public static final String EVENT_COMMENT_2_2 = "event_comment_2_2";

    /**
     * 点击快捷评论内容:照片拍的真好
     */
    public static final String EVENT_COMMENT_3_1 = "event_comment_3_1";

    /**
     * 点击快捷评论内容:好幸福
     */
    public static final String EVENT_COMMENT_3_2 = "event_comment_3_2";

    /**
     * 回复评论弹出的快捷弹框
     */
    public static final String PAGE_QUICK_REPLY = "page_quick_reply";

    /**
     * 回复内容：下次我们一起
     */
    public static final String EVENT_REPLY_1_1 = "event_reply_1_1";

    /**
     * 回复内容：谢谢关心
     */
    public static final String EVENT_REPLY_1_2 = "event_reply_1_2";

    /**
     * 回复内容：这里确实很美
     */
    public static final String EVENT_REPLY_2_1 = "event_reply_2_1";

    /**
     * 回复内容：大家很想你
     */
    public static final String EVENT_REPLY_2_2 = "event_reply_2_2";

    /**
     * 回复内容：照相技术不错吧
     */
    public static final String EVENT_REPLY_3_1 = "event_reply_3_1";

    /**
     * 回复内容：什么时候回来
     */
    public static final String EVENT_REPLY_3_2 = "event_reply_3_2";

    /**
     * 呼叫社工页面
     */
    public static final String PAGE_SOCIAL_WORKER = "page_social_worker";

    /**
     * 点击报警按钮事件
     */
    public static final String EVENT_URGENT_0_1 = "event_urgent_0_1";

    /**
     * 点击呼叫医生按钮事件
     */
    public static final String EVENT_URGENT_0_2 = "event_urgent_0_2";

    /**
     * 点击被盗按钮事件
     */
    public static final String EVENT_URGENT_0_3 = "event_urgent_0_3";

    /**
     * 点击发送信息内容:有时间吗
     */
    public static final String EVENT_MESSAGE_1_1 = "event_message_1_1";

    /**
     * 点击发送信息内容:我这里需要帮忙
     */
    public static final String EVENT_MESSAGE_1_2 = "event_message_1_2";

    /**
     * 点击发送信息内容:来看看我
     */
    public static final String EVENT_MESSAGE_1_3 = "event_message_1_3";

    /**
     * 点击发送信息内容:来陪我聊会天吧
     */
    public static final String EVENT_MESSAGE_2_1 = "event_message_2_1";

    /**
     * 点击发送信息内容:来陪我走走吧
     */
    public static final String EVENT_MESSAGE_2_2 = "event_message_2_2";

    /**
     * 点击发送信息内容:一起吃饭吧
     */
    public static final String EVENT_MESSAGE_2_3 = "event_message_2_3";

    /**
     * 缩略图照片页面
     */
    public static final String PAGE_THUMBNAIL_IMAGE = "page_thumbnail_image";

    /**
     * 点击相框设置按钮
     */
    public static final String EVENT_SETTINGS = "event_settings";

    /**
     * 点击照片管理按钮
     */
    public static final String EVENT_IMAGE_MANAGEMENT = "event_image_management";

    /**
     * 照片管理页面
     */
    public static final String PAGE_IMAGE_MANAGEMENT = "page_image_management";

    /**
     * 选择照片
     */
    public static final String EVENT_IMAGE_SELECTED = "event_image_selected";

    /**
     * 删除照片
     */
    public static final String EVENT_IMAGE_DELETED = "event_image_deleted";

    /**
     * 点击退出
     */
    public static final String EVENT_QUIT_IMAGE_MANAGEMENT = "event_quit_image_management";

    /**
     * 相框设置页面
     */
    public static final String PAGE_SETTINGS = "page_settings";

    /**
     * 点击相框信息按钮
     */
    public static final String EVENT_PHOTO_FRAME_INFO = "event_photo_frame_info";

    /**
     * 点击WIFI设置按钮
     */
    public static final String EVENT_WIFI = "event_wifi";

    /**
     * 点击绑定的设备按钮
     */
    public static final String EVENT_DEVICE_BINDED = "event_device_binded";

    /**
     * 点击声音及显示按钮
     */
    public static final String EVENT_SOUND_DISPLAY = "event_sound_display";

    /**
     * 点击高级设置按钮
     */
    public static final String EVENT_ADVANCED_SETTING = "event_advanced_setting";

    /**
     * 点击关于按钮
     */
    public static final String EVENT_ABOUT = "event_about";

    /**
     * 控件类型:button
     */
    public static final String TYPE_BUTTON = "button";

    /**
     * 控件类型:image
     */
    public static final String TYPE_IMAGE = "image";

    /**
     * 操作类型:click
     */
    public static final String OPERATION_CLICK = "click";

    /**
     * 操作类型:touch_up
     */
    public static final String OPERATION_TOUCH_UP = "touch_up";

    /**
     * 操作类型:touch_down
     */
    public static final String OPERATION_TOUCH_DOWN = "touch_down";

    /**
     * 操作类型:touch_left
     */
    public static final String OPERATION_TOUCH_LEFT = "touch_left";

    /**
     * 操作类型:touch_right
     */
    public static final String OPERATION_TOUCH_RIGHT = "touch_right";

    /**
     * 事件ID匹配器
     */
    private static ArrayMap<String, String> sEventMatcher = new ArrayMap<>();

    /**
     * 画面ID匹配器
     */
    private static ArrayMap<String, String> sPageMatcher = new ArrayMap<>();

    static {
        //事件ID映射
        sEventMatcher.put(String.valueOf(R.id.image_view), EVENT_CLICK_IMAGE);
        sEventMatcher.put(String.valueOf(R.id.headportrait), EVENT_HEAD_PORTRAIT);
        sEventMatcher.put(String.valueOf(R.id.praise_btn_ib), EVENT_LIKED);
        sEventMatcher.put(String.valueOf(R.id.message), EVENT_COMMENT);
        sEventMatcher.put(String.valueOf(R.id.tingzhenqi), EVENT_HEALTH_DATA);
        sEventMatcher.put(String.valueOf(R.id.menu), EVENT_THUMBNAIL_IMAGE);
        sEventMatcher.put(String.valueOf(R.id.social_worker), EVENT_SOCIAL_WORKER);
        sEventMatcher.put(String.valueOf(R.id.life_environment_pm25), EVENT_ENVIRONMENT);
        sEventMatcher.put(EVENT_IMAGE_MOVE_RIGHT, EVENT_IMAGE_MOVE_RIGHT);
        sEventMatcher.put(EVENT_IMAGE_MOVE_LEFT, EVENT_IMAGE_MOVE_LEFT);
        sEventMatcher.put(EVENT_IMAGE_MOVE_UP, EVENT_IMAGE_MOVE_UP);
        sEventMatcher.put(EVENT_IMAGE_MOVE_DOWN, EVENT_IMAGE_MOVE_DOWN);

        sEventMatcher.put(String.valueOf(R.id.allPeople), EVENT_ALL_USER);
        sEventMatcher.put(String.valueOf(R.drawable.type_blood_oxygen), EVENT_SPO2H);
        sEventMatcher.put(String.valueOf(R.drawable.type_blood_pressure), EVENT_BLOOD_PRESSURE);
        sEventMatcher.put(String.valueOf(R.drawable.type_body_fat), EVENT_WEIGHT);
        sEventMatcher.put(String.valueOf(R.drawable.type_blood_sugar), EVENT_BLOOD_GLUCOSE);
        sEventMatcher.put(String.valueOf(R.drawable.type_env), EVENT_ENVIRONMENT_CARD);
        sEventMatcher.put(String.valueOf(R.drawable.type_water), EVENT_WATER_CARD);
        sEventMatcher.put(String.valueOf(R.drawable.type_temperature), EVENT_EAR_THERMOMETER);
        sEventMatcher.put(String.valueOf(R.drawable.type_health_service), EVENT_PUSH_MESSAGE_CARD);

        //自定义id,在getEventId()接口调用处需使用相同ID规则。
        sEventMatcher.put(String.valueOf(R.array.type_discuss) + "_0", EVENT_COMMENT_1_1);
        sEventMatcher.put(String.valueOf(R.array.type_discuss) + "_1", EVENT_COMMENT_1_2);
        sEventMatcher.put(String.valueOf(R.array.type_discuss) + "_2", EVENT_COMMENT_2_1);
        sEventMatcher.put(String.valueOf(R.array.type_discuss) + "_3", EVENT_COMMENT_2_2);
        sEventMatcher.put(String.valueOf(R.array.type_discuss) + "_4", EVENT_COMMENT_3_1);
        sEventMatcher.put(String.valueOf(R.array.type_discuss) + "_5", EVENT_COMMENT_3_2);
        //自定义id,在getEventId()接口调用处需使用相同ID规则。
        sEventMatcher.put(String.valueOf(R.array.type_response) + "_0", EVENT_REPLY_1_1);
        sEventMatcher.put(String.valueOf(R.array.type_response) + "_1", EVENT_REPLY_1_2);
        sEventMatcher.put(String.valueOf(R.array.type_response) + "_2", EVENT_REPLY_2_1);
        sEventMatcher.put(String.valueOf(R.array.type_response) + "_3", EVENT_REPLY_2_2);
        sEventMatcher.put(String.valueOf(R.array.type_response) + "_4", EVENT_REPLY_3_1);
        sEventMatcher.put(String.valueOf(R.array.type_response) + "_5", EVENT_REPLY_3_2);

        sEventMatcher.put(String.valueOf(R.id.call_worker_tip1), EVENT_URGENT_0_1);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip2), EVENT_URGENT_0_2);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip3), EVENT_URGENT_0_3);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip4), EVENT_MESSAGE_1_1);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip5), EVENT_MESSAGE_1_2);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip6), EVENT_MESSAGE_1_3);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip7), EVENT_MESSAGE_2_1);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip8), EVENT_MESSAGE_2_2);
        sEventMatcher.put(String.valueOf(R.id.call_worker_tip9), EVENT_MESSAGE_2_3);

        sEventMatcher.put(String.valueOf(R.id.foot_action_bar_content_moreoverflow), EVENT_SETTINGS);
        sEventMatcher.put(String.valueOf(R.id.foot_action_bar_grid_mode_manage), EVENT_IMAGE_MANAGEMENT);
        //图片选择分为单选和全选，事件ID一致，传递的acc值不同
        sEventMatcher.put(String.valueOf(R.id.foot_action_bar_selection_all),EVENT_IMAGE_SELECTED);
        sEventMatcher.put(String.valueOf(R.id.media_view_grid_item_choice),EVENT_IMAGE_SELECTED);
        sEventMatcher.put(String.valueOf(R.id.btn1),EVENT_IMAGE_DELETED);
        sEventMatcher.put(String.valueOf(R.id.foot_action_bar_selection_mode_back),EVENT_QUIT_IMAGE_MANAGEMENT);

        sEventMatcher.put(String.valueOf(R.id.settings_info),EVENT_PHOTO_FRAME_INFO);
        sEventMatcher.put(String.valueOf(R.id.settings_wifi),EVENT_WIFI);
        sEventMatcher.put(String.valueOf(R.id.settings_ble),EVENT_DEVICE_BINDED);
        sEventMatcher.put(String.valueOf(R.id.settings_sound_display),EVENT_SOUND_DISPLAY);
        sEventMatcher.put(String.valueOf(R.id.settings_advanced),EVENT_ADVANCED_SETTING);
        sEventMatcher.put(String.valueOf(R.id.settings_about),EVENT_ABOUT);

        //画面ID映射
        //照片全屏显示画面
        sPageMatcher.put(EVENT_CLICK_IMAGE, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_HEAD_PORTRAIT, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_LIKED, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_COMMENT, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_HEALTH_DATA, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_THUMBNAIL_IMAGE, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_SOCIAL_WORKER, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_ENVIRONMENT, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_IMAGE_MOVE_RIGHT, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_IMAGE_MOVE_LEFT, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_IMAGE_MOVE_UP, PAGE_FULL_IMAGE);
        sPageMatcher.put(EVENT_IMAGE_MOVE_DOWN, PAGE_FULL_IMAGE);

        //健康数据画面
        sPageMatcher.put(EVENT_ALL_USER, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_SPO2H, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_BLOOD_PRESSURE, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_WEIGHT, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_BLOOD_GLUCOSE, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_ENVIRONMENT_CARD, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_WATER_CARD, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_EAR_THERMOMETER, PAGE_HEALTH_DATA);
        sPageMatcher.put(EVENT_PUSH_MESSAGE_CARD, PAGE_HEALTH_DATA);

        //评论弹出框
        sPageMatcher.put(EVENT_COMMENT_1_1, PAGE_QUICK_COMMENT);
        sPageMatcher.put(EVENT_COMMENT_1_2, PAGE_QUICK_COMMENT);
        sPageMatcher.put(EVENT_COMMENT_2_1, PAGE_QUICK_COMMENT);
        sPageMatcher.put(EVENT_COMMENT_2_2, PAGE_QUICK_COMMENT);
        sPageMatcher.put(EVENT_COMMENT_3_1, PAGE_QUICK_COMMENT);
        sPageMatcher.put(EVENT_COMMENT_3_2, PAGE_QUICK_COMMENT);

        //回复弹出框
        sPageMatcher.put(EVENT_REPLY_1_1, PAGE_QUICK_REPLY);
        sPageMatcher.put(EVENT_REPLY_1_2, PAGE_QUICK_REPLY);
        sPageMatcher.put(EVENT_REPLY_2_1, PAGE_QUICK_REPLY);
        sPageMatcher.put(EVENT_REPLY_2_2, PAGE_QUICK_REPLY);
        sPageMatcher.put(EVENT_REPLY_3_1, PAGE_QUICK_REPLY);
        sPageMatcher.put(EVENT_REPLY_3_2, PAGE_QUICK_REPLY);

        //呼叫社工弹出框
        sPageMatcher.put(EVENT_URGENT_0_1, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_URGENT_0_2, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_URGENT_0_3, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_MESSAGE_1_1, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_MESSAGE_1_2, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_MESSAGE_1_3, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_MESSAGE_2_1, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_MESSAGE_2_2, PAGE_SOCIAL_WORKER);
        sPageMatcher.put(EVENT_MESSAGE_2_3, PAGE_SOCIAL_WORKER);

        //照片缩略图画面
        sPageMatcher.put(EVENT_SETTINGS, PAGE_THUMBNAIL_IMAGE);
        sPageMatcher.put(EVENT_IMAGE_MANAGEMENT, PAGE_THUMBNAIL_IMAGE);
        //图片选择分为单选和全选，事件ID一致，传递的acc值不同
        sPageMatcher.put(EVENT_IMAGE_SELECTED, PAGE_IMAGE_MANAGEMENT);
        sPageMatcher.put(EVENT_IMAGE_SELECTED, PAGE_IMAGE_MANAGEMENT);
        sPageMatcher.put(EVENT_IMAGE_DELETED, PAGE_IMAGE_MANAGEMENT);
        sPageMatcher.put(EVENT_QUIT_IMAGE_MANAGEMENT, PAGE_IMAGE_MANAGEMENT);

        //设置画面
        sPageMatcher.put(EVENT_PHOTO_FRAME_INFO, PAGE_SETTINGS);
        sPageMatcher.put(EVENT_WIFI, PAGE_SETTINGS);
        sPageMatcher.put(EVENT_DEVICE_BINDED, PAGE_SETTINGS);
        sPageMatcher.put(EVENT_SOUND_DISPLAY, PAGE_SETTINGS);
        sPageMatcher.put(EVENT_ADVANCED_SETTING, PAGE_SETTINGS);
        sPageMatcher.put(EVENT_ABOUT, PAGE_SETTINGS);
    }

    /**
     * 返回事件ID
     *
     * @param resId 资源ID
     * @return 事件ID
     */
    public static String getEventId(int resId) {
        String eventId = sEventMatcher.get(String.valueOf(resId));
        if (eventId != null) {
            return eventId;
        }
        return "";
    }

    /**
     * 返回事件ID
     *
     * @param key 键值
     * @return 事件ID
     */
    public static String getEventId(String key) {
        if (key == null) {
            return "";
        }
        String eventId = sEventMatcher.get(key);
        if (eventId != null) {
            return eventId;
        }
        return "";
    }

    /**
     * 获取Page ID
     *
     * @param eventId 事件ID
     * @return Page ID
     */
    public static String getPageId(String eventId) {
        if (eventId == null) {
            return "";
        }
        String pageId = sPageMatcher.get(eventId);
        if (pageId != null) {
            return pageId;
        }
        return "";
    }

    /**
     * 产生事件的控件类型，默认为button
     *
     * @param eventId 事件ID
     * @return Page ID
     */
    public static String getTargetViewType(String eventId) {
        if (eventId == null) {
            return TYPE_BUTTON;
        }

        switch (eventId) {
            case EVENT_IMAGE_MOVE_UP:
            case EVENT_IMAGE_MOVE_DOWN:
            case EVENT_IMAGE_MOVE_LEFT:
            case EVENT_IMAGE_MOVE_RIGHT:
                return TYPE_IMAGE;
            default:
                return TYPE_BUTTON;

        }
    }

    /**
     * 控件操作类型,默认为click
     *
     * @param eventId 事件ID
     * @return Page ID
     */
    public static String getOperation(String eventId) {
        String result = OPERATION_CLICK;

        if (eventId == null) {
            return result;
        }

        switch (eventId) {
            case EVENT_IMAGE_MOVE_UP:
                result = OPERATION_TOUCH_UP;
                break;
            case EVENT_IMAGE_MOVE_DOWN:
                result = OPERATION_TOUCH_DOWN;
                break;
            case EVENT_IMAGE_MOVE_LEFT:
                result = OPERATION_TOUCH_LEFT;
                break;
            case EVENT_IMAGE_MOVE_RIGHT:
                result = OPERATION_TOUCH_RIGHT;
                break;
            default:
                break;

        }
        return result;
    }
}
