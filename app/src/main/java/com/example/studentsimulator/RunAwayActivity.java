package com.example.studentsimulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class RunAwayActivity extends AppCompatActivity {

    int QuestionCounter;
    private GameView gameView;
    private GifImageView gifImageView;
    private Timer timer;
    private TimerTask timerTask;
    private String question="Ахах?";
    private String str = "Вопросов осталось: ";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] questions = {"question1", "question2", "question3" ,"question4","question5","question6","question7","question8","question9","question10",
                "question11"};
        String[] answers = {"yes","no", "yes","no", "yes","yes", "yes","yes", "yes", "yes", "yes"};
        int[] indexes ={0,1,2,3,4,5,6,7,8,9,10};
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_run_away);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Перемешиваем массив с вопросами
        Collections.shuffle(Collections.singletonList(indexes));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);

        setContentView(gameView);
        //Teacher teacher = new Teacher(getResources());

        setContentView(R.layout.activity_run_away);


        //gifImageView = new GifImageView(this);
        /*TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        }*/

        QuestionCounter = 10;
        // СКОЛЬКО ВОПРОСОВ ОСТАЛОСЬ
        //TextView remainingNumber = findViewById(R.id.RemainingNumber);
        //remainingNumber.setText(str+ QuestionCounter);
        //Number number = findNumberById(R.id.editTextNumber);
        //ГИФКИ СТУДЕНТА И ТИЧЕРА
        GifImageView student = findViewById(R.id.student);
        GifImageView teacher = findViewById(R.id.teacher);

        GifImageView gifImageView = findViewById(R.id.imageView);


        //ОБЪЯВЛЯЕМ АНИМАЦИИ
        TranslateAnimation animRight = new TranslateAnimation(0, 100, 0, 0);
        animRight.setDuration(300);

        TranslateAnimation animLeft = new TranslateAnimation(0, -100, 0, 0);
        animLeft.setDuration(300);



        //ОПИСАНИЕ АЛЕРТА




        //АЛЕРТ ПОБЕДЫ
        AlertDialog.Builder builderWin = new AlertDialog.Builder(RunAwayActivity.this);
        builderWin.setTitle("").setMessage("ПОБЕДА!!!").setPositiveButton("Ок", (dialog, id)->{
            goBack();
        });
        AlertDialog alertWin = builderWin.create();


        //АЛЕРТ ПОРАЖЕНИЯ
        AlertDialog.Builder builderLoose =new AlertDialog.Builder(RunAwayActivity.this);
        builderLoose.setTitle("").setMessage("Вы проиграли").setPositiveButton("Ок", (dialog, id)->{
            goBack();
        });
        AlertDialog alertLoose = builderLoose.create();



        //ПЕРВАЯ ЗАДЕРЖКА
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                AlertDialog.Builder builder =new AlertDialog.Builder(RunAwayActivity.this);
                builder.setTitle("").setMessage(questions[indexes[QuestionCounter]]).setPositiveButton("Да", (dialog, id)->{
                    QuestionCounter--;
                    ((GifDrawable)gifImageView.getDrawable()).start();
                    ((GifDrawable)teacher.getDrawable()).start();
                    ((GifDrawable)student.getDrawable()).start();
                    //remainingNumber.setText(str+ QuestionCounter);
                    if(answers[indexes[QuestionCounter]].equals("yes"))
                        student.startAnimation(animRight);
                    else
                        student.startAnimation(animLeft);
                    dialog.cancel();
                }).setNegativeButton("Нет", (dialog, id)->{
                    QuestionCounter--;
                    ((GifDrawable)gifImageView.getDrawable()).start();
                    ((GifDrawable)teacher.getDrawable()).start();
                    ((GifDrawable)student.getDrawable()).start();
                    //remainingNumber.setText(str+ QuestionCounter);
                    if(answers[indexes[QuestionCounter]].equals("no"))
                        student.startAnimation(animRight);
                    else
                        student.startAnimation(animLeft);

                    dialog.cancel();
                });
                AlertDialog alert = builder.create();
                alert.show();
                ((GifDrawable)gifImageView.getDrawable()).stop();
                ((GifDrawable)teacher.getDrawable()).stop();
                ((GifDrawable)student.getDrawable()).stop();
            }
        }, 2000);

        //АНИМАЦИЯ ВПРАВО
        animRight.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }


            //ПОСЛЕ СДВИГА ВПРАВО
            @Override
            public void onAnimationEnd(Animation animation)
            {
                /*FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)student.getLayoutParams();
                params.rightMargin += 100;
                student.setLayoutParams(params);*/
                student.setX(student.getX()+100);

                //QuestionCounter--;
                //remainingNumber.setText("Вопросов осталось: "+ QuestionCounter);

                Handler handler = new Handler();
                Log.e("info", "ANIMATION END "+student.getX()+" "+teacher.getX());
                if(student.getX()>500){
                    alertWin.show();
                    ((GifDrawable)gifImageView.getDrawable()).stop();
                    ((GifDrawable)teacher.getDrawable()).stop();
                    ((GifDrawable)student.getDrawable()).stop();
                }

                else {
                    if(QuestionCounter==0){
                        alertLoose.show();
                        ((GifDrawable)gifImageView.getDrawable()).stop();
                        ((GifDrawable)teacher.getDrawable()).stop();
                        ((GifDrawable)student.getDrawable()).stop();
                    }

                    else {
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                AlertDialog.Builder builder =new AlertDialog.Builder(RunAwayActivity.this);
                                builder.setTitle("").setMessage(questions[indexes[QuestionCounter]]).setPositiveButton("Да", (dialog, id)->{
                                    QuestionCounter--;
                                    ((GifDrawable)gifImageView.getDrawable()).start();
                                    ((GifDrawable)teacher.getDrawable()).start();
                                    ((GifDrawable)student.getDrawable()).start();
                                    //remainingNumber.setText(str+ QuestionCounter);
                                    if(answers[indexes[QuestionCounter]].equals("yes"))
                                        student.startAnimation(animRight);
                                    else
                                        student.startAnimation(animLeft);
                                    dialog.cancel();
                                }).setNegativeButton("Нет", (dialog, id)->{
                                    QuestionCounter--;
                                    ((GifDrawable)gifImageView.getDrawable()).start();
                                    ((GifDrawable)teacher.getDrawable()).start();
                                    ((GifDrawable)student.getDrawable()).start();
                                    //remainingNumber.setText(str+ QuestionCounter);
                                    if(answers[indexes[QuestionCounter]].equals("no"))
                                        student.startAnimation(animRight);
                                    else
                                        student.startAnimation(animLeft);

                                    dialog.cancel();
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                                ((GifDrawable)gifImageView.getDrawable()).stop();
                                ((GifDrawable)teacher.getDrawable()).stop();
                                ((GifDrawable)student.getDrawable()).stop();
                                //remainingNumber.setText(str+ QuestionCounter);
                            }
                        }, 2000);
                    }

                }

            }
        });

        //АНИМАЦИЯ ВЛЕВО
        animLeft.setAnimationListener(new TranslateAnimation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            //ПОСЛЕ СДВИГА ВЛЕВО
            @Override
            public void onAnimationEnd(Animation animation) {
                /*FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)student.getLayoutParams();
                params.rightMargin += 100;
                student.setLayoutParams(params);*/
                student.setX(student.getX() - 100);

                //QuestionCounter--;
                //remainingNumber.setText(str+ QuestionCounter);


                if (QuestionCounter == 0){
                    alertLoose.show();
                    ((GifDrawable)gifImageView.getDrawable()).stop();
                    ((GifDrawable)teacher.getDrawable()).stop();
                    ((GifDrawable)student.getDrawable()).stop();
                }


                else if (teacher.getX() >= student.getX() + 500){
                    alertLoose.show();
                    ((GifDrawable)gifImageView.getDrawable()).stop();
                    ((GifDrawable)teacher.getDrawable()).stop();
                    ((GifDrawable)student.getDrawable()).stop();
                }


                else {


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RunAwayActivity.this);
                        builder.setTitle("").setMessage(questions[indexes[QuestionCounter]]).setPositiveButton("Да", (dialog, id) -> {
                            QuestionCounter--;
                            ((GifDrawable)gifImageView.getDrawable()).start();
                            ((GifDrawable)teacher.getDrawable()).start();
                            ((GifDrawable)student.getDrawable()).start();

                            //remainingNumber.setText(str+ QuestionCounter);
                            if (answers[indexes[QuestionCounter]].equals("yes"))
                                student.startAnimation(animRight);
                            else
                                student.startAnimation(animLeft);
                            dialog.cancel();
                        }).setNegativeButton("Нет", (dialog, id) -> {
                            QuestionCounter--;
                            ((GifDrawable)gifImageView.getDrawable()).start();
                            ((GifDrawable)teacher.getDrawable()).start();
                            ((GifDrawable)student.getDrawable()).start();

                            //remainingNumber.setText(str+ QuestionCounter);
                            if (answers[indexes[QuestionCounter]].equals("no"))
                                student.startAnimation(animRight);
                            else
                                student.startAnimation(animLeft);

                            dialog.cancel();
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        ((GifDrawable)gifImageView.getDrawable()).stop();
                        ((GifDrawable)teacher.getDrawable()).stop();
                        ((GifDrawable)student.getDrawable()).stop();
                    }
                }, 2000);

            }

            }
        });
    }
    public void onClick(View view) throws InterruptedException {
        GifImageView gifImageView = findViewById(R.id.imageView);
        GifImageView student = findViewById(R.id.student);
        GifImageView teacher = findViewById(R.id.teacher);
        ((GifDrawable)gifImageView.getDrawable()).stop();
        ((GifDrawable)student.getDrawable()).stop();
        ((GifDrawable)teacher.getDrawable()).stop();
        goBack();

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}