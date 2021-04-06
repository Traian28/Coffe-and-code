package com.ea.vrtx;

public class NewsItem {
    private String desc;
    private String title;

    public NewsItem(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public NewsItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
