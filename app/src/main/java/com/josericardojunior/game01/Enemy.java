package com.josericardojunior.game01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by josericardo on 12/06/17.
 */

public class Enemy extends GameEntity {

    Bitmap bitmap;
    int screenX;
    int screenY;

    static final float MAX_SPEED = 300;
    static final float MIN_SPEED = 100;

    public Enemy(int x, int y, int screenX, int screenY, Context context){
        super(x, y);

        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.enemy);

        this.screenX = screenX;
        this.screenY = screenY;

        reset();
    }

    @Override
    public void update(float elapsedTime) {
        super.update(elapsedTime);

        x -= getSpeed() * elapsedTime;

        if ((x + bitmap.getWidth() / 3) < 0 ){
            reset();
        }
    }

    public void desenha(Canvas canvas){
        canvas.drawBitmap(bitmap, new Rect(0, 0, 128, 126),
                new Rect(x, y, x + 128 * 2, y + 126 * 2), null);
    }

    public void reset(){

        x = screenX + 100;
        y = (int)((float)Math.random() * (float)(screenY - bitmap.getHeight()));

        float speed = MIN_SPEED +
                ((float)Math.random() * (MAX_SPEED - MIN_SPEED));

        setSpeed(speed);
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth() / 3;
    }
}
