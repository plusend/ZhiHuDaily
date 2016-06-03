package com.plusend.zhihudaily.mvp.view;

import com.plusend.zhihudaily.model.bean.LatestNews;

/**
 * Created by plusend on 16/5/29.
 */
public interface LatestNewsView extends NewsView{

    void showNews(LatestNews news);

}
