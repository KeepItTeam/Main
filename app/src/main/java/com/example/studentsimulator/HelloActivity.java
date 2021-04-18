package com.example.studentsimulator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);


    }
    int i=0;
    String str="";
    int dataManagement=0;
    int communications=0;
    int safety=0;
    int contentMaking=0;
    int problemSolving=0;
    String dataStat;
    final int questionsNumber=3;
    final TestQuestion[] questions={
            new TestQuestion("Можно ли совершить телефонный звонок, если у вас нет подключения к мобильной сети?","да", "нет"),
            new TestQuestion("Трелло-это платформа для совершения видеозвонков?","да", "нет"),
            new TestQuestion("Сколько ячеек в Экселе между А1 и В4?","8", "нет")//да, именно так - 8
    };


    void writeFile(int dataManagement, int communications, int safety, int contentMaking, int problemSolving) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("data.txt", MODE_PRIVATE)));
            // пишем данные
            bw.write(dataManagement+" "+communications+" "+safety+" "+contentMaking+" "+problemSolving);
            // закрываем поток
            bw.close();
            Log.e("LOG_TAG", "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onClickMic()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        startActivityForResult(intent, 10);
        TextView tv_text_answer_Teacher=findViewById(R.id.tv_text_answer_Teacher);
        tv_text_answer_Teacher.setText(questions[i].question);
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
                    //textTest.setText(text.get(0));
                    textCommand(text.get(0));
                    break;
            }
        }
    }
      private void textCommand(String text)
    {
        if (text.equals(questions[i].rightAnswer)){
            Toast.makeText(getApplicationContext(), "верно",Toast.LENGTH_LONG);
            TextView tv_answer_makar=findViewById(R.id.tv_answer_makar);
            tv_answer_makar.setText(text);
            str+=i;
            switch (i){
                    case 0:
                    case 1:
                    communications++;
                    break;
                case 2:
                    dataManagement++;
            }
        }
        else
        {
            TextView tv_answer_makar=findViewById(R.id.tv_answer_makar);
            tv_answer_makar.setText(text);
        }
        try {
            Thread.sleep(1000*i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        if (i<questionsNumber)
        {

            onClickMic();
        }
        else{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeFile(dataManagement,communications,safety,contentMaking,problemSolving);
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("res",str);
            startActivity(intent);
             }
    }

    public void goToSpeechExam(View view) {
        ImageView icon=findViewById(R.id.imageView);
        icon.setImageResource(R.drawable.microphone);
        TextView tv_choose_text=findViewById(R.id.tv_choose_text);
        tv_choose_text.setText("Нажмите, чтобы ответить");
        TextView tv_hello_answer_teacher=findViewById(R.id.tv_hello_answer_teacher);
        tv_hello_answer_teacher.setText("Ответь,");

        TextView tv_answer_makar=findViewById(R.id.tv_answer_makar);
        tv_answer_makar.setText("");
        onClickMic();

    }
    public void goToMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }





}