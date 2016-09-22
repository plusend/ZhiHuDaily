package com.plusend.zhihudaily.model.rest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.plusend.zhihudaily.common.Constants;
import com.plusend.zhihudaily.common.RxBus;
import com.plusend.zhihudaily.model.NewsData;
import com.plusend.zhihudaily.model.bean.BeforeNews;
import com.plusend.zhihudaily.model.bean.DetailNews;
import com.plusend.zhihudaily.model.bean.DetailTheme;
import com.plusend.zhihudaily.model.bean.LatestNews;
import com.plusend.zhihudaily.model.bean.LongComments;
import com.plusend.zhihudaily.model.bean.ShortComments;
import com.plusend.zhihudaily.model.bean.StartImage;
import com.plusend.zhihudaily.model.bean.StoryExtra;
import com.plusend.zhihudaily.model.bean.Themes;
import com.plusend.zhihudaily.model.db.DatabaseHelper;
import com.plusend.zhihudaily.model.db.NewsContract;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by plusend on 16/5/29.
 */
public class RestNewsData implements NewsData {

    private static final String TAG = "RestNewsData";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    private API mAPI = retrofit.create(API.class);

    private Context mContext;

    public RestNewsData(Context context) {
        mContext = context;
    }

    @Override
    public void getLatestNews() {
        mAPI.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestNews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LatestNews latestNews) {
                        latestNews.getStories().get(0).setType(
                                Integer.valueOf(latestNews.getDate()));
                        RxBus.getInstance().send(latestNews);

                        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
                        SQLiteDatabase db = databaseHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put(NewsContract.LatestNews.COLUMN_NAME_JSON, new Gson().toJson(latestNews));
                        long newRowId = db.insert(NewsContract.LatestNews.TABLE_NAME, null, values);
                    }
                });
    }

    @Override
    public void getStartImage() {
        mAPI.getStartImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StartImage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StartImage startImage) {
                        RxBus.getInstance().send(startImage);
                    }
                });
    }

    @Override
    public void getDetailNews(final int id) {
        mAPI.getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailNews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DetailNews detailNews) {
                        RxBus.getInstance().send(detailNews);

                        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
                        SQLiteDatabase db = databaseHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put(NewsContract.DetailNews.COLUMN_NAME_ID, id);
                        values.put(NewsContract.DetailNews.COLUMN_NAME_JSON, new Gson().toJson(detailNews));
                        long newRowId = db.insert(NewsContract.DetailNews.TABLE_NAME, null, values);
                    }
                });
    }

    @Override
    public void getBeforeNews(final String date) {
        mAPI.getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeforeNews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BeforeNews beforeNews) {
                        beforeNews.getStories().get(0).setType(
                                Integer.valueOf(beforeNews.getDate()));
                        RxBus.getInstance().send(beforeNews);

                        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
                        SQLiteDatabase db = databaseHelper.getWritableDatabase();

                        ContentValues values = new ContentValues();
                        values.put(NewsContract.BeforeNews.COLUMN_NAME_DATE, date);
                        values.put(NewsContract.BeforeNews.COLUMN_NAME_JSON, new Gson().toJson(beforeNews));
                        long newRowId = db.insert(NewsContract.BeforeNews.TABLE_NAME, null, values);
                    }
                });
    }

    @Override
    public void getStoryExtra(String id) {
        mAPI.getStoryExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryExtra>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StoryExtra storyExtra) {
                        RxBus.getInstance().send(storyExtra);
                    }
                });
    }

    @Override
    public void getLongComments(String id) {
        mAPI.getLongComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LongComments>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LongComments longComments) {
                        RxBus.getInstance().send(longComments);
                    }
                });
    }

    @Override
    public void getShortComments(String id) {
        mAPI.getShortComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShortComments>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShortComments shortComments) {
                        RxBus.getInstance().send(shortComments);
                    }
                });
    }

    @Override
    public void getThemes() {
        mAPI.getThemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Themes>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Themes themes) {
                        RxBus.getInstance().send(themes);
                    }
                });
    }

    @Override
    public void getDetailTheme(String id) {
        mAPI.getDetailTheme(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailTheme>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailTheme detailTheme) {
                        RxBus.getInstance().send(detailTheme);
                    }
                });
    }
}
