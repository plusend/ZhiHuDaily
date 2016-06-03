package com.plusend.zhihudaily.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by plusend on 16/5/30.
 */
public class StoryExtra {

    @SerializedName("long_comments")
    private int longComments;
    @SerializedName("popularity")
    private int popularity;
    @SerializedName("short_comments")
    private int shortComments;
    @SerializedName("comments")
    private int comments;

    public int getLongComments() {
        return longComments;
    }

    public void setLongComments(int longComments) {
        this.longComments = longComments;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getShortComments() {
        return shortComments;
    }

    public void setShortComments(int shortComments) {
        this.shortComments = shortComments;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
