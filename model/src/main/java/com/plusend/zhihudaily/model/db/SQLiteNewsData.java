package com.plusend.zhihudaily.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.plusend.zhihudaily.common.Logger;
import com.plusend.zhihudaily.common.RxBus;
import com.plusend.zhihudaily.model.NewsData;
import com.plusend.zhihudaily.model.bean.BeforeNews;
import com.plusend.zhihudaily.model.bean.DetailNews;
import com.plusend.zhihudaily.model.bean.LatestNews;

/**
 * Created by plusend on 16/7/5.
 */
public class SQLiteNewsData implements NewsData {

    private static final String TAG = SQLiteNewsData.class.getSimpleName();
    private Context mContext;

    public SQLiteNewsData(Context context) {
        mContext = context;
    }

    @Override
    public void getLatestNews() {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String[] projection = {
                NewsContract.LatestNews.COLUMN_NAME_JSON
        };

        Cursor cursor = db.query(
                NewsContract.LatestNews.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        String json = null;
        if (cursor.moveToFirst()) {
            json = cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.LatestNews.COLUMN_NAME_JSON));
        }
        cursor.close();
        LatestNews latestNews = new Gson().fromJson(json, LatestNews.class);
        RxBus.getInstance().send(latestNews);
    }

    @Override
    public void getStartImage() {

    }

    @Override
    public void getDetailNews(int id) {
        Logger.d(TAG, "getDetailNews id: " + id);
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String[] projection = {
                NewsContract.DetailNews.COLUMN_NAME_ID,
                NewsContract.DetailNews.COLUMN_NAME_JSON
        };

        Cursor cursor = db.query(
                NewsContract.DetailNews.TABLE_NAME,
                projection,
                NewsContract.DetailNews.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        String json = null;
        if (cursor.moveToFirst()) {
            json = cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.DetailNews.COLUMN_NAME_JSON));
            Logger.json(json);
        }else {
            Logger.d(TAG, "getDetailNews: id = " + id + " result = null");
        }
        cursor.close();
        DetailNews detailNews = new Gson().fromJson(json, DetailNews.class);
        RxBus.getInstance().send(detailNews);
    }

    @Override
    public void getBeforeNews(String date) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String[] projection = {
                NewsContract.BeforeNews.COLUMN_NAME_DATE,
                NewsContract.BeforeNews.COLUMN_NAME_JSON
        };

        Cursor cursor = db.query(
                NewsContract.BeforeNews.TABLE_NAME,
                projection,
                NewsContract.BeforeNews.COLUMN_NAME_DATE + " = ?",
                new String[]{date},
                null,
                null,
                null);
        String json = null;
        if (cursor.moveToFirst()) {
            json = cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.BeforeNews.COLUMN_NAME_JSON));
        }
        cursor.close();
        BeforeNews beforeNews = new Gson().fromJson(json, BeforeNews.class);
        RxBus.getInstance().send(beforeNews);
    }

    @Override
    public void getStoryExtra(String id) {

    }

    @Override
    public void getLongComments(String id) {

    }

    @Override
    public void getShortComments(String id) {

    }

    @Override
    public void getThemes() {

    }

    @Override
    public void getDetailTheme(String id) {

    }
}
