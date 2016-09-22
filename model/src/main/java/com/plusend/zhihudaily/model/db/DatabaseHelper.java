package com.plusend.zhihudaily.model.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.plusend.zhihudaily.common.Logger;

/**
 * Created by plusend on 16/6/23.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DB_NAME = "news.db";
    private static final int DB_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int version = oldVersion + 1; version <= newVersion; version++) {
            upgradeTo(db, version);
        }
    }

    private void upgradeTo(SQLiteDatabase db, int version) {
        switch (version) {
            case 1:
                createLatestNewsTable(db);
                break;
            default:
                throw new IllegalStateException("Don't know how to upgrade to " + version);
        }
    }

    private void createLatestNewsTable(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("DROP TABLE IF EXISTS " + NewsContract.LatestNews.TABLE_NAME);
            db.execSQL("CREATE TABLE " + NewsContract.LatestNews.TABLE_NAME + " (" +
                    NewsContract.LatestNews.COLUMN_NAME_JSON + TEXT_TYPE +
                    " )");
            db.execSQL("DROP TABLE IF EXISTS " + NewsContract.BeforeNews.TABLE_NAME);
            db.execSQL("CREATE TABLE " + NewsContract.BeforeNews.TABLE_NAME + " (" +
                    NewsContract.BeforeNews.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    NewsContract.BeforeNews.COLUMN_NAME_JSON + TEXT_TYPE +
                    " )");
            db.execSQL("DROP TABLE IF EXISTS " + NewsContract.DetailNews.TABLE_NAME);
            db.execSQL("CREATE TABLE " + NewsContract.DetailNews.TABLE_NAME + " (" +
                    NewsContract.DetailNews.COLUMN_NAME_ID + " INTEGER" + COMMA_SEP +
                    NewsContract.DetailNews.COLUMN_NAME_JSON + TEXT_TYPE +
                    " )");
            db.setTransactionSuccessful();
        } catch (SQLException ex) {
            Logger.e(TAG, "couldn't create table in news database");
            throw ex;
        } finally {
            db.endTransaction();
        }
    }
}
