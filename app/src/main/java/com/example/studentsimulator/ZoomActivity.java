package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class ZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);
    }

    public void goToPractice(View view) {
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        int mood = sPref.getInt("mood", 85)-3;
        int energy = sPref.getInt("energy", 85)-3;
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("mood", mood);
        ed.putInt("energy", energy);
        ed.commit();
        Intent intent=new Intent(this,RunAwayActivity.class);
        startActivity(intent);
    }
}