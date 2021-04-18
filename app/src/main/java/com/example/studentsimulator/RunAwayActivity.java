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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
    int communications;
    int dataManagement;
    int safety;
    int contentMaking;
    int problemSolving;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] questions = {"Может ли включить преподователь включить твой звук?",
                "Можно ли  создать конференцию ЗУМ на одного человека?",
                "Могут ли участники одной конференции ЗУМ запустить несколько демонстраций экрана одновременно? "};
        String[] answers = {"yes","yes", "no"};
        int[] indexes ={0,1,2};
        super.onCreate(savedInstanceState);
        String dataFromFile=readFile();
        String[] a=dataFromFile.split(" ");
        dataManagement= Integer.parseInt(a[0]);
        communications=Integer.parseInt(a[1]);
        safety=Integer.parseInt(a[2]);
        contentMaking=Integer.parseInt(a[3]);
        problemSolving=Integer.parseInt(a[4]);
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

        QuestionCounter = 2 ;
        // СКОЛЬКО ВОПРОСОВ ОСТАЛОСЬ
        //TextView remainingNumber = findViewById(R.id.RemainingNumber);
        //remainingNumber.setText(str+ QuestionCounter);
        //Number number = findNumberById(R.id.editTextNumber);
        //ГИФКИ СТУДЕНТА И ТИЧЕРА
        GifImageView student = findViewById(R.id.student);
        GifImageView teacher = findViewById(R.id.teacher);

        GifImageView gifImageView = findViewById(R.id.imageView);


        //ОБЪЯВЛЯЕМ АНИМАЦИИ
        TranslateAnimation animRight = new TranslateAnimation(0, 200, 0, 0);
        animRight.setDuration(300);

        TranslateAnimation animLeft = new TranslateAnimation(0, -300, 0, 0);
        animLeft.setDuration(300);



        //ОПИСАНИЕ АЛЕРТА




        //АЛЕРТ ПОБЕДЫ
        AlertDialog.Builder builderWin = new AlertDialog.Builder(RunAwayActivity.this);
        builderWin.setTitle("").setMessage("ПОЗДРАВЛЯЕМ, ВЫ ПРАВИЛЬНО ОТВЕТИЛИ НА ВСЕ ВОПРОСЫ И СБЕЖАЛИ ОТ ЗЛОГО ПРЕПОДАВАТЕЛЯ  ").setPositiveButton("Ок", (dialog, id)->{
            writeFile(dataManagement,communications,safety,contentMaking,problemSolving);
            goBack();
        });
        AlertDialog alertWin = builderWin.create();


        //АЛЕРТ ПОРАЖЕНИЯ
        AlertDialog.Builder builderLoose =new AlertDialog.Builder(RunAwayActivity.this);
        builderLoose.setTitle("").setMessage("Вы проиграли").setPositiveButton("Ок", (dialog, id)->{
            writeFile(dataManagement,communications,safety,contentMaking,problemSolving);
            goBack();
        });
        AlertDialog alertLoose = builderLoose.create();



        //ПЕРВАЯ ЗАДЕРЖКА
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                AlertDialog.Builder builder =new AlertDialog.Builder(RunAwayActivity.this);
                builder.setTitle("").setMessage(questions[QuestionCounter]).setPositiveButton("Да", (dialog, id)->{

                    ((GifDrawable)gifImageView.getDrawable()).start();
                    ((GifDrawable)teacher.getDrawable()).start();
                    ((GifDrawable)student.getDrawable()).start();
                    //remainingNumber.setText(str+ QuestionCounter);
                    if(answers[indexes[QuestionCounter]].equals("yes")){
                        communications++;
                        student.startAnimation(animRight);

                    }
                    else
                    {
                        student.startAnimation(animLeft);
                        communications--;
                    }
                    QuestionCounter--;
                    dialog.cancel();
                }).setNegativeButton("Нет", (dialog, id)->{

                    ((GifDrawable)gifImageView.getDrawable()).start();
                    ((GifDrawable)teacher.getDrawable()).start();
                    ((GifDrawable)student.getDrawable()).start();
                    //remainingNumber.setText(str+ QuestionCounter);
                    if(answers[indexes[QuestionCounter]].equals("no")){
                        communications++;
                        student.startAnimation(animRight);

                    }
                    else{
                        communications--;
                        student.startAnimation(animLeft);}
                    QuestionCounter--;
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
                student.setX(student.getX()+275);

                //QuestionCounter--;
                //remainingNumber.setText("Вопросов осталось: "+ QuestionCounter);

                Handler handler = new Handler();
                Log.e("info", "ANIMATION END "+student.getX()+" "+teacher.getX());
                if(student.getX()>1000){
                    alertWin.show();
                    ((GifDrawable)gifImageView.getDrawable()).stop();
                    ((GifDrawable)teacher.getDrawable()).stop();
                    ((GifDrawable)student.getDrawable()).stop();
                }

                else {
                    if(QuestionCounter==-1){
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

                                    ((GifDrawable)gifImageView.getDrawable()).start();
                                    ((GifDrawable)teacher.getDrawable()).start();
                                    ((GifDrawable)student.getDrawable()).start();
                                    //remainingNumber.setText(str+ QuestionCounter);
                                    if(answers[indexes[QuestionCounter]].equals("yes"))
                                        student.startAnimation(animRight);
                                    else
                                        student.startAnimation(animLeft);
                                    QuestionCounter--;
                                    dialog.cancel();
                                }).setNegativeButton("Нет", (dialog, id)->{

                                    ((GifDrawable)gifImageView.getDrawable()).start();
                                    ((GifDrawable)teacher.getDrawable()).start();
                                    ((GifDrawable)student.getDrawable()).start();
                                    //remainingNumber.setText(str+ QuestionCounter);
                                    if(answers[indexes[QuestionCounter]].equals("no"))
                                        student.startAnimation(animRight);
                                    else
                                        student.startAnimation(animLeft);
                                    QuestionCounter--;
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
                student.setX(student.getX() - 200);

                //QuestionCounter--;
                //remainingNumber.setText(str+ QuestionCounter);


                if (QuestionCounter == -1){
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

                            ((GifDrawable)gifImageView.getDrawable()).start();
                            ((GifDrawable)teacher.getDrawable()).start();
                            ((GifDrawable)student.getDrawable()).start();

                            //remainingNumber.setText(str+ QuestionCounter);
                            if (answers[indexes[QuestionCounter]].equals("yes"))
                                student.startAnimation(animRight);
                            else
                                student.startAnimation(animLeft);
                            QuestionCounter--;
                            dialog.cancel();
                        }).setNegativeButton("Нет", (dialog, id) -> {

                            ((GifDrawable)gifImageView.getDrawable()).start();
                            ((GifDrawable)teacher.getDrawable()).start();
                            ((GifDrawable)student.getDrawable()).start();

                            //remainingNumber.setText(str+ QuestionCounter);
                            if (answers[indexes[QuestionCounter]].equals("no"))
                                student.startAnimation(animRight);
                            else
                                student.startAnimation(animLeft);
                            QuestionCounter--;
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
    String readFile() {
        String resultString = "";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("data.txt")));
            String str = "";

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.e("LOG_TAG", str+"in the game");
                resultString+=str;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

}