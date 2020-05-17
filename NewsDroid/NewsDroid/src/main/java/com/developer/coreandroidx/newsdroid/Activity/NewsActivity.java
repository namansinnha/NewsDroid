package com.developer.coreandroidx.newsdroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.developer.coreandroidx.newsdroid.Adapter.NewsAdapter;
import com.developer.coreandroidx.newsdroid.Adapter.SourceAdapter;
import com.developer.coreandroidx.newsdroid.Helper.ApplicationController;
import com.developer.coreandroidx.newsdroid.Model.NewsJsonResponse;
import com.developer.coreandroidx.newsdroid.Model.SourceJsonResponse;
import com.developer.coreandroidx.newsdroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import es.dmoral.toasty.Toasty;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    private RecyclerView newsRecyclerList, sourceRecyclerList;
    private EditText searchNews_Everything;
    private FloatingActionButton searchNews_btn, more_btn;
    private RelativeLayout mainLayout;

    private RequestQueue requestQueue;
    private NewsAdapter mNewsAdapter;
    private ArrayList<NewsJsonResponse> mNewsJsonResponses;
    private SourceAdapter mSourcesAdapter;
    private ArrayList<SourceJsonResponse> mSourceJsonResponses;
    private String newsName;
    private String searchedWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        requestQueue = Volley.newRequestQueue(NewsActivity.this);

        initializeViews();
        mNewsJsonResponses = new ArrayList<>();
        mSourceJsonResponses = new ArrayList<>();

        searchNews_Everything.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    fetchNewsOnSearch();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                    newsRecyclerList.setVisibility(View.VISIBLE);
                    sourceRecyclerList.setVisibility(View.GONE);
                    searchNews_Everything.setText("");

                }
                return false;
            }
        });

        searchNews_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                if (searchNews_Everything.getText().toString().isEmpty()) {
                    fetchNews();
                } else {
                    newsRecyclerList.setVisibility(View.VISIBLE);
                    sourceRecyclerList.setVisibility(View.GONE);
                    fetchNewsOnSearch();
                    searchNews_Everything.setText("");
                }
            }
        });


        more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newsRecyclerList.setVisibility(View.GONE);
                sourceRecyclerList.setVisibility(View.VISIBLE);
                fetchSourceNews();
            }
        });

        fetchNews();

    }

    private void initializeViews() {

        mainLayout = findViewById(R.id.mainLayout);
        searchNews_Everything = findViewById(R.id.news_search_bar);
        more_btn = findViewById(R.id.more);
        searchNews_btn = findViewById(R.id.btn_search);

        newsRecyclerList = findViewById(R.id.newsRecyclerList);
        newsRecyclerList.setHasFixedSize(true);
        newsRecyclerList.setLayoutManager(new LinearLayoutManager(this));

        sourceRecyclerList = findViewById(R.id.sourceRecyclerList);
        sourceRecyclerList.setHasFixedSize(true);
        sourceRecyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchNews() {

        String newsApiUrl = ApplicationController.HEADLINES_URL + "?q=corona" + "&" + ApplicationController.PAGE_SIZE + "=50" + "&apiKey=" + ApplicationController.API_KEY;
        mNewsJsonResponses.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, newsApiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("articles");
                    for (int i = 0; i <= jsonArray.length(); i++) {

                        JSONObject articlesJsonObject = jsonArray.getJSONObject(i);

                        JSONObject object = articlesJsonObject.getJSONObject("source");
                        for (int s = 0; s < object.length(); s++) {

                            newsName = object.getString(ApplicationController.NEWS_NAME);

                        }

                        String newsImageUrl = articlesJsonObject.getString(ApplicationController.NEWS_IMAGE);
                        String newsTitle = articlesJsonObject.getString(ApplicationController.NEWS_TITLE);
                        String newsUrl = articlesJsonObject.getString(ApplicationController.NEWS_URL);
                        String newsDescription = articlesJsonObject.getString(ApplicationController.NEWS_DESC);
                        String newsContent = articlesJsonObject.getString(ApplicationController.NEWS_CONTENT);
                        String newsPublishDate = articlesJsonObject.getString(ApplicationController.NEWS_PUBLISHED_DATE);

                        newsPublishDate = newsPublishDate.split("T")[0];
//                        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//                        SimpleDateFormat output = new SimpleDateFormat("dd/MMM/yyyy hh:mm a");
//
//                        Date d = null;
//                        try {
//                            d = input.parse(newsPublishDate);
//
//                            String formatted = output.format(d);
//                            newsPublishDate =
//
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }

                        mNewsJsonResponses.add(new NewsJsonResponse(newsName, newsTitle, newsDescription, newsImageUrl, newsPublishDate, newsContent, newsUrl));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mNewsAdapter = new NewsAdapter(NewsActivity.this, mNewsJsonResponses);
                mNewsAdapter.notifyDataSetChanged();
                newsRecyclerList.setAdapter(mNewsAdapter);
                mNewsAdapter.setOnItemClickListener(NewsActivity.this);
                newsRecyclerList.setNestedScrollingEnabled(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toasty.error(getApplicationContext(), "Error :" + error, Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    private void fetchNewsOnSearch() {

        searchedWord = searchNews_Everything.getText().toString();
        String newsApiUrl = ApplicationController.EVERYTHING_URL + "?q=" + searchedWord + "&pageSize=" + ApplicationController.PAGE_SIZE + "&apiKey=" + ApplicationController.API_KEY;
        mNewsJsonResponses.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, newsApiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("articles");
                    for (int i = 0; i <= jsonArray.length(); i++) {

                        JSONObject articlesJsonObject = jsonArray.getJSONObject(i);

                        JSONObject object = articlesJsonObject.getJSONObject("source");
                        for (int s = 0; s < object.length(); s++) {

                            newsName = object.getString(ApplicationController.NEWS_NAME);

                        }
                        String newsImageUrl = articlesJsonObject.getString(ApplicationController.NEWS_IMAGE);
                        String newsTitle = articlesJsonObject.getString(ApplicationController.NEWS_TITLE);
                        String newsUrl = articlesJsonObject.getString(ApplicationController.NEWS_URL);
                        String newsDescription = articlesJsonObject.getString(ApplicationController.NEWS_DESC);
                        String newsContent = articlesJsonObject.getString(ApplicationController.NEWS_CONTENT);
                        String newsPublishDate = articlesJsonObject.getString(ApplicationController.NEWS_PUBLISHED_DATE);

                        mNewsJsonResponses.add(new NewsJsonResponse(newsName, newsTitle, newsDescription, newsImageUrl, newsPublishDate, newsContent, newsUrl));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

                mNewsAdapter = new NewsAdapter(NewsActivity.this, mNewsJsonResponses);
                mNewsAdapter.notifyDataSetChanged();
                newsRecyclerList.setAdapter(mNewsAdapter);
                mNewsAdapter.setOnItemClickListener(NewsActivity.this);
                newsRecyclerList.setNestedScrollingEnabled(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toasty.error(getApplicationContext(), "Error :" + error, Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    private void fetchSourceNews() {

        String sourceUrl = ApplicationController.SOURCES_URL + "?apiKey=" + ApplicationController.API_KEY + "&pageSize=50";
        mSourceJsonResponses.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, sourceUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("sources");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject sourceJsonObject = jsonArray.getJSONObject(i);
                        String newsName = sourceJsonObject.getString(ApplicationController.NEWS_NAME);
                        String newsDescription = sourceJsonObject.getString(ApplicationController.NEWS_DESC);
                        String newsUrl = sourceJsonObject.getString(ApplicationController.NEWS_URL);
                        String newsCategory = sourceJsonObject.getString(ApplicationController.NEWS_CATEGORY);
                        String newsLanguage = sourceJsonObject.getString(ApplicationController.NEWS_LANGUAGE);
                        String newsCountry = sourceJsonObject.getString(ApplicationController.NEWS_COUNTRY);

                        mSourceJsonResponses.add(new SourceJsonResponse(newsName, newsDescription, newsUrl, newsCategory, newsLanguage, newsCountry));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

                mSourcesAdapter = new SourceAdapter(NewsActivity.this, mSourceJsonResponses);
                mSourcesAdapter.notifyDataSetChanged();
                sourceRecyclerList.setAdapter(mSourcesAdapter);
                sourceRecyclerList.setNestedScrollingEnabled(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toasty.error(getApplicationContext(), "Error: " + error, Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void OnItemClick(int position) {

    }
}
