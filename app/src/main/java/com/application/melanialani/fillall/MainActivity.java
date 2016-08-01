package com.application.melanialani.fillall;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Data data;
    private boolean[] isStageLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // initialize data class
        data = new Data();

        // initialize STAGELOCK
        isStageLocked = new boolean[4];
        isStageLocked[0] = true;
        isStageLocked[1] = false;
        isStageLocked[2] = true;
        isStageLocked[3] = true;

        // show splash screen for 3 seconds
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // go to main menu after 3s
                setContentView(R.layout.activity_main);
            }
        }, 3000);
    }

    public void onPlay(View v){
        setContentView(R.layout.menu_stage);
    }

    public void onStage1(View v){
        gotoGameActivity(1);
    }

    public void onStage2(View v){
        gotoGameActivity(2);
    }

    public void onStage3(View v){
        gotoGameActivity(3);
    }

    private void gotoGameActivity(int stage){
        // create intent game activity
        // also send parameter to game activity -> send which stage selected

        Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
        gameIntent.putExtra("stage", String.valueOf(stage));
        startActivity(gameIntent);
    }

    public void gotoCharacterActivity(View view) {
        Intent characterIntent = new Intent(MainActivity.this, CharacterActivity.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(characterIntent);
    }

}
