package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    public void goToSpeechExam(View view) {
        Intent intent=new Intent(this, ExamSpeechActivity.class);
        startActivity(intent);
    }
}