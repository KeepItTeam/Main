package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;


public class TestActivity extends AppCompatActivity {
    int res=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String[]wrongAnswers={"No1","No2","No3","No4"};
        int wrongAnswersNumber=3;
        String question="question";
        String rightAnswer="YES";
        //TODO разобраться с паузой, убрать кнопку
        final Button backButton = findViewById(R.id.backButton);
        backButton.setEnabled(false);
        //массивы дважды перемешиваются чтобы 1)взять случайые вопросы из неправильных 2)перемешать правильные с неправильными
        final Button[] buttonAnswers = new Button[wrongAnswersNumber+1];
        final LinearLayout variants= findViewById(R.id.variants);
        Resources resources = getResources();
        final int greenColor = resources.getColor(R.color.rightAnswerGreen,  null);
        final int redColor = resources.getColor(R.color.wrongAnswerRed,  null);
        Collections.shuffle(Arrays.asList(wrongAnswers));

        //вопрос
        TextView questionView = findViewById(R.id.question);
        questionView.setText(question);

        //Кнопка с правильным ответом
        final Button rightAnswerButton=new Button(this);
        rightAnswerButton.setText(rightAnswer);
        rightAnswerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //что-то++ (или тащим через интент)
                rightAnswerButton.setBackgroundColor(greenColor);
                backButton.setEnabled(true);
                for (Button btn:buttonAnswers){
                    btn.setEnabled(false);
                }
                res=1;

            }
        });
        buttonAnswers[0]=rightAnswerButton;

        //Кнопки с неправильными ответами
        for (int i = 0; i < wrongAnswersNumber; i++){
            final Button button=new Button(this);
            button.setText(wrongAnswers[i]);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //что-то++ (или тащим через интент)
                    button.setBackgroundColor(redColor);
                    rightAnswerButton.setBackgroundColor(greenColor);
                    backButton.setEnabled(true);
                    for (Button btn:buttonAnswers){
                        btn.setEnabled(false);
                    }

                }
            });
            buttonAnswers[i+1]=button;
        }

        //перемешиваем кнопки
        Collections.shuffle(Arrays.asList(buttonAnswers));

        //добавляем кнопки на экран
        for (int i = 0; i <= wrongAnswersNumber; i++) {
            buttonAnswers[i].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            variants.addView(buttonAnswers[i]);
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("res",res);
        startActivity(intent);
    }


}