package com.application.melanialani.fillall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

public class MainActivity extends AppCompatActivity {

    //region variables
    private Swipe           swipe;
    private TextView        textView;

    private String[][]      maps;
    private ImageView[][]   mapspict;

    private int             posX, posY;

    private int             lebar = 4;
    private int             tinggi = 5;

    private Stage1          stage1;
    //endregion

    //region dont touch this if not necessary
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate variables;
        maps = new String[tinggi][lebar];
        mapspict = new ImageView[tinggi][lebar];

        // initiate textview
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("");

        // initiate swipe listener
        swipeDetector();

        // put each imageview into array
        defineImageView();
    }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    private void swipeDetector() {
        swipe = new Swipe();
        swipe.addListener(new SwipeListener() {
            @Override public void onSwipingLeft(final MotionEvent event) {
                //textView.setText("SWIPING_LEFT");
                move("LEFT");
            }

            @Override public void onSwipedLeft(final MotionEvent event) {
                //textView.setText("SWIPED_LEFT");
                move("LEFT");
            }

            @Override public void onSwipingRight(final MotionEvent event) {
                //textView.setText("SWIPING_RIGHT");
                move("RIGHT");
            }

            @Override public void onSwipedRight(final MotionEvent event) {
                //textView.setText("SWIPED_RIGHT");
                move("RIGHT");
            }

            @Override public void onSwipingUp(final MotionEvent event) {
                //textView.setText("SWIPING_UP");
                move("UP");
            }

            @Override public void onSwipedUp(final MotionEvent event) {
                //textView.setText("SWIPED_UP");
                move("UP");
            }

            @Override public void onSwipingDown(final MotionEvent event) {
                //textView.setText("SWIPING_DOWN");
                move("DOWN");
            }

            @Override public void onSwipedDown(final MotionEvent event) {
                //textView.setText("SWIPED_DOWN");
                move("DOWN");
            }
        });
    }

    private void move(String action) {
        if (action.equalsIgnoreCase("RIGHT")){ // x same, y plus
            int posYwanted = posY + 1;
            for (int y = posYwanted; y < lebar; y++){
                try {
                    if (maps[posX][y].equals("0")){
                        maps[posX][y] = "1";
                        mapspict[posX][y].setImageResource(R.drawable.all);
                        posY = y;
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        } else if (action.equalsIgnoreCase("LEFT")){ // x same, y minus
            int posYwanted = posY - 1;
            for (int y = posYwanted; y >= 0; y--){
                try {
                    if (maps[posX][y].equals("0")){
                        maps[posX][y] = "1";
                        mapspict[posX][y].setImageResource(R.drawable.all);
                        posY = y;
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        } else if (action.equalsIgnoreCase("DOWN")){ // x plus, y same
            int posXwanted = posX + 1;
            for (int x = posXwanted; x < tinggi; x++){
                try {
                    if (maps[x][posY].equals("0")){
                        maps[x][posY] = "1";
                        mapspict[x][posY].setImageResource(R.drawable.all);
                        posX = x;
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        } else if (action.equalsIgnoreCase("UP")){ // x minus, y same
            int posXwanted = posX - 1;
            for (int x = posXwanted; x >= 0; x--){
                try {
                    if (maps[x][posY].equals("0")){
                        maps[x][posY] = "1";
                        mapspict[x][posY].setImageResource(R.drawable.all);
                        posX = x;
                    } else
                        break;
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        }
    }
    //endregion

    private void defineImageView() {
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

        // empty array maps
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    maps[x][y] = "0";
                } catch (Exception ex){
                    Log.e("array_ERR", ex.toString());
                }
            }
        }

        // set default player position
        posX = 4;
        posY = 3;
        maps[posX][posY] = "1";
        mapspict[posX][posY].setImageResource(R.drawable.all);
    }

    //region stage1
    public void level1(View v){
        // get data from stage1
        stage1 = new Stage1();
        stage1.setLevel(1);

        initiateNewMap();
    }

    public void level2(View v){
        // get data from stage1
        stage1 = new Stage1();
        stage1.setLevel(2);

        initiateNewMap();
    }

    public void level3(View v){
        // get data from stage1
        stage1 = new Stage1();
        stage1.setLevel(3);

        initiateNewMap();
    }

    public void level4(View v){
        // get data from stage1
        stage1 = new Stage1();
        stage1.setLevel(4);

        initiateNewMap();
    }

    public void level5(View v){
        // get data from stage1
        stage1 = new Stage1();
        stage1.setLevel(5);

        initiateNewMap();
    }
    //endregion

    private void initiateNewMap(){
        this.lebar = stage1.getLebar();
        this.tinggi = stage1.getTinggi();
        this.posX = stage1.getPosX();
        this.posY = stage1.getPosY();

        this.maps = new String[tinggi][lebar];
        this.maps = stage1.getMap();

        this.mapspict = new ImageView[tinggi][lebar];

        // set layout & put each image view into array
        if (tinggi == 1 && lebar == 4) {
            setContentView(R.layout.map1x4);
            defineMap1x4();
        } else if (tinggi == 4 && lebar == 4) {
            setContentView(R.layout.map4x4);
            defineMap4x4();
        } else if (tinggi == 5 && lebar == 5) {
            setContentView(R.layout.map5x5);
            defineMap5x5();
        } else if (tinggi == 5 && lebar == 6) {
            setContentView(R.layout.map5x6);
            defineMap5x6();
        }

        // properly set image source in each image view
        for (int x = 0; x < tinggi; x++){
            for (int y = 0; y < lebar; y++){
                try {
                    if (maps[x][y].equals("0")){
                        mapspict[x][y].setImageResource(R.drawable.blank);
                    } else if (maps[x][y].equals("1")){
                        mapspict[x][y].setImageResource(R.drawable.all);
                    } else if (maps[x][y].equals("#")){
                        mapspict[x][y].setImageResource(R.drawable.block);
                    }
                } catch (Exception ex){
                    Log.e("array_ERR", ex.toString());
                }
            }
        }
    }

    //region define array image view
    private void defineMap1x4(){
        mapspict[0][0] = (ImageView) findViewById(R.id.iv00);
        mapspict[0][1] = (ImageView) findViewById(R.id.iv01);
        mapspict[0][2] = (ImageView) findViewById(R.id.iv02);
        mapspict[0][3] = (ImageView) findViewById(R.id.iv03);
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
    //endregion
}
