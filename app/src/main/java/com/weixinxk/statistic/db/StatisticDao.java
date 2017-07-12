/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lisichen.statistic_db.consts.DbConsts;
import com.example.lisichen.statistic_db.mode.BaseBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***********************************************************************
 *
 * 数据库操作
 *
 * @类名 StatisticDao
 * @包名 com.example.lisichen.statistic_db.db
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
     * @param statisticInfo
     */
    public void insert(BaseBean statisticInfo) {
        SQLiteDatabase database = sqliteHelper.getWritableDatabase();
        String sql = "INSERT INTO " + DbConsts.STATISTIC_DATA_TABLE_NAME + " (EVENT_ID, EVENT_TYPE, MESSAGE_ID, CONTENT, STATISTIC_LEVEL, MEASURE_TIME) VALUES (?, ?, ?, ?, ?, ?)";
        database.beginTransaction();
        try {
            List<Object> values = new ArrayList<>();
            values.add(statisticInfo.getEventId());
            values.add(statisticInfo.getEventType());
            values.add(statisticInfo.getMessageId());
            values.add(statisticInfo.getContent());
            values.add(statisticInfo.getStatisticLevel());
            values.add(statisticInfo.getMeasureTime());
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
