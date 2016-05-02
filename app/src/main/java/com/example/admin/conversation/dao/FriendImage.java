package com.example.admin.conversation.dao;

/**
 * Created by admin on 2016/5/2.
 */
public class FriendImage {
    private int imageSrc;
    private String name;

    public FriendImage() {

    }

    public FriendImage(int imageSrc, String name) {
        this.imageSrc = imageSrc;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }
}
