package com.plusend.zhihudaily.model.bean;

import java.io.Serializable;

/**
 * Created by plusend on 16/6/12.
 */
public class TopStories implements Serializable{

    private static final long serialVersionUID = -7506848418523537988L;

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
}
