package com.example.studentsimulator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);


    }
    int i=0;

    final int questionsNumber=3;
    final TestQuestion[] questions={
            new TestQuestion("Можно ли совершить телефонный звонок, если у вас нет подключения к мобильной сети?","да", "нет"),
            new TestQuestion("Трелло-это платформа для совершения видеозвонков?","да", "нет"),
            new TestQuestion("Сколько ячеек в Экселе между А1 и В4?","8", "нет")//да, именно так - 8
    };



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
            //todo сохранить и обработать результат
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
            Intent intent = new Intent(this,MainActivity.class);
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
}