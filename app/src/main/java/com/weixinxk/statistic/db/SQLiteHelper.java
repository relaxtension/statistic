/***********************************************************************
 *
 * Company: 广州蛙鸣智能科技有限公司.
 * Copyright: Copyright (c) 2016
 * **********************************************************************/
package com.weixinxk.statistic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.weixinxk.statistic.consts.DbConsts;


/***********************************************************************
 *
 * SQL helper
 *
 * @类名 SQLiteHelper
 * @包名 com.weixinxk.statistic.db
 * @类指责描述
 *
 * @作者 lisichen
 * @创建日期 17/7/11
 *
 ***********************************************************************/

public class SQLiteHelper extends SQLiteOpenHelper {

    private static int databaseVersion;
    private static SQLiteHelper sqliteHelper;

    private SQLiteHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public static synchronized SQLiteHelper getInstance(Context context) {
        if (sqliteHelper == null) {
            databaseVersion = DbConsts.SQL_LIST.size();
            sqliteHelper = new SQLiteHelper(context, DbConsts.DATA_BASE_NAME, databaseVersion);

        }
        return sqliteHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        executeAllSql(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        executeAllSql(db);
    }

    /**
     * 进行DB初始化
     * @param sqLiteDatabase DB数据库
     */
    private void executeAllSql(SQLiteDatabase sqLiteDatabase) {
        for (String sql : DbConsts.SQL_LIST) {
            try {
                if(sql.length() > 0) {
                    sqLiteDatabase.execSQL(sql);
                }
            } catch (Exception ignore) {

            }
        }
    }
}
