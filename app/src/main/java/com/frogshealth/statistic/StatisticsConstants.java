package com.frogshealth.statistic;

import com.frogshealth.photoframe.service.wechat.WxOpenApi;

/**********************************************************************
 * 统计常量定义类
 * @author sundi
 * @类名 StatisticsConstants
 * @包名 com.frogshealth.photoframe.common.statistics
 * @创建日期 16/12/1
 ***********************************************************************/
public class StatisticsConstants {

    /**
     * 服务接口基地址
     */
    public static final String BASE_URL = WxOpenApi.HOST_ROOT + "/api/page/element";

    /**
     * 事件接口
     */
    public static final String EVENT_URL = BASE_URL + "/batchAdd.json";

    /**
     * 分隔符
     */
    public static final String FILE_SEP = "@=@";


    /**
     * 缓存文件目录
     */
    public static final String FILE_FOLDER= "/statistic/";

    /**
     * 缓存文件前缀
     */
    public static final String FILE_NAME_PREFIX = "cache_";

    /**
     * 缓存文件类型
     */
    public static final String TYPE_EVENT = "type_event";

    /**
     * 默认缓存文件大小
     */
    public static final long DEFAULT_FILE_SIZE = 512 * 1024;//512k

    /**
     * 默认缓存文件大小
     */
    public static final long DEFAULT_INTERVAL_TIME = 60 * 1000;//1分钟

    /**
     * 读取超时时长
     */
    public static final int READ_TIME_OUT = 30000;

    /**
     * 连接超时时长
     */
    public static final int CONN_TIME_OUT = 30000;

    /**
     * 时间戳
     */
    public static final String KEY_TIME = "time";
    /**
     * 设备ID
     */
    public static final String KEY_DEVICE_ID = "deviceSn";
    /**
     * 页面ID
     */
    public static final String KEY_PAGE_ID = "pageId";
    /**
     * 事件ID
     */
    public static final String KEY_EVENT_ID = "eventId";
    /**
     * 计数
     */
    public static final String KEY_ACC = "acc";
    /**
     * 版本
     */
    public static final String KEY_VERSION = "version";
    /**
     * 应用ID
     */
    public static final String KEY_APP_ID = "appId";

    /**
     * 应用ID值
     */
    public static final String VALUE_APP_ID = "android";

    /**
     * post request中 Access Token对应的Key.
     */
    public static final String KEY_ACCESS_TOKEN = "access_token";
    /**
     * post request中 统计对应的Key.
     */
    public static final String KEY_STATISTICS_LOG = "statistics_log";

    /**
     * post request中 统计对应的Key.
     */
    public static final String KEY_LOG_INFO = "logInfo";

    /**
     * 产生事件View的类型对应的KEY
     */
    public static final String KEY_VIEW_TYPE = "elementName";

    /**
     * 事件类型对应的KEY
     */
    public static final String KEY_OPERATION_TYPE = "eventName";

    /**
     * 字串分隔符
     */
    public static final String STRING_SEP = "-";
}
