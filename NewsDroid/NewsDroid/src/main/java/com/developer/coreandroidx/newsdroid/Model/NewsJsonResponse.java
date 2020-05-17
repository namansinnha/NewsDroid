package com.developer.coreandroidx.newsdroid.Model;

public class NewsJsonResponse {

    String news_name, news_title, news_desc, news_image_url, news_published_date, news_content, news_url;

    public NewsJsonResponse() {
    }

    public NewsJsonResponse(String news_name, String news_title, String news_desc, String news_image_url, String news_published_date, String news_content, String news_url) {
        this.news_name = news_name;
        this.news_title = news_title;
        this.news_desc = news_desc;
        this.news_image_url = news_image_url;
        this.news_published_date = news_published_date;
        this.news_content = news_content;
        this.news_url = news_url;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_desc() {
        return news_desc;
    }

    public void setNews_desc(String news_desc) {
        this.news_desc = news_desc;
    }

    public String getNews_image_url() {
        return news_image_url;
    }

    public void setNews_image_url(String news_image_url) {
        this.news_image_url = news_image_url;
    }

    public String getNews_published_date() {
        return news_published_date;
    }

    public void setNews_published_date(String news_published_date) {
        this.news_published_date = news_published_date;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }
}
