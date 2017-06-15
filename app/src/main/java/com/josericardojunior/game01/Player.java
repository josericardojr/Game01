package com.josericardojunior.game01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by josericardo on 05/06/17.
 */

public class Player extends GameEntity {

    final static float MIN_SPEED = -300;
    final static float MAX_SPEED = 200;


    private int maxY;
    protected Bitmap bitmap;

    private boolean isBoosting = false;

    public void setBoosting(){ isBoosting = true; }
    public void stopBoosting(){ isBoosting = false; }

    public Player(int x, int y, int maxY, Context context){
        super(x, y);

        this.maxY = maxY;

        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.player);
    }

    public void desenha(Canvas canvas){

        canvas.drawBitmap(bitmap, new Rect(0, 0, 128, 126),
                new Rect(x, y, x + 128, y + 126), null);

    }

    @Override
    public void update(float elapsedTime) {
        super.update(elapsedTime);

        //Log.d("Info", "Boost: " + isBoosting + " Vel. " + speed);
        if (isBoosting) setSpeed(getSpeed() - 15);
        else setSpeed(getSpeed() + 8);


        if (getSpeed() < MIN_SPEED) setSpeed(MIN_SPEED);
        if (getSpeed() > MAX_SPEED) setSpeed(MAX_SPEED);

        y += getSpeed() * elapsedTime;

        if (y < 0){
            y = 0;
            setSpeed(0);
        }


        //Log.d("Info", "y: " + y + " max y " + maxY);
        if (y + bitmap.getHeight() > maxY) {
            y = maxY - bitmap.getHeight();
            setSpeed(0);
        }

    }

    @Override
    public int getWidth() {
        return bitmap.getWidth() / 8;
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }
}
