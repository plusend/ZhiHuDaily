package com.plusend.zhihudaily.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by plusend on 16/5/29.
 */
public class LatestNews {

    @com.google.gson.annotations.SerializedName("date")
    private String date;
    @com.google.gson.annotations.SerializedName("stories")
    private List<Stories> stories;
    @com.google.gson.annotations.SerializedName("top_stories")
    private List<TopStories> topStories;

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

    public List<TopStories> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStories> topStories) {
        this.topStories = topStories;
    }

    public static class Stories {
        @com.google.gson.annotations.SerializedName("type")
        private int type;
        @com.google.gson.annotations.SerializedName("id")
        private int id;
        @com.google.gson.annotations.SerializedName("ga_prefix")
        private String gaPrefix;
        @com.google.gson.annotations.SerializedName("title")
        private String title;
        @com.google.gson.annotations.SerializedName("images")
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

    public static class TopStories implements Parcelable {
        @com.google.gson.annotations.SerializedName("image")
        private String image;
        @com.google.gson.annotations.SerializedName("type")
        private int type;
        @com.google.gson.annotations.SerializedName("id")
        private int id;
        @com.google.gson.annotations.SerializedName("ga_prefix")
        private String gaPrefix;
        @com.google.gson.annotations.SerializedName("title")
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.image);
            dest.writeInt(this.type);
            dest.writeInt(this.id);
            dest.writeString(this.gaPrefix);
            dest.writeString(this.title);
        }

        public TopStories() {
        }

        protected TopStories(Parcel in) {
            this.image = in.readString();
            this.type = in.readInt();
            this.id = in.readInt();
            this.gaPrefix = in.readString();
            this.title = in.readString();
        }

        public static final Parcelable.Creator<TopStories> CREATOR = new Parcelable.Creator<TopStories>() {
            @Override
            public TopStories createFromParcel(Parcel source) {
                return new TopStories(source);
            }

            @Override
            public TopStories[] newArray(int size) {
                return new TopStories[size];
            }
        };
    }
}
