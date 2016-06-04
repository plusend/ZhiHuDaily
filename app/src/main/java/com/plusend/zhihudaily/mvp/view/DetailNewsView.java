package com.plusend.zhihudaily.mvp.view;

import com.plusend.zhihudaily.model.bean.DetailNews;

/**
 * Created by plusend on 16/6/3.
 */
public interface DetailNewsView extends NewsView{
    void showDetail(DetailNews detailNews);
}
