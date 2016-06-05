package com.plusend.zhihudaily.model.rest;

import android.util.Log;

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
                        latestNews.getStories().get(0).setType(Integer.valueOf(latestNews.getDate()));
                        RxBus.getInstance().send(latestNews);
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
    public void getDetailNews(int id) {
        mAPI.getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailNews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailNews detailNews) {
                        RxBus.getInstance().send(detailNews);
                    }
                });
    }

    @Override
    public void getBeforeNews(String date) {
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
                        Log.d(TAG, "getBeforeNews: " + beforeNews.getDate());
                        beforeNews.getStories().get(0).setType(Integer.valueOf(beforeNews.getDate()));
                        RxBus.getInstance().send(beforeNews);
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
