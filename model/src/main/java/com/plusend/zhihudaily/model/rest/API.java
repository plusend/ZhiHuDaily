package com.plusend.zhihudaily.model.rest;

import com.plusend.zhihudaily.model.bean.BeforeNews;
import com.plusend.zhihudaily.model.bean.DetailNews;
import com.plusend.zhihudaily.model.bean.DetailTheme;
import com.plusend.zhihudaily.model.bean.LatestNews;
import com.plusend.zhihudaily.model.bean.LongComments;
import com.plusend.zhihudaily.model.bean.ShortComments;
import com.plusend.zhihudaily.model.bean.StartImage;
import com.plusend.zhihudaily.model.bean.StoryExtra;
import com.plusend.zhihudaily.model.bean.Themes;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by plusend on 16/5/29.
 */
public interface API {

    @GET("news/latest")
    Observable<LatestNews> getLatestNews();

    @GET("start-image/1080*1776")
    Observable<StartImage> getStartImage();

    @GET("news/{id}")
    Observable<DetailNews> getDetailNews(@Path("id") String id);

    @GET("news/before/{date}")
    Observable<BeforeNews> getBeforeNews(@Path("date") String date);

    @GET("story-extra/{id}")
    Observable<StoryExtra> getStoryExtra(@Path("id") String id);

    @GET("story/{id}/long-comments")
    Observable<LongComments> getLongComments(@Path("id") String id);

    @GET("story/{id}/short-comments")
    Observable<ShortComments> getShortComments(@Path("id") String id);

    @GET("themes")
    Observable<Themes> getThemes();

    @GET("theme/{id}")
    Observable<DetailTheme> getDetailTheme(@Path("id") String id);

}
