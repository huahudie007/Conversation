package com.example.admin.conversation.dao;

/**
 * Created by admin on 2016/5/2.
 */
public class Friend {
    private String name;
    private String nickname;
    private String time;
    private int imgSrc;
    private String talkContent;

    public Friend( ){
    }

    public Friend(int imgSrc, String name, String talkContent, String time, String nickname) {
        this.imgSrc = imgSrc;
        this.name = name;
        this.talkContent = talkContent;
        this.time = time;
        this.nickname=nickname;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
