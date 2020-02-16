package com.example.allay;

public class professionalData {

    private String name;
    private String recentMessage;
    private String active;
    private String imgUrl;

    public professionalData(String name, String recentMessage, String url, String active){
        this.name = name;
        this.recentMessage = recentMessage;
        this.imgUrl = url;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecentMessage() {
        return recentMessage;
    }

    public void setRecentMessage(String recentMessage) {
        this.recentMessage = recentMessage;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
