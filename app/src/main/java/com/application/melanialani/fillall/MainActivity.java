package com.application.melanialani.fillall;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

public class MainActivity extends AppCompatActivity {

    //region variables
    private Swipe           swipe;
    private TextView        textView;

    private String[][]      maps;
    private ImageView[][]   mapspict;

    private int             posX, posY;

    private static final int lebar = 4;
    private static final int tinggi = 5;
    //endregion

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

        // empty array maps and hide imageview
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
            int posYbefore = posY;
            for (int y = posYbefore; y < lebar; y++){
                try {
                    if (maps[posX][y].equals("0")){
                        maps[posX][y] = "1";
                        mapspict[posX][y].setImageResource(R.drawable.all);
                        posY = y;
                    }
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        } else if (action.equalsIgnoreCase("LEFT")){ // x same, y minus
            int posYbefore = posY;
            for (int y = posYbefore; y >= 0; y--){
                try {
                    if (maps[posX][y].equals("0")){
                        maps[posX][y] = "1";
                        mapspict[posX][y].setImageResource(R.drawable.all);
                        posY = y;
                    }
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        } else if (action.equalsIgnoreCase("DOWN")){ // x plus, y same
            int posXbefore = posX;
            for (int x = posXbefore; x < tinggi; x++){
                try {
                    if (maps[x][posY].equals("0")){
                        maps[x][posY] = "1";
                        mapspict[x][posY].setImageResource(R.drawable.all);
                        posX = x;
                    }
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        } else if (action.equalsIgnoreCase("UP")){ // x minus, y same
            int posXbefore = posX;
            for (int x = posXbefore; x >= 0; x--){
                try {
                    if (maps[x][posY].equals("0")){
                        maps[x][posY] = "1";
                        mapspict[x][posY].setImageResource(R.drawable.all);
                        posX = x;
                    }
                } catch (Exception ex){
                    Log.e("move_ERR", ex.toString());
                }
                //textView.setText(textView.getText() + " " + posX + "," + posY);
            }
        }
    }
}
