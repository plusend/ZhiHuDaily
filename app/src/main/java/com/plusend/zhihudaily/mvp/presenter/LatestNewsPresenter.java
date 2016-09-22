package com.plusend.zhihudaily.mvp.presenter;

import android.content.Context;

import com.plusend.zhihudaily.common.NetworkUtil;
import com.plusend.zhihudaily.common.RxBus;
import com.plusend.zhihudaily.model.NewsData;
import com.plusend.zhihudaily.model.bean.LatestNews;
import com.plusend.zhihudaily.model.db.SQLiteNewsData;
import com.plusend.zhihudaily.model.rest.RestNewsData;
import com.plusend.zhihudaily.mvp.view.LatestNewsView;

import rx.functions.Action1;

/**
 * Created by plusend on 16/5/29.
 */
public class LatestNewsPresenter extends Presenter {

    private NewsData mNewsData;
    private LatestNewsView mLatestNewsView;

    public LatestNewsPresenter(LatestNewsView latestNewsView) {
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
                if (o instanceof LatestNews) {
                    mLatestNewsView.showNews((LatestNews) o);
                }
            }
        });
    }

    @Override
    public void start() {
        mNewsData.getLatestNews();

    }

    @Override
    public void stop() {

    }
}
