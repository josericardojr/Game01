package com.josericardojunior.game01;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements
        View.OnClickListener {

    ImageView btnPlay;
    ImageView btnHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (ImageView)
                findViewById(R.id.btnPlayGame);
        btnHighScore = (ImageView)
                findViewById(R.id.btnHighScore);
        btnPlay.setOnClickListener(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }

    @Override
    public void onClick(View v) {

        if (v == btnPlay){
            Intent intent = new Intent(MainActivity.this,
                    MainGame.class);

            startActivity(intent);
        }

    }
}
