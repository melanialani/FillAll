package com.application.melanialani.fillall;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
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
    private Handler         handler;

    // tampilan Karakter
    private ImageView       tampilanKarakter;

    public MediaPlayer      player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // initialize database
        db = new DatabaseHelper(MainActivity.this);

        // if dbVersion = 1, update it!
        if (db.getDBVersion() == 1){
            db.updateDBVersion(); // update dbversion to 2
        }


        // show splash screen for 3 seconds
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // go to main menu after 3s
                setContentView(R.layout.activity_main);
            }
        }, 3000);
    }

    public void onPlay(View v){
        // play tombol
        playSound(1);

        // kill handler
        handler.removeCallbacksAndMessages(null);

        loadStageMenu();
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

    public void gotoMainMenu(View v){
        setContentView(R.layout.activity_main);
    }

    private void loadStageMenu(){
        setContentView(R.layout.menu_stage);

        // tampilan Karakter
        tampilanKarakter = (ImageView) findViewById(R.id.karakter);
        if (db.getChosenCharacter().equalsIgnoreCase("RED")) {tampilanKarakter.setImageResource(R.drawable.red_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("PUR")) {tampilanKarakter.setImageResource(R.drawable.pur_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("PIKA")) {tampilanKarakter.setImageResource(R.drawable.pika_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("OCE")) {tampilanKarakter.setImageResource(R.drawable.oce_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("LEMON")) {tampilanKarakter.setImageResource(R.drawable.lemon_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("GEEKS")) {tampilanKarakter.setImageResource(R.drawable.geeks_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("FLATRE")) {tampilanKarakter.setImageResource(R.drawable.flatre_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("BULB")) {tampilanKarakter.setImageResource(R.drawable.bulb_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("BLACK")) {tampilanKarakter.setImageResource(R.drawable.black_ani0);}
        else if (db.getChosenCharacter().equalsIgnoreCase("ATU")) {tampilanKarakter.setImageResource(R.drawable.atu_ani0);}

        checkStage();
    }

    public void playSound(int arg){
        try {
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }

        } catch(Exception e){
            Log.e("SOUND_ERR", e.toString());
        }

        if(arg==1){
            player = MediaPlayer.create(this, R.raw.tombol);
        } else if(arg==2){
            player= MediaPlayer.create(this, R.raw.tut);
        } else if(arg==3){
            player= MediaPlayer.create(this, R.raw.themesong3);
        }

        player.setLooping(false);
        player.start();
    }

    public void gotoCharacterActivity(View view) {
        // play tombol
        playSound(1);

        // kill handler
        handler.removeCallbacksAndMessages(null);

        Intent characterIntent = new Intent(MainActivity.this, CharacterActivity.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(characterIntent);
    }

    private void gotoGameActivity(int stage){
        // create intent game activity
        // also send parameter to game activity -> send which stage selected

        Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
        gameIntent.putExtra("stage", String.valueOf(stage));
        startActivityForResult(gameIntent, 79);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 79){
            if (resultCode == Activity.RESULT_OK){
                if (data.getBooleanExtra("reload", true)){
                    loadStageMenu();
                }
            }
        }
    }

    private void checkStage(){
        isStageLocked = new boolean[4];
        isStageLocked[0] = true;

        System.out.println("LAST LEVEL: " + db.getLastLevel());

        if (db.getLastLevel() < 6) {
            System.out.println("last stage: 1");
            isStageLocked[1] = false;
            isStageLocked[2] = true;
            isStageLocked[3] = true;
        } else if (db.getLastLevel() >= 6 && db.getLastLevel() < 11) {
            System.out.println("last stage: 2");
            isStageLocked[1] = false;
            isStageLocked[2] = false;
            isStageLocked[3] = true;
        } else if(db.getLastLevel() >= 11) {
            System.out.println("last stage: 3");
            isStageLocked[1] = false;
            isStageLocked[2] = false;
            isStageLocked[3] = false;
        }

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
}
