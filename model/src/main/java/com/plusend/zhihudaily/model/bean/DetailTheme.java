package com.plusend.zhihudaily.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by plusend on 16/5/30.
 */
public class DetailTheme {

    @SerializedName("description")
    private String description;
    @SerializedName("background")
    private String background;
    @SerializedName("color")
    private int color;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("image_source")
    private String imageSource;
    @SerializedName("stories")
    private List<Stories> stories;
    @SerializedName("editors")
    private List<Editors> editors;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Editors> getEditors() {
        return editors;
    }

    public void setEditors(List<Editors> editors) {
        this.editors = editors;
    }

    public static class Stories {
        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
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

    public static class Editors {
        @SerializedName("url")
        private String url;
        @SerializedName("bio")
        private String bio;
        @SerializedName("id")
        private int id;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("name")
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
