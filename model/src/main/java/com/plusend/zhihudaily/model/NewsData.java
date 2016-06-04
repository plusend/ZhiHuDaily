package com.plusend.zhihudaily.model;

/**
 * Created by plusend on 16/5/29.
 */
public interface NewsData {

    void getLatestNews();

    void getStartImage();

    void getDetailNews(int id);

    /**
     * 若果需要查询 11 月 18 日的消息，before 后的数字应为 20131119
     * 知乎日报的生日为 2013 年 5 月 19 日，若 before 后数字小于 20130520 ，只会接收到空消息
     * 输入的今日之后的日期仍然获得今日内容，但是格式不同于最新消息的 JSON 格式
     */
    void getBeforeNews(String date);

    void getStoryExtra(String id);

    void getLongComments(String id);

    void getShortComments(String id);

    void getThemes();

    void getDetailTheme(String id);
}
