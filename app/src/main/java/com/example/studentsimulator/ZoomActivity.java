package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        /*int daysToExam=getIntent().getIntExtra("daysToExam",1);
        Log.e("log", String.valueOf(daysToExam));
        TextView daysToExamView=findViewById(R.id.textView5);
        Log.e("log", daysToExamView.toString());
        if (daysToExam>0){
            daysToExamView.setText(Integer.toString(daysToExam));
        }
        else{
            daysToExamView.setText("");
            TextView tv_dayView=findViewById(R.id.textView4);
            tv_dayView.setText("");
            TextView tvExamView=findViewById(R.id.textView6);
            tvExamView.setText("День экзамена");
        }*/
    }
    public void goToRunAwayPractice(View view) {
        view.setEnabled(false);
        getSad();
        Intent intent=new Intent(this,RunAwayActivity.class);
        startActivity(intent);
    }
    private void getSad(){
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        int mood = sPref.getInt("mood", 85)-3;
        int energy = sPref.getInt("energy", 85)-3;
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("mood", mood);
        ed.putInt("energy", energy);
        ed.commit();
    }

    public void goToTestPractice(View view) {
        getSad();
        view.setEnabled(false);
        //todo ко второй практике
    }

    public void goToLecture(View view) {
        getSad();
        view.setEnabled(false);
        //todo к лекции
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void showCourseStatistics(View view) {
        Intent intent = new Intent(this, StatActivity.class);
        startActivity(intent);

    }

}