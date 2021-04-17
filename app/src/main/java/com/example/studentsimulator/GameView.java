package com.example.studentsimulator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private Paint paint;
    private boolean isPlaying;
    private int screenX;
    private BackGround backGround1, backGround2;

    private float screenRatioX;

    public GameView(Context context, int screenX, int screenY){
        super(context);

        this.screenX = screenX;


        screenRatioX = 1920f / screenX;

        backGround1 = new BackGround(screenX, screenY, getResources());
        backGround2 = new BackGround(screenX, screenY, getResources());



        backGround2.x = screenX;
        paint = new Paint();

    }

    @Override
    public void run() {
        while (isPlaying) {

            update ();
            draw ();
            sleep ();

        }
    }
    private void update(){
        backGround1.x -= 10* screenRatioX;
        backGround2.x -= 10* screenRatioX;

        if (backGround1.x + backGround1.background.getWidth() < 0) {
            backGround1.x = screenX;
        }

        if (backGround2.x + backGround2.background.getWidth() < 0) {
            backGround2.x = screenX;
        }

    }
    private void draw(){
        if (getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backGround1.background, backGround1.x, backGround1.y, paint);
            canvas.drawBitmap(backGround2.background, backGround2.x, backGround2.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    private void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume () {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause () {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
