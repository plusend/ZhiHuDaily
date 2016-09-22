package com.plusend.zhihudaily.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by plusend on 16/6/12.
 */
public class Section {
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    @Override
    public String toString() {
        return "Section{" +
                "thumbnail='" + thumbnail + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
