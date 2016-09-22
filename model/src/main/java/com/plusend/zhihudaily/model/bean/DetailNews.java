package com.plusend.zhihudaily.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by plusend on 16/5/30.
 */
public class DetailNews {

    @SerializedName("body")
    private String body;
    @SerializedName("image_source")
    private String imageSource;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("ga_prefix")
    private String gaPrefix;
    @SerializedName("section")
    private Section section;
    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;
    @SerializedName("js")
    private List<?> js;
    @SerializedName("images")
    private List<String> images;
    @SerializedName("css")
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "DetailNews{" +
                "body='" + body + '\'' +
                ", imageSource='" + imageSource + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", gaPrefix='" + gaPrefix + '\'' +
                ", section=" + section +
                ", type=" + type +
                ", id=" + id +
                ", js=" + js +
                ", images=" + images +
                ", css=" + css +
                '}';
    }
}
