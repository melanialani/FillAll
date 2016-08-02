package com.application.melanialani.fillall;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean[]       isStageLocked;
    private ImageView[]     stageView;
    private DatabaseHelper  db;

    public MediaPlayer      player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // initialize database
        db = new DatabaseHelper(MainActivity.this);
        // test coins database
        db.setCoins(100);
        Log.d("COINS", String.valueOf(db.getCoins()));

        // play theme song 3
        playSound(3);

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

        // play tombol
        playSound(1);

        // initialize STAGELOCK
        isStageLocked = new boolean[4];
        isStageLocked[0] = true;
        isStageLocked[1] = false;
        isStageLocked[2] = true;
        isStageLocked[3] = true;

        stageView = new ImageView[4];
        stageView[1] = (ImageView) findViewById(R.id.stage01);
        stageView[2] = (ImageView) findViewById(R.id.stage02);
        stageView[3] = (ImageView) findViewById(R.id.stage03);

        //STAGE01
        if ( isStageLocked[1] == false ) { stageView[1].setImageResource(R.drawable.stage_1); }
        else { stageView[1].setImageResource(R.drawable.stage_1_closed); }

        //STAGE02
        if ( isStageLocked[2] == false ) { stageView[2].setImageResource(R.drawable.stage_2); }
        else { stageView[2].setImageResource(R.drawable.stage_2_closed); }

        //STAGE03
        if ( isStageLocked[3] == false ) { stageView[3].setImageResource(R.drawable.stage_3); }
        else { stageView[3].setImageResource(R.drawable.stage_3_closed); }
    }

    public void onStage1(View v){
        playSound(2); // play tut
        gotoGameActivity(1);
    }

    public void onStage2(View v){
        if (!isStageLocked[2]) {
            playSound(2); // play tut
            gotoGameActivity(2);
        }
    }

    public void onStage3(View v){
        if (!isStageLocked[3]) {
            playSound(2); // play tut
            gotoGameActivity(3);
        }
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
        playSound(1);//play tombol
        startActivity(characterIntent);
    }

    public void playSound(int arg){
        try{
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }

        }catch(Exception e){
        }
        if(arg==1){
            player = MediaPlayer.create(this, R.raw.tombol);
        }
        else if(arg==2){
            player= MediaPlayer.create(this, R.raw.tut);
        }
        else if(arg==3){
            player= MediaPlayer.create(this, R.raw.themesong3);
        }
        player.setLooping(false); // Set looping
        player.start();
    }


}
