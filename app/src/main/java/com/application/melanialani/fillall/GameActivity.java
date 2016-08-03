package com.application.melanialani.fillall;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GameActivity extends AppCompatActivity {

    //region variables
    private Swipe           swipe;

    private String[][]      maps;
    private ImageView[][]   mapspict;

    private boolean[]       isLocked;
    private ImageView[]     levelView;

    private int             lebar, tinggi, posX, posY, posX2, posY2, posLevel, fromStage;
    private boolean         player2, isReverse, isMoving;

    private Handler         handler;
    private Data            data;
    private DatabaseHelper  db;
    private MediaPlayer     player;

    public TextView         tvCoins;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // connect to database
        db = new DatabaseHelper(GameActivity.this);

        // initiate LOCKLEVEL variables
        initializeLockLevel();

        // check stage
        Intent callerIntent = getIntent();
        if (callerIntent.getStringExtra("stage").equals("1")) {
            fromStage = 1;
            setContentView(R.layout.menu_stage1);
            checkLockLevelStage1();
        } else if (callerIntent.getStringExtra("stage").equals("2")) {
            fromStage = 2;
            setContentView(R.layout.menu_stage2);
            checkLockLevelStage2();
        } else if (callerIntent.getStringExtra("stage").equals("3")) {
            fromStage = 3;
            setContentView(R.layout.menu_stage3);
            checkLockLevelStage3();
        }

        // initiate variables;
        maps = new String[tinggi][lebar];
        mapspict = new ImageView[tinggi][lebar];
        data = new Data();
        posLevel = 0;

        // initiate swipe listener
        swipeDetector();
    }

    private void move(String action) {
        // play sound srut
        playSound(4);

        if (action.equalsIgnoreCase("RIGHT")){ // x same, y plus
            int posYwanted = posY + 1;
            for (int y = posYwanted; y < lebar; y++){
                try {
                    if (maps[posX][y].equals("0")){
                        maps[posX][y] = "1";
                        posY = y;
                        //mapspict[posX][y].setImageResource(R.drawable.all);
                        setCharacterPicture(posX, posY, action);
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
            }

            if (player2){
                int posY2wanted = posY2 + 1;
                for (int y = posY2wanted; y < lebar; y++){
                    try {
                        if (maps[posX2][y].equals("0")){
                            maps[posX2][y] = "1";
                            posY2 = y;
                            //mapspict[posX2][y].setImageResource(R.drawable.flatre_ani0);
                            setCharacterPicture(posX2, posY2, action);
                        } else
                            break;
                    } catch (Exception ex){
                        Log.e("move_ERR", ex.toString());
                    }
                }
            }

        }

        else if (action.equalsIgnoreCase("LEFT")){ // x same, y minus
            int posYwanted = posY - 1;
            for (int y = posYwanted; y >= 0; y--){
                try {
                    if (maps[posX][y].equals("0")){
                        maps[posX][y] = "1";
                        posY = y;
                        //mapspict[posX][y].setImageResource(R.drawable.flatre_ani0);
                        setCharacterPicture(posX, posY, action);
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
            }

            if (player2){
                int posY2wanted = posY2 - 1;
                for (int y = posY2wanted; y >= 0; y--){
                    try {
                        if (maps[posX2][y].equals("0")){
                            maps[posX2][y] = "1";
                            posY2 = y;
                            //mapspict[posX2][y].setImageResource(R.drawable.flatre_ani0);
                            setCharacterPicture(posX2, posY2, action);
                        } else
                            break;
                    } catch (Exception ex){
                        Log.e("move_ERR", ex.toString());
                    }
                }
            }
        }

        else if (action.equalsIgnoreCase("DOWN")){ // x plus, y same
            int posXwanted = posX + 1;
            for (int x = posXwanted; x < tinggi; x++){
                try {
                    if (maps[x][posY].equals("0")){
                        maps[x][posY] = "1";
                        posX = x;
                        //mapspict[x][posY].setImageResource(R.drawable.flatre_ani0);
                        setCharacterPicture(posX, posY, action);
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
            }

            if (player2){
                int posX2wanted = posX2 + 1;
                for (int x = posX2wanted; x < tinggi; x++){
                    try {
                        if (maps[x][posY2].equals("0")){
                            maps[x][posY2] = "1";
                            posX2 = x;
                            //mapspict[x][posY2].setImageResource(R.drawable.flatre_ani0);
                            setCharacterPicture(posX2, posY2, action);
                        } else
                            break;
                    } catch (Exception ex){
                        Log.e("move_ERR", ex.toString());
                    }
                }
            }
        }

        else if (action.equalsIgnoreCase("UP")){ // x minus, y same
            int posXwanted = posX - 1;
            for (int x = posXwanted; x >= 0; x--){
                try {
                    if (maps[x][posY].equals("0")){
                        maps[x][posY] = "1";
                        posX = x;
                        //mapspict[x][posY].setImageResource(R.drawable.flatre_ani0);
                        setCharacterPicture(posX, posY, action);
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
            }

            if (player2){
                int posX2wanted = posX2 - 1;
                for (int x = posX2wanted; x >= 0; x--){
                    try {
                        if (maps[x][posY2].equals("0")){
                            maps[x][posY2] = "1";
                            posX2 = x;
                            //mapspict[x][posY2].setImageResource(R.drawable.flatre_ani0);
                            setCharacterPicture(posX2, posY2, action);
                        } else
                            break;
                    } catch (Exception ex){
                        Log.e("move_ERR", ex.toString());
                    }
                }
            }
        }

        checkFinish();
    }

    //region win & after
    private void checkFinish(){
        if(isWin()) {
            System.out.println("WIN LEVEL: " + data.getPosPlay());
            unlockLevel();

            // wait for 1 second & then show win message
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // show win screen
                    setContentView(R.layout.win_message);
                    ImageView ivCoin = (ImageView) findViewById(R.id.ivCoin);
                    if ((data.getPosPlay() + 1) <= 5) {
                        ivCoin.setImageResource(R.drawable.coin_300);

                        // update database
                        int totalCoin = db.getCoins() + 300;
                        db.setCoins(totalCoin);
                    } else if ((data.getPosPlay() + 1) > 5 && (data.getPosPlay() + 1) < 11) {
                        ivCoin.setImageResource(R.drawable.coin_350);

                        // update database
                        int totalCoin = db.getCoins() + 350;
                        db.setCoins(totalCoin);
                    } else if ((data.getPosPlay() + 1) >= 11) {
                        ivCoin.setImageResource(R.drawable.coin_400);

                        // update database
                        int totalCoin = db.getCoins() + 400;
                        db.setCoins(totalCoin);
                    }

                    // show win screen for 4 second and then go to stage menu
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (db.getLastLevel() == 6 || db.getLastLevel() == 11){
                                // go to stage menu
                                gotoStageMenu();
                            } else if (fromStage == 1) {
                                setContentView(R.layout.menu_stage1);
                                checkLockLevelStage1();
                            } else if (fromStage == 2) {
                                setContentView(R.layout.menu_stage2);
                                checkLockLevelStage2();
                            } else if (fromStage == 3) {
                                setContentView(R.layout.menu_stage3);
                                checkLockLevelStage3();
                            }
                        }
                    }, 4000);
                }
            }, 1000);
        }
    }

    private boolean isWin() {
        boolean cek = true;

        // check if all squares are filled
        for (int i = 0; i < tinggi; i++) {
            for (int j = 0; j < lebar; j++) {
                if (maps[i][j].equals("0"))
                    cek = false;
            }
        }

        return cek;
    }

    private void gotoStageMenu(){
        Intent intentReturn = new Intent();

        // set properties to return
        intentReturn.putExtra("reload", true);

        // return intent
        setResult(Activity.RESULT_OK, intentReturn);

        // close this activity
        this.finish();
    }
    //endregion

    //region initialize lock level & unlock level & check locked levels in stages
    private void initializeLockLevel(){
        isLocked = new boolean[16];
        levelChecked();

        levelView = new ImageView[16];
        levelView[1] = (ImageView) findViewById(R.id.level01);
        levelView[2] = (ImageView) findViewById(R.id.level02);
        levelView[3] = (ImageView) findViewById(R.id.level03);
        levelView[4] = (ImageView) findViewById(R.id.level04);
        levelView[5] = (ImageView) findViewById(R.id.level05);
        levelView[6] = (ImageView) findViewById(R.id.level06);
        levelView[7] = (ImageView) findViewById(R.id.level07);
        levelView[8] = (ImageView) findViewById(R.id.level08);
        levelView[9] = (ImageView) findViewById(R.id.level09);
        levelView[10] = (ImageView) findViewById(R.id.level10);
        levelView[11] = (ImageView) findViewById(R.id.level11);
        levelView[12] = (ImageView) findViewById(R.id.level12);
        levelView[13] = (ImageView) findViewById(R.id.level13);
        levelView[14] = (ImageView) findViewById(R.id.level14);
        levelView[15] = (ImageView) findViewById(R.id.level15);
    }

    private void checkLockLevelStage1(){
        levelView[1] = (ImageView) findViewById(R.id.level01);
        levelView[2] = (ImageView) findViewById(R.id.level02);
        levelView[3] = (ImageView) findViewById(R.id.level03);
        levelView[4] = (ImageView) findViewById(R.id.level04);
        levelView[5] = (ImageView) findViewById(R.id.level05);

        //LEVEL01
        if ( isLocked[1] == false ) {
            levelView[1].setImageResource(R.drawable.level_1);
        }
        else {
            levelView[1].setImageResource(R.drawable.level_1_closed);
        }

        //LEVEL02
        if ( isLocked[2] == false ) {
            levelView[2].setImageResource(R.drawable.level_2);
        }
        else {
            levelView[2].setImageResource(R.drawable.level_2_closed);
        }

        //LEVEL03
        if ( isLocked[3] == false ) {
            levelView[3].setImageResource(R.drawable.level_3);
        }
        else {
            levelView[3].setImageResource(R.drawable.level_3_closed);
        }

        //LEVEL04
        if ( isLocked[4] == false ) {
            levelView[4].setImageResource(R.drawable.level_4);
        }
        else {
            levelView[4].setImageResource(R.drawable.level_4_closed);
        }

        //LEVEL05
        if ( isLocked[5] == false ) {
            levelView[5].setImageResource(R.drawable.level_5);
        }
        else {
            levelView[5].setImageResource(R.drawable.level_5_closed);
        }
    }

    private void checkLockLevelStage2(){
        levelView[6] = (ImageView) findViewById(R.id.level06);
        levelView[7] = (ImageView) findViewById(R.id.level07);
        levelView[8] = (ImageView) findViewById(R.id.level08);
        levelView[9] = (ImageView) findViewById(R.id.level09);
        levelView[10] = (ImageView) findViewById(R.id.level10);

        //LEVEL06
        if ( isLocked[6] == false ) {
            levelView[6].setImageResource(R.drawable.level_6);
        }
        else {
            levelView[6].setImageResource(R.drawable.level_6_closed);
        }

        //LEVEL07
        if ( isLocked[7] == false ) {
            levelView[7].setImageResource(R.drawable.level_7);
        }
        else {
            levelView[7].setImageResource(R.drawable.level_7_closed);
        }

        //LEVEL08
        if ( isLocked[8] == false ) {
            levelView[8].setImageResource(R.drawable.level_8);
        }
        else {
            levelView[8].setImageResource(R.drawable.level_8_closed);
        }

        //LEVEL09
        if ( isLocked[9] == false ) {
            levelView[9].setImageResource(R.drawable.level_9);
        }
        else {
            levelView[9].setImageResource(R.drawable.level_9_closed);
        }

        //LEVEL10
        if ( isLocked[10] == false ) {
            levelView[10].setImageResource(R.drawable.level_10);
        }
        else {
            levelView[10].setImageResource(R.drawable.level_10_closed);
        }
    }

    private void checkLockLevelStage3(){
        levelView[11] = (ImageView) findViewById(R.id.level11);
        levelView[12] = (ImageView) findViewById(R.id.level12);
        levelView[13] = (ImageView) findViewById(R.id.level13);
        levelView[14] = (ImageView) findViewById(R.id.level14);
        levelView[15] = (ImageView) findViewById(R.id.level15);

        //LEVEL11
        if ( isLocked[11] == false ) {
            levelView[11].setImageResource(R.drawable.level_11);
        }
        else {
            levelView[11].setImageResource(R.drawable.level_11_closed);
        }

        //LEVEL12
        if ( isLocked[12] == false ) {
            levelView[12].setImageResource(R.drawable.level_12);
        }
        else {
            levelView[12].setImageResource(R.drawable.level_12_closed);
        }

        //LEVEL13
        if ( isLocked[13] == false ) {
            levelView[13].setImageResource(R.drawable.level_13);
        }
        else {
            levelView[13].setImageResource(R.drawable.level_13_closed);
        }

        //LEVEL14
        if ( isLocked[14] == false ) {
            levelView[14].setImageResource(R.drawable.level_14);
        }
        else {
            levelView[14].setImageResource(R.drawable.level_14_closed);
        }

        //LEVEL15
        if ( isLocked[15] == false ) {
            levelView[15].setImageResource(R.drawable.level_15);
        }
        else {
            levelView[15].setImageResource(R.drawable.level_15_closed);
        }
    }

    private void levelChecked() {
        // lock all level as default
        for (int i = 0; i < isLocked.length; i++) {
            isLocked[i] = true;
        }

        // check unlocked levels stored in database & unlock all before it
        for (int j = 0; j <= db.getLastLevel(); j++) {
            System.out.println("unlocking level " + j);
            isLocked[j] = false;
        }
    }

    private void unlockLevel(){
        if (isLocked[data.getPosPlay() + 1]) {
            System.out.println("UNLOCK LEVEL: " + (data.getPosPlay() + 1));

            //isLocked[data.getPosPlay() + 1] = false;

            // update last level in database
            db.setLastLevel(data.getPosPlay() + 1);

            // update last stage in database
            if (db.getLastLevel() >= 6 && db.getLastLevel() < 11)
                db.setLastStage(2);
            else if (db.getLastLevel() >= 11)
                db.setLastStage(3);
            else
                db.setLastStage(1);

            levelChecked();
        }
    }
    //endregion

    private void setCharacterPicture(final int x, final int y, final String move){
        if (db.getChosenCharacter().equalsIgnoreCase("RED")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_redRight(0), data.getResId_redRight(1),
                        data.getResId_redRight(2), data.getResId_redRight(3), data.getResId_redRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_redLeft(0), data.getResId_redLeft(1),
                        data.getResId_redLeft(2), data.getResId_redLeft(3), data.getResId_redLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_redUp(0), data.getResId_redUp(1),
                        data.getResId_redUp(2), data.getResId_redUp(3), data.getResId_redUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_redDown(0), data.getResId_redDown(1),
                        data.getResId_redDown(2), data.getResId_redDown(3), data.getResId_redDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("PUR")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_purRight(0), data.getResId_purRight(1),
                        data.getResId_purRight(2), data.getResId_purRight(3), data.getResId_purRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_purLeft(0), data.getResId_purLeft(1),
                        data.getResId_purLeft(2), data.getResId_purLeft(3), data.getResId_purLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_purUp(0), data.getResId_purUp(1),
                        data.getResId_purUp(2), data.getResId_purUp(3), data.getResId_purUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_purDown(0), data.getResId_purDown(1),
                        data.getResId_purDown(2), data.getResId_purDown(3), data.getResId_purDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("PIKA")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_pikaRight(0), data.getResId_pikaRight(1),
                        data.getResId_pikaRight(2), data.getResId_pikaRight(3), data.getResId_pikaRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_pikaLeft(0), data.getResId_pikaLeft(1),
                        data.getResId_pikaLeft(2), data.getResId_pikaLeft(3), data.getResId_pikaLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_pikaUp(0), data.getResId_pikaUp(1),
                        data.getResId_pikaUp(2), data.getResId_pikaUp(3), data.getResId_pikaUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_pikaDown(0), data.getResId_pikaDown(1),
                        data.getResId_pikaDown(2), data.getResId_pikaDown(3), data.getResId_pikaDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("OCE")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_oceRight(0), data.getResId_oceRight(1),
                        data.getResId_oceRight(2), data.getResId_oceRight(3), data.getResId_oceRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_oceLeft(0), data.getResId_oceLeft(1),
                        data.getResId_oceLeft(2), data.getResId_oceLeft(3), data.getResId_oceLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_oceUp(0), data.getResId_oceUp(1),
                        data.getResId_oceUp(2), data.getResId_oceUp(3), data.getResId_oceUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_oceDown(0), data.getResId_oceDown(1),
                        data.getResId_oceDown(2), data.getResId_oceDown(3), data.getResId_oceDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("LEMON")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_lemonRight(0), data.getResId_lemonRight(1),
                        data.getResId_lemonRight(2), data.getResId_lemonRight(3), data.getResId_lemonRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_lemonLeft(0), data.getResId_lemonLeft(1),
                        data.getResId_lemonLeft(2), data.getResId_lemonLeft(3), data.getResId_lemonLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_lemonUp(0), data.getResId_lemonUp(1),
                        data.getResId_lemonUp(2), data.getResId_lemonUp(3), data.getResId_lemonUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_lemonDown(0), data.getResId_lemonDown(1),
                        data.getResId_lemonDown(2), data.getResId_lemonDown(3), data.getResId_lemonDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("GEEKS")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_geeksRight(0), data.getResId_geeksRight(1),
                        data.getResId_geeksRight(2), data.getResId_geeksRight(3), data.getResId_geeksRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_geeksLeft(0), data.getResId_geeksLeft(1),
                        data.getResId_geeksLeft(2), data.getResId_geeksLeft(3), data.getResId_geeksLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_geeksUp(0), data.getResId_geeksUp(1),
                        data.getResId_geeksUp(2), data.getResId_geeksUp(3), data.getResId_geeksUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_geeksDown(0), data.getResId_geeksDown(1),
                        data.getResId_geeksDown(2), data.getResId_geeksDown(3), data.getResId_geeksDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("FLATRE")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_flatreRight(0), data.getResId_flatreRight(1),
                        data.getResId_flatreRight(2), data.getResId_flatreRight(3), data.getResId_flatreRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_flatreLeft(0), data.getResId_flatreLeft(1),
                        data.getResId_flatreLeft(2), data.getResId_flatreLeft(3), data.getResId_flatreLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_flatreUp(0), data.getResId_flatreUp(1),
                        data.getResId_flatreUp(2), data.getResId_flatreUp(3), data.getResId_flatreUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_flatreDown(0), data.getResId_flatreDown(1),
                        data.getResId_flatreDown(2), data.getResId_flatreDown(3), data.getResId_flatreDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("BULB")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_bulbRight(0), data.getResId_bulbRight(1),
                        data.getResId_bulbRight(2), data.getResId_bulbRight(3), data.getResId_bulbRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_bulbLeft(0), data.getResId_bulbLeft(1),
                        data.getResId_bulbLeft(2), data.getResId_bulbLeft(3), data.getResId_bulbLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_bulbUp(0), data.getResId_bulbUp(1),
                        data.getResId_bulbUp(2), data.getResId_bulbUp(3), data.getResId_bulbUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_bulbDown(0), data.getResId_bulbDown(1),
                        data.getResId_bulbDown(2), data.getResId_bulbDown(3), data.getResId_bulbDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("BLACK")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_blackRight(0), data.getResId_blackRight(1),
                        data.getResId_blackRight(2), data.getResId_blackRight(3), data.getResId_blackRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_blackLeft(0), data.getResId_blackLeft(1),
                        data.getResId_blackLeft(2), data.getResId_blackLeft(3), data.getResId_blackLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_blackUp(0), data.getResId_blackUp(1),
                        data.getResId_blackUp(2), data.getResId_blackUp(3), data.getResId_blackUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_blackDown(0), data.getResId_blackDown(1),
                        data.getResId_blackDown(2), data.getResId_blackDown(3), data.getResId_blackDown(4));
            }
        } else if (db.getChosenCharacter().equalsIgnoreCase("ATU")) {
            if (move.equalsIgnoreCase("RIGHT")){
                changePicture(x, y, data.getResId_atuRight(0), data.getResId_atuRight(1),
                        data.getResId_atuRight(2), data.getResId_atuRight(3), data.getResId_atuRight(4));
            } else if (move.equalsIgnoreCase("LEFT")){
                changePicture(x, y, data.getResId_atuLeft(0), data.getResId_atuLeft(1),
                        data.getResId_atuLeft(2), data.getResId_atuLeft(3), data.getResId_atuLeft(4));
            } else if (move.equalsIgnoreCase("UP")){
                changePicture(x, y, data.getResId_atuUp(0), data.getResId_atuUp(1),
                        data.getResId_atuUp(2), data.getResId_atuUp(3), data.getResId_atuUp(4));
            } else if (move.equalsIgnoreCase("DOWN")){
                changePicture(x, y, data.getResId_atuDown(0), data.getResId_atuDown(1),
                        data.getResId_atuDown(2), data.getResId_atuDown(3), data.getResId_atuDown(4));
            }
        }
    }

    //region animation changePicture -> handler here
    private void changePicture(final int x, final int y, final int resId0, final int resId1,
                               final int resId2, final int resId3, final int resId4){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mapspict[x][y].setImageResource(resId1);

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mapspict[x][y].setImageResource(resId2);

                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mapspict[x][y].setImageResource(resId3);

                                handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mapspict[x][y].setImageResource(resId4);

                                        handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                mapspict[x][y].setImageResource(resId3);

                                                handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        mapspict[x][y].setImageResource(resId2);

                                                        handler = new Handler();
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                mapspict[x][y].setImageResource(resId1);

                                                                handler = new Handler();
                                                                handler.postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        mapspict[x][y].setImageResource(resId0);

                                                                        isMoving = false;
                                                                    }
                                                                }, 0);
                                                            }
                                                        }, 0);
                                                    }
                                                }, 0);
                                            }
                                        }, 0);
                                    }
                                }, 0);
                            }
                        }, 0);
                    }
                }, 0);
            }
        }, 0);

    }
    //endregion

    //region set level (starting new game level)
    public void level1(View v){
        // get data from data
        if ( isLocked[1] == false ) {
            playSound(2);
            data.setLevel(1);
            initiateNewMap();
        }
    }

    public void level2(View v){
        // get data from data
        if ( isLocked[2] == false ) {
            playSound(2);
            data.setLevel(2);
            initiateNewMap();
        }
    }

    public void level3(View v){
        // get data from data
        if ( isLocked[3] == false ) {
            playSound(2);
            data.setLevel(3);
            initiateNewMap();
        }
    }

    public void level4(View v){
        // get data from data
        if ( isLocked[4] == false ) {
            playSound(2);
            data.setLevel(4);
            initiateNewMap();
        }
    }

    public void level5(View v){
        // get data from data
        if ( isLocked[5] == false ) {
            playSound(2);
            data.setLevel(5);
            initiateNewMap();
        }
    }

    public void level6(View v){
        // get data from data
        if ( isLocked[6] == false ) {
            playSound(2);
            data.setLevel(6);
            initiateNewMap();
        }
    }

    public void level7(View v){
        // get data from data
        if ( isLocked[7] == false ) {
            playSound(2);
            data.setLevel(7);
            initiateNewMap();
        }
    }

    public void level8(View v){
        // get data from data
        if ( isLocked[8] == false ) {
            playSound(2);
            data.setLevel(8);
            initiateNewMap();
        }
    }

    public void level9(View v){
        // get data from data
        if ( isLocked[9] == false ) {
            playSound(2);
            data.setLevel(9);
            initiateNewMap();
        }
    }

    public void level10(View v){
        // get data from data
        if ( isLocked[10] == false ) {
            playSound(2);
            data.setLevel(10);
            initiateNewMap();
        }
    }

    public void level11(View v){
        // get data from data
        if ( isLocked[11] == false ) {
            playSound(2);
            data.setLevel(11);
            this.isReverse = true;
            initiateNewMap();
        }
    }

    public void level12(View v){
        // get data from data
        if ( isLocked[12] == false ) {
            playSound(2);
            data.setLevel(12);
            this.isReverse = true;
            initiateNewMap();
        }
    }

    public void level13(View v){
        // get data from data
        if ( isLocked[13] == false ) {
            playSound(2);
            data.setLevel(13);
            this.isReverse = true;
            initiateNewMap();
        }
    }

    public void level14(View v){
        // get data from data
        if ( isLocked[14] == false ) {
            playSound(2);
            data.setLevel(14);
            this.isReverse = true;
            initiateNewMap();
        }
    }

    public void level15(View v){
        // get data from data
        if ( isLocked[15] == false ) {
            playSound(2);
            data.setLevel(15);
            this.isReverse = true;
            initiateNewMap();
        }
    }
    //endregion

    public void reloadGame(View view){
        data.setLevel(data.getLevel());
        initiateNewMap();
    }

    private void initiateNewMap(){
        this.lebar = data.getLebar();
        this.tinggi = data.getTinggi();
        this.posX = data.getPosX();
        this.posY = data.getPosY();
        this.posX2 = data.getPosX2();
        this.posY2 = data.getPosY2();
        this.player2 = data.getPlayer2();

        this.maps = new String[tinggi][lebar];
        this.maps = data.getMap();

        this.mapspict = new ImageView[tinggi][lebar];

        // set layout & put each image view into array
        if (tinggi == 1 && lebar == 4) {
            setContentView(R.layout.map1x4);
            defineMap1x4();
        } else if (tinggi == 3 && lebar == 6) {
            setContentView(R.layout.map3x6);
            defineMap3x6();
        } else if (tinggi == 4 && lebar == 4) {
            setContentView(R.layout.map4x4);
            defineMap4x4();
        } else if (tinggi == 4 && lebar == 5) {
            setContentView(R.layout.map4x5);
            defineMap4x5();
        } else if (tinggi == 4 && lebar == 6) {
            setContentView(R.layout.map4x6);
            defineMap4x6();
        } else if (tinggi == 5 && lebar == 4) {
            setContentView(R.layout.map5x4);
            defineMap5x4();
        } else if (tinggi == 5 && lebar == 5) {
            setContentView(R.layout.map5x5);
            defineMap5x5();
        } else if (tinggi == 5 && lebar == 6) {
            setContentView(R.layout.map5x6);
            defineMap5x6();
        } else if (tinggi == 5 && lebar == 7) {
            setContentView(R.layout.map5x7);
            defineMap5x7();
        } else if (tinggi == 6 && lebar == 4) {
            setContentView(R.layout.map6x4);
            defineMap6x4();
        } else if (tinggi == 6 && lebar == 5) {
            setContentView(R.layout.map6x5);
            defineMap6x5();
        } else if (tinggi == 6 && lebar == 6) {
            setContentView(R.layout.map6x6);
            defineMap6x6();
        } else if (tinggi == 7 && lebar == 5) {
            setContentView(R.layout.map7x5);
            defineMap7x5();
        }

        // properly set image source in each image view
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    if (maps[x][y].equals("0")){
                        mapspict[x][y].setImageResource(R.drawable.blank);
                    } else if (maps[x][y].equals("1")){
                        //mapspict[x][y].setImageResource(R.drawable.flatre_ani0);
                        setCharacterPicture(x, y, "RIGHT");
                    } else if (maps[x][y].equals("#")){
                        mapspict[x][y].setImageResource(R.drawable.block);
                    }
                } catch (Exception ex){
                    Log.e("array_ERR", ex.toString());
                }
            }
        }
    }

    //region define imageview in map
    private void defineMap1x4(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);
    }

    private void defineMap3x6(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);
        mapspict[0][5] = (ImageView) findViewById(R.id.iv05);   mapspict[1][5] = (ImageView) findViewById(R.id.iv15);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);
        mapspict[2][5] = (ImageView) findViewById(R.id.iv25);
    }

    private void defineMap4x4(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
    }

    private void defineMap4x5(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][4] = (ImageView) findViewById(R.id.iv34);
    }

    private void defineMap4x6(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);
        mapspict[0][5] = (ImageView) findViewById(R.id.iv05);   mapspict[1][5] = (ImageView) findViewById(R.id.iv15);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][4] = (ImageView) findViewById(R.id.iv34);
        mapspict[2][5] = (ImageView) findViewById(R.id.iv25);   mapspict[3][5] = (ImageView) findViewById(R.id.iv35);
    }

    private void defineMap5x4(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);
    }

    private void defineMap5x5(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][4] = (ImageView) findViewById(R.id.iv34);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);
        mapspict[4][4] = (ImageView) findViewById(R.id.iv44);
    }

    private void defineMap5x6(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);
        mapspict[0][5] = (ImageView) findViewById(R.id.iv05);   mapspict[1][5] = (ImageView) findViewById(R.id.iv15);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][4] = (ImageView) findViewById(R.id.iv34);
        mapspict[2][5] = (ImageView) findViewById(R.id.iv25);   mapspict[3][5] = (ImageView) findViewById(R.id.iv35);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);
        mapspict[4][4] = (ImageView) findViewById(R.id.iv44);
        mapspict[4][5] = (ImageView) findViewById(R.id.iv45);
    }

    private void defineMap5x7(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);
        mapspict[0][5] = (ImageView) findViewById(R.id.iv05);   mapspict[1][5] = (ImageView) findViewById(R.id.iv15);
        mapspict[0][6] = (ImageView) findViewById(R.id.iv06);   mapspict[1][6] = (ImageView) findViewById(R.id.iv16);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][4] = (ImageView) findViewById(R.id.iv34);
        mapspict[2][5] = (ImageView) findViewById(R.id.iv25);   mapspict[3][5] = (ImageView) findViewById(R.id.iv35);
        mapspict[2][6] = (ImageView) findViewById(R.id.iv26);   mapspict[3][6] = (ImageView) findViewById(R.id.iv36);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);
        mapspict[4][4] = (ImageView) findViewById(R.id.iv44);
        mapspict[4][5] = (ImageView) findViewById(R.id.iv45);
        mapspict[4][6] = (ImageView) findViewById(R.id.iv46);
    }

    private void defineMap6x4(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);   mapspict[5][0] = (ImageView) findViewById(R.id.iv50);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);   mapspict[5][1] = (ImageView) findViewById(R.id.iv51);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);   mapspict[5][2] = (ImageView) findViewById(R.id.iv52);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);   mapspict[5][3] = (ImageView) findViewById(R.id.iv53);
    }

    private void defineMap6x5(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][4] = (ImageView) findViewById(R.id.iv34);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);   mapspict[5][0] = (ImageView) findViewById(R.id.iv50);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);   mapspict[5][1] = (ImageView) findViewById(R.id.iv51);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);   mapspict[5][2] = (ImageView) findViewById(R.id.iv52);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);   mapspict[5][3] = (ImageView) findViewById(R.id.iv53);
        mapspict[4][4] = (ImageView) findViewById(R.id.iv44);   mapspict[5][4] = (ImageView) findViewById(R.id.iv54);
    }

    private void defineMap6x6(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][4] = (ImageView) findViewById(R.id.iv14);
        mapspict[0][5] = (ImageView) findViewById(R.id.iv05);   mapspict[1][5] = (ImageView) findViewById(R.id.iv15);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][4] = (ImageView) findViewById(R.id.iv34);
        mapspict[2][5] = (ImageView) findViewById(R.id.iv25);   mapspict[3][5] = (ImageView) findViewById(R.id.iv35);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);   mapspict[5][0] = (ImageView) findViewById(R.id.iv50);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);   mapspict[5][1] = (ImageView) findViewById(R.id.iv51);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);   mapspict[5][2] = (ImageView) findViewById(R.id.iv52);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);   mapspict[5][3] = (ImageView) findViewById(R.id.iv53);
        mapspict[4][4] = (ImageView) findViewById(R.id.iv44);   mapspict[5][4] = (ImageView) findViewById(R.id.iv54);
        mapspict[4][5] = (ImageView) findViewById(R.id.iv45);   mapspict[5][5] = (ImageView) findViewById(R.id.iv55);
    }

    private void defineMap7x5(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);   mapspict[1][0] = (ImageView) findViewById(R.id.iv10);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);   mapspict[1][1] = (ImageView) findViewById(R.id.iv11);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);   mapspict[1][2] = (ImageView) findViewById(R.id.iv12);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);   mapspict[1][3] = (ImageView) findViewById(R.id.iv13);
        mapspict[0][4] = (ImageView) findViewById(R.id.iv04);   mapspict[1][3] = (ImageView) findViewById(R.id.iv14);

        mapspict[2][0] = (ImageView) findViewById(R.id.iv20);   mapspict[3][0] = (ImageView) findViewById(R.id.iv30);
        mapspict[2][1] = (ImageView) findViewById(R.id.iv21);   mapspict[3][1] = (ImageView) findViewById(R.id.iv31);
        mapspict[2][2] = (ImageView) findViewById(R.id.iv22);   mapspict[3][2] = (ImageView) findViewById(R.id.iv32);
        mapspict[2][3] = (ImageView) findViewById(R.id.iv23);   mapspict[3][3] = (ImageView) findViewById(R.id.iv33);
        mapspict[2][4] = (ImageView) findViewById(R.id.iv24);   mapspict[3][3] = (ImageView) findViewById(R.id.iv34);

        mapspict[4][0] = (ImageView) findViewById(R.id.iv40);   mapspict[5][0] = (ImageView) findViewById(R.id.iv50);
        mapspict[4][1] = (ImageView) findViewById(R.id.iv41);   mapspict[5][1] = (ImageView) findViewById(R.id.iv51);
        mapspict[4][2] = (ImageView) findViewById(R.id.iv42);   mapspict[5][2] = (ImageView) findViewById(R.id.iv52);
        mapspict[4][3] = (ImageView) findViewById(R.id.iv43);   mapspict[5][3] = (ImageView) findViewById(R.id.iv53);
        mapspict[4][4] = (ImageView) findViewById(R.id.iv44);   mapspict[5][3] = (ImageView) findViewById(R.id.iv54);

        mapspict[6][0] = (ImageView) findViewById(R.id.iv60);
        mapspict[6][1] = (ImageView) findViewById(R.id.iv61);
        mapspict[6][2] = (ImageView) findViewById(R.id.iv62);
        mapspict[6][3] = (ImageView) findViewById(R.id.iv63);
        mapspict[6][4] = (ImageView) findViewById(R.id.iv64);
    }
    //endregion

    //region swipe detector
    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    private void swipeDetector() {
        swipe = new Swipe();
        swipe.addListener(new SwipeListener() {
            @Override public void onSwipingLeft(final MotionEvent event) { }

            @Override public void onSwipedLeft(final MotionEvent event) {
                if (!isMoving){
                    if (!isReverse)
                        move("LEFT");
                    else
                        move("RIGHT");
                }
            }

            @Override public void onSwipingRight(final MotionEvent event) { }

            @Override public void onSwipedRight(final MotionEvent event) {
                if (!isMoving){
                    if (!isReverse)
                        move("RIGHT");
                    else
                        move("LEFT");
                }
            }

            @Override public void onSwipingUp(final MotionEvent event) { }

            @Override public void onSwipedUp(final MotionEvent event) {
                if (!isMoving){
                    if (!isReverse)
                        move("UP");
                    else
                        move("DOWN");
                }
            }

            @Override public void onSwipingDown(final MotionEvent event) { }

            @Override public void onSwipedDown(final MotionEvent event) {
                if (!isMoving){
                    if (!isReverse)
                        move("DOWN");
                    else
                        move("UP");
                }
            }
        });
    }
    //endregion

    public void playSound(int arg){
        try{
            if (player.isPlaying()) {
                player.stop();
                player.release();
            }

        } catch(Exception e){
            Log.e("play_sound", e.toString());
        }

        if(arg==1){
            player = MediaPlayer.create(this, R.raw.tombol);
        } else if(arg==2){
            player= MediaPlayer.create(this, R.raw.tut);
        } else if(arg==3){
            player= MediaPlayer.create(this, R.raw.themesong3);
        } else if(arg==4){
            player= MediaPlayer.create(this, R.raw.srut);
        }

        // Set looping
        player.setLooping(false);
        player.start();
    }
}
