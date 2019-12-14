package com.example.smartcity.Model;

public class Picture {
    private Integer pictureId;
    private String path;
    private Integer item;

    public Integer getPictureId() {
        return pictureId;
    }

    public Integer getItem() {
        return item;
    }

    public String getPath() {
        return path;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
