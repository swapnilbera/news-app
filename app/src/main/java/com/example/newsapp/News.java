package com.example.newsapp;

public class News {
    public String headline;
    public String date;
    public String image_url;

    public String getChrome_url() {
        return chrome_url;
    }

    public void setChrome_url(String chrome_url) {
        this.chrome_url = chrome_url;
    }

    public String chrome_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getHeadline() {
        return headline;
    }
    public String getDate() {
        return date;
    }
    public News(String headline,String date,String image_url,String chrome_url){
        this.headline=headline;
        this.date=date;
        this.image_url=image_url;
        this.image_url=image_url;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
