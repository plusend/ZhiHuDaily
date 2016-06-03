package com.plusend.zhihudaily.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by plusend on 16/5/30.
 */
public class Themes {

    @SerializedName("limit")
    private int limit;
    @SerializedName("subscribed")
    private List<?> subscribed;
    @SerializedName("others")
    private List<Others> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<?> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<?> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Others> getOthers() {
        return others;
    }

    public void setOthers(List<Others> others) {
        this.others = others;
    }

    public static class Others {
        @SerializedName("color")
        private int color;
        @SerializedName("thumbnail")
        private String thumbnail;
        @SerializedName("description")
        private String description;
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
