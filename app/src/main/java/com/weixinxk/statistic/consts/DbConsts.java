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
     * 数据库名
     */
    public static final String DATA_BASE_NAME = "data.db";

    /**
     * 统计表表名.
     */
    public static final String STATISTIC_TABLE_NAME = "statistic_data";

    /**
     * 统计数据主键
     */
    public static final String STATISTIC_ID = "ID";
    /**
     * 事件ID
     */
    public static final String STATISTIC_EVENT_ID = "EVENT_ID";

    /**
     * 时间类型
     */
    public static final String STATISTIC_EVENT_TYPE = "EVENT_TYPE";

    /**
     * 消息ID
     */
    public static final String STATISTIC_MESSAGE_ID = "MESSAGE_ID";

    /**
     * 消息内容
     */
    public static final String STATISTIC_CONTENT = "CONTENT";

    /**
     * 统计等级
     */
    public static final String STATISTIC_PRIORITY = "PRIORITY";
    /**
     * 统计等级
     */
    public static final String STATISTIC_TRIGGER_TIME = "TRIGGER_TIME";

    /**
     * 创建统计表.
     */
    private static final String CREATE_TABLE_STATISTIC_DATA = "CREATE TABLE " + STATISTIC_TABLE_NAME + " ("
            + STATISTIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STATISTIC_EVENT_ID + " VARCHAR(32) NOT NULL, "
            + STATISTIC_EVENT_TYPE + " INTEGER NULL, "
            + STATISTIC_MESSAGE_ID + " VARCHAR(20) NULL, "
            + STATISTIC_CONTENT + " VARCHAR(20) NULL, "
            + STATISTIC_PRIORITY + " INTEGER DEFAULT 0, "
            + STATISTIC_TRIGGER_TIME + " INTEGER NULL)";

    /**
     * Sql语句列表.
     */
    public static final List<String> SQL_LIST;

    static {
        List<String> sqls = new ArrayList<String>();
        sqls.add(CREATE_TABLE_STATISTIC_DATA);
        SQL_LIST = Collections.unmodifiableList(sqls);
    }

}
