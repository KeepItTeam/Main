package com.example.studentsimulator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

public class ExamSpeechActivity extends AppCompatActivity {
    private TextView textTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_speech);
        init();

    }
    private void init()
    {
        textTest = findViewById(R.id.textTest);
    }
    public void onClickMic(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        startActivityForResult(intent, 10);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null)
        {
            switch (requestCode)
            {
                case 10:
                    ArrayList<String> text =  data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textTest.setText(text.get(0));
                    textCommand(text.get(0));
                    break;
            }
        }
    }

    private void textCommand(String text)
    {
        switch (text)
        {
            case "да":
                Toast toast1 = Toast.makeText(getApplicationContext(), "Ответ ПОЛОЖИТЕЛЬНЫЙ", Toast.LENGTH_LONG);
                toast1.show();
                break;
            case "нет":
                Toast toast2 = Toast.makeText(getApplicationContext(), "Ответ ОТРИЦАТЕЛЬНЫЙ", Toast.LENGTH_LONG);
                toast2.show();
                break;

        }
    }
}
