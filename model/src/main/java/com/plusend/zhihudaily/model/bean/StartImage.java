package com.plusend.zhihudaily.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by plusend on 16/5/30.
 */
public class StartImage {

    @SerializedName("text")
    private String text;
    @SerializedName("img")
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
