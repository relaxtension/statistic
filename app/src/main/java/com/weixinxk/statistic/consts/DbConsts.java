/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.consts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***********************************************************************
 *
 * DB相关常量
 *
 * @类名 DbConsts
 * @包名 com.example.lisichen.statistic_db.db
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/8
 *
 ***********************************************************************/

public class DbConsts {
    /**
     * 统计表表名.
     */
    public static final String STATISTIC_DATA_TABLE_NAME = "statistic_data";

    /**
     * 数据库名
     */
    public static final String DATA_BASE_NAME = "data.db";
    /**
     * 创建统计表.
     */
    private static final String CREATE_TABLE_STATISTIC_DATA = "CREATE TABLE " + DbConsts.STATISTIC_DATA_TABLE_NAME + " (" +
            "STATISTIC_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "EVENT_ID VARCHAR(32) NOT NULL, " +
            "EVENT_TYPE INTEGER NULL, " +
            "MESSAGE_ID VARCHAR(20) NULL, " +
            "CONTENT VARCHAR(20) NULL, " +
            "STATISTIC_LEVEL INTEGER DEFAULT 1, " +
            "MEASURE_TIME INTEGER NULL)";

    /**
     * Sql语句列表.
     */
    public static final List<String> SQL_LIST;

    static {
        List<String> sqls = new ArrayList<String>();
        sqls.add(CREATE_TABLE_STATISTIC_DATA);
        SQL_LIST = Collections.unmodifiableList(sqls);
    }

    /**
     * 统计数据主键
     */
    public static final String STATISTIC_DATA_STATISTIC_ID = "STATISTIC_ID";
    /**
     * 事件ID
     */
    public static final String STATISTIC_DATA_EVENT_ID = "EVENT_ID";

    /**
     * 时间类型
     */
    public static final String STATISTIC_DATA_EVENT_TYPE = "EVENT_TYPE";

    /**
     * 消息ID
     */
    public static final String STATISTIC_DATA_MESSAGE_ID = "MESSAGE_ID";

    /**
     * 消息内容
     */
    public static final String STATISTIC_DATA_CONTENT = "CONTENT";

    /**
     * 统计等级
     */
    public static final String STATISTIC_DATA_STATISTIC_LEVEL = "STATISTIC_LEVEL";
    /**
     * 统计等级
     */
    public static final String STATISTIC_DATA_STATISTIC_TIME = "MEASURE_TIME";

}
