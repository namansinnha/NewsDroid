package com.developer.coreandroidx.newsdroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.developer.coreandroidx.newsdroid.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                gotoWallpaperActivity();
            }
        }, 2000);
    }

    private void gotoWallpaperActivity() {

        Intent intent = new Intent(SplashActivity.this, NewsActivity.class);
        startActivity(intent);
        finish();

    }
}