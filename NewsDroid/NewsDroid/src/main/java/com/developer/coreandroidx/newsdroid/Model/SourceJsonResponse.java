package com.developer.coreandroidx.newsdroid.Model;

public class SourceJsonResponse {

    String newsName, newsDesc, newsUrl, newsCategory, newsLanguage, newsCountry;

    public SourceJsonResponse() {
    }

    public SourceJsonResponse(String newsName, String newsDesc, String newsUrl, String newsCategory, String newsLanguage, String newsCountry) {
        this.newsName = newsName;
        this.newsDesc = newsDesc;
        this.newsUrl = newsUrl;
        this.newsCategory = newsCategory;
        this.newsLanguage = newsLanguage;
        this.newsCountry = newsCountry;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getNewsLanguage() {
        return newsLanguage;
    }

    public void setNewsLanguage(String newsLanguage) {
        this.newsLanguage = newsLanguage;
    }

    public String getNewsCountry() {
        return newsCountry;
    }

    public void setNewsCountry(String newsCountry) {
        this.newsCountry = newsCountry;
    }
}

