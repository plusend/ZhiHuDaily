package com.plusend.zhihudaily.mvp.presenter;

import com.plusend.zhihudaily.common.RxBus;
import com.plusend.zhihudaily.model.NewsData;
import com.plusend.zhihudaily.model.bean.DetailNews;
import com.plusend.zhihudaily.model.rest.RestNewsData;
import com.plusend.zhihudaily.mvp.view.DetailNewsView;

import rx.functions.Action1;

/**
 * Created by plusend on 16/6/3.
 */
public class DetailNewsPresenter extends Presenter {
    private NewsData mNewsData;
    private DetailNewsView mDetailNewsView;
    private int mId;

    public DetailNewsPresenter(DetailNewsView detailNewsView, int id) {
        mDetailNewsView = detailNewsView;
        mNewsData = new RestNewsData();
        mId = id;
    }

    @Override
    public void start() {
        mNewsData.getDetailNews(mId);
        RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof DetailNews) {
                    mDetailNewsView.showDetail((DetailNews) o);
                }
            }
        });
    }

    @Override
    public void stop() {

    }
}
