package com.plusend.zhihudaily.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by plusend on 16/5/30.
 */
public class BeforeNews {

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<Stories> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public static class Stories {
        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String gaPrefix;
        @SerializedName("title")
        private String title;
        @SerializedName("images")
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
