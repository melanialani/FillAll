package com.application.melanialani.fillall;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

public class CharacterActivity extends AppCompatActivity {

    private Swipe swipe;
    private TextView text;
    private ImageView img1,img2,img3;

    ImageView[] img= new ImageView[7];

    private int currentChar;

    boolean isBackPosition=false;
    boolean isTouch;

    private Handler         handler;
    private Runnable        runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        currentChar=0;
        isTouch=false;
        //text=(TextView) findViewById(R.id.txt);
        img[0]=(ImageView) findViewById(R.id.img1);
        img[1]=(ImageView) findViewById(R.id.img2);
        img[2]=(ImageView) findViewById(R.id.img3);
        img[3]=(ImageView) findViewById(R.id.img4);
        img[4]=(ImageView) findViewById(R.id.img5);
        img[5]=(ImageView) findViewById(R.id.img6);
        img[6]=(ImageView) findViewById(R.id.img7);

        swipeDetector();

    }

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    //region thread
    @Override
    protected void onStart() {
        super.onStart();

        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        // random angka dari 1 - 100
                        if(isBackPosition)
                        {
                            back_position();
                        }
                        Thread.sleep(10);
                    } catch (Exception ex){
                        Toast.makeText(CharacterActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        background.start();
    }
    //endregion

    private void swipeDetector() {
        swipe = new Swipe();
        swipe.addListener(new SwipeListener() {
            @Override public void onSwipingLeft(final MotionEvent event) {
                moving_left_animation();

            }

            @Override public void onSwipedLeft(final MotionEvent event) {
                if(currentChar<img.length-1)
                    currentChar++;
                isBackPosition=true;
                System.out.println("current char:" + currentChar);
            }

            @Override public void onSwipingRight(final MotionEvent event) {
                moving_right_animation();
            }

            @Override public void onSwipedRight(final MotionEvent event) {
                if(currentChar>0)
                    currentChar--;
                isBackPosition=true;
                System.out.println("current char:" + currentChar);
            }

            @Override public void onSwipingUp(final MotionEvent event) { }

            @Override public void onSwipedUp(final MotionEvent event) { }

            @Override public void onSwipingDown(final MotionEvent event) { }

            @Override public void onSwipedDown(final MotionEvent event) { }
        });
    }

    private void move(String in) {
        isTouch=true;
        if(in.equals("RIGHT"))
        {
            isTouch=true;
            if(currentChar>0)
                currentChar--;
            System.out.println(currentChar);
        }
        else if(in.equals("LEFT"))
        {
            isTouch=true;
            currentChar++;

            System.out.println(currentChar);
        }

    }

    private void moving_right_animation() {
        int interval=25;
        for(int i=0;i<img.length;i++)
        {
            img[i].setX(img[i].getX()+interval);
        }


    }

    private void moving_left_animation() {
        int interval=25;
        for(int i=0;i<img.length;i++)
        {
            img[i].setX(img[i].getX()-interval);
        }
    }

    private void back_position(){

        if(img[currentChar].getX()<=100 )
        {
            try{
                for(int i=0;i<img.length;i++)
                {
                    img[i].setX(img[i].getX()+10);
                }
            }
            catch (Exception ex){
                Log.e("move_ERR", ex.toString());
            }
        }
        else if(img[currentChar].getX()>=110 )
        {
            try{
                for(int i=0;i<img.length;i++)
                {
                    img[i].setX(img[i].getX()-10);
                }
            }
            catch (Exception ex){
                Log.e("move_ERR", ex.toString());
            }
        }
        else
        {
            isBackPosition=false;
        }


    }

    private void coba(){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                img[currentChar].setX(img[currentChar].getX()+100);

            }
        }, 300);
    }
}
