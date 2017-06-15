package com.josericardojunior.game01;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josericardo on 09/06/17.
 */

public class StarManager {

    int numStars;
    DisplayMetrics metrics;
    Point [] stars;
    float [] speeds;
    float minSpeed, maxSpeed;
    Paint paint;

    public StarManager(int numStars, float minSpeed, float maxSpeed,
                       DisplayMetrics metrics){
        this.numStars = numStars;
        this.metrics = metrics;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;

        stars = new Point[this.numStars];
        speeds = new float[this.numStars];
        paint = new Paint();
        paint.setColor(Color.WHITE);


        generateStars();
    }

    private void generateStars(){

        for (int i = 0; i < numStars; i++){

            speeds[i] = minSpeed + (
                    (float) Math.random() * (maxSpeed - minSpeed));

            int y = (int) (Math.random() * (float) metrics.heightPixels);

            int x = (int) (Math.random() * (float) metrics.widthPixels);
            stars[i] = new Point(x, y);
        }
    }

    public void update(float timeElapsed){

        for (int i = 0; i < numStars; i++){
            stars[i].x -= speeds[i] * timeElapsed;

            if (stars[i].x <= 0){
                stars[i].x = metrics.widthPixels + 50;
            }
        }
    }

    public void desenha(Canvas canvas){

        for (int i = 0; i < numStars; i++){
            canvas.drawCircle(
                    (float)stars[i].x, (float)stars[i].y, 2.0f, paint);
        }

    }
}
