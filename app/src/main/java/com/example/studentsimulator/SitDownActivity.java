package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SitDownActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_down);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SitDownActivity.this, ForestBusActivity.class);
                SitDownActivity.this.startActivity(mainIntent);
                SitDownActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    }


