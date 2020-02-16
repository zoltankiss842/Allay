package com.example.allay;

import java.io.Serializable;

public class dreamData {

    private String title;
    private String story;

    public dreamData(String title, String story){
        this.title = title;
        this.story = story;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

}
