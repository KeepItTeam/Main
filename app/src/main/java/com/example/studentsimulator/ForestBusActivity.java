package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ForestBusActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest_bus);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(ForestBusActivity.this, UniversityActivity.class);
                ForestBusActivity.this.startActivity(mainIntent);
                ForestBusActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}