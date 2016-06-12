package com.plusend.zhihudaily.model.bean;

import java.util.List;

/**
 * Created by plusend on 16/5/29.
 */
public class LatestNews {

    @com.google.gson.annotations.SerializedName("date")
    private String date;
    @com.google.gson.annotations.SerializedName("stories")
    private List<Story> stories;
    @com.google.gson.annotations.SerializedName("top_stories")
    private List<TopStories> topStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<TopStories> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStories> topStories) {
        this.topStories = topStories;
    }
}
