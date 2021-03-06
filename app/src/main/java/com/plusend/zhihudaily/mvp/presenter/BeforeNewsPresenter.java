package com.plusend.zhihudaily.mvp.presenter;

import android.content.Context;

import com.plusend.zhihudaily.common.NetworkUtil;
import com.plusend.zhihudaily.common.RxBus;
import com.plusend.zhihudaily.model.NewsData;
import com.plusend.zhihudaily.model.bean.BeforeNews;
import com.plusend.zhihudaily.model.db.SQLiteNewsData;
import com.plusend.zhihudaily.model.rest.RestNewsData;
import com.plusend.zhihudaily.mvp.view.LatestNewsView;

import rx.functions.Action1;

/**
 * Created by plusend on 16/6/5.
 */
public class BeforeNewsPresenter extends Presenter {
    private NewsData mNewsData;
    private LatestNewsView mLatestNewsView;
    private String mDate;

    public BeforeNewsPresenter(LatestNewsView latestNewsView) {
        mLatestNewsView = latestNewsView;
        Context context = mLatestNewsView.getContext();
        if (NetworkUtil.isOnline(context)) {
            mNewsData = new RestNewsData(context);
        } else {
            mNewsData = new SQLiteNewsData(context);
        }
        RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof BeforeNews) {
                    mLatestNewsView.addBefore((BeforeNews) o);
                }
            }
        });
    }

    public void getBeforeNews(String date) {
        mDate = date;
        start();
    }

    @Override
    public void start() {
        mNewsData.getBeforeNews(mDate);

    }

    @Override
    public void stop() {

    }
}
