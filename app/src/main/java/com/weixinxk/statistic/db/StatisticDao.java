/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.weixinxk.statistic.consts.DbConsts;
import com.weixinxk.statistic.model.BaseData;

import java.util.ArrayList;
import java.util.List;

/***********************************************************************
 *
 * 数据库操作
 *
 * @类名 StatisticDao
 * @包名 com.weixinxk.statistic.db
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/11
 *
 ***********************************************************************/

public class StatisticDao {
    /**
     *
     */
    private SQLiteHelper sqliteHelper;

    public StatisticDao(Context context) {
        this.sqliteHelper = SQLiteHelper.getInstance(context);
    }

    /**
     * 插入
     * @param baseData
     */
    public void insert(BaseData baseData) {
        SQLiteDatabase database = sqliteHelper.getWritableDatabase();
        String sql = "INSERT INTO " + DbConsts.STATISTIC_DATA_TABLE_NAME + " (EVENT_ID, EVENT_TYPE, MESSAGE_ID, CONTENT, PRIORITY, TRIGGER_TIME) VALUES (?, ?, ?, ?, ?, ?)";
        database.beginTransaction();
        try {
            List<Object> values = new ArrayList<>();
            values.add(baseData.getEventId());
            values.add(baseData.getEventType());
            values.add(baseData.getMessageId());
            values.add(baseData.getContent());
            values.add(baseData.getPriority());
            values.add(baseData.getTriggerTime());
            database.execSQL(sql, values.toArray());
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    /**
     * 查询
     * @param time 查询时间
     * @return 查询结果
     */
    public Cursor query(long time) {
        SQLiteDatabase database = sqliteHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DbConsts.STATISTIC_DATA_TABLE_NAME + " where "+ DbConsts.STATISTIC_DATA_STATISTIC_TIME + " < " + '\"' + time + '\"';
        Cursor result = database.rawQuery(sql, new String[] {});
        return result;
    }

    /**
     * 删除
     * @param id 删除的数据
     */
    public void delete(int id) {
        SQLiteDatabase database = sqliteHelper.getWritableDatabase();
        String sql = "DELETE FROM " + DbConsts.STATISTIC_DATA_TABLE_NAME + " WHERE " + DbConsts.STATISTIC_DATA_STATISTIC_ID + " = ?";
        database.execSQL(sql, new String[] {id + ""});
    }
}
