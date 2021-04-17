package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
    }

    public void gpToZoom(View view) {
        Intent intent = new Intent(this, ZoomActivity.class);
        startActivity(intent);
    }
    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void showCourseStatistics(View view) {
        Intent intent = new Intent(this, StatActivity.class);
        startActivity(intent);

    }
    public void goStudying(View view) {
        //посетить ли лекцию и практику (чекбокс)
        view.setEnabled(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(CoursesActivity.this);
        builder.setTitle("Идёшь на пары?").setPositiveButton("Конечно",
                (dialog, id) -> {
                    Intent intent = new Intent(this, ZoomActivity.class);
                    startActivity(intent);

                });
        builder.setNegativeButton("Нет, спасибо",
                (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();

    }
}