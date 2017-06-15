package com.josericardojunior.game01;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by josericardo on 05/06/17.
 */

public class MainGame extends Activity {

    GameLoop gameLoop = null;
    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gameLoop = new GameLoop(this, metrics);
        setContentView(gameLoop);
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameLoop.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

        gameLoop.onStop();
    }
}
