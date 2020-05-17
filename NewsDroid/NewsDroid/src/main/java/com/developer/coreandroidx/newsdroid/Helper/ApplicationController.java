package com.developer.coreandroidx.newsdroid.Helper;

import android.app.Application;

public class ApplicationController extends Application {

    public static final String API_KEY = "f7f3fc682a8741968e711234a2fa64e9";
    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String EVERYTHING_URL = BASE_URL+"everything";
    public static final String HEADLINES_URL = BASE_URL+"top-headlines";
    public static final String SOURCES_URL = BASE_URL+"sources";

    public static final String PAGE_SIZE = "50";
    public static final String NEWS_IMAGE ="urlToImage";
    public static final String NEWS_NAME ="name";
    public static final String NEWS_CATEGORY ="category";
    public static final String NEWS_LANGUAGE ="language";
    public static final String NEWS_COUNTRY ="country";
    public static final String NEWS_TITLE ="title";
    public static final String NEWS_URL ="url";
    public static final String NEWS_DESC ="description";
    public static final String NEWS_CONTENT ="content";
    public static final String NEWS_PUBLISHED_DATE ="publishedAt";

}
