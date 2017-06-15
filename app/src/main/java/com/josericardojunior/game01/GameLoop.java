package com.josericardojunior.game01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by josericardo on 05/06/17.
 */

public class GameLoop extends SurfaceView implements Runnable,
        View.OnTouchListener {

    final static int MAX_ENEMIES = 5;

    Thread gameLoop = null;
    volatile boolean finished;
    Player player;
    Enemy [] enemies;
    DisplayMetrics displayMetrics;
    int fps = 60;
    StarManager starManager;

    SurfaceHolder surfaceHolder = null;

    public GameLoop(Context context, DisplayMetrics displayMetrics){
        super(context);


        surfaceHolder = getHolder();

        this.displayMetrics = displayMetrics;

        player = new Player(75, 50,
                displayMetrics.heightPixels, context);

        enemies = new Enemy[MAX_ENEMIES];
        for (int i = 0; i < MAX_ENEMIES; i++){

            enemies[i] = new Enemy(displayMetrics.widthPixels + 100,
                    0, displayMetrics.widthPixels, displayMetrics.heightPixels, context);
        }

        starManager = new StarManager(300, 100, 300, this.displayMetrics);

        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        if (event.getAction()  == MotionEvent.ACTION_DOWN){
            player.setBoosting();
        } else if (event.getAction() == MotionEvent.ACTION_UP){
            player.stopBoosting();
        }

        return true;
    }

    @Override
    public void run() {

        while (finished != true){

            if (!surfaceHolder.getSurface().isValid())
                continue;

            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            update();
            desenha(canvas);

            surfaceHolder.unlockCanvasAndPost(canvas);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }

    private void update(){

        player.update(0.1f);
        starManager.update(0.1f);

        for (int i = 0; i < MAX_ENEMIES; i++){
            enemies[i].update(0.1f);

            if (enemies[i].Collided(player.getCollisionBox()))
                enemies[i].reset();
        }
    }

    private void desenha(Canvas canvas){
        starManager.desenha(canvas);
        player.desenha(canvas);

        for (int i = 0; i < MAX_ENEMIES; i++){
            enemies[i].desenha(canvas);
        }
    }

    public void onResume(){
        gameLoop = new Thread(this);
        finished = false;

        gameLoop.start();
    }

    public void onStop(){
        finished = true;

        try {
            gameLoop.join();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
