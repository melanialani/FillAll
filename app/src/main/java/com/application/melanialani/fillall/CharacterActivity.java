package com.application.melanialani.fillall;

import android.content.Intent;
import android.os.Handler;
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

public class CharacterActivity extends AppCompatActivity {

    private Swipe swipe;
    private TextView text;
    private ImageView img1,img2,img3;

    ImageView[] img = new ImageView[10];
    ImageView[] imgBuyed = new ImageView[10];

    private int currentChar;

    boolean isBackPosition = false;
    boolean isTouch;
    boolean isBuyed[];

    private Handler         handler;
    private DatabaseHelper  db;
    private Data data;

    public TextView tvCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        currentChar=0;
        isTouch=false;
        data=new Data();
        isBuyed=new boolean[10];
        for(int i=0;i<10;i++)
        {
            isBuyed[i]=false;
        }

        //text=(TextView) findViewById(R.id.txt);
        img[0]=(ImageView) findViewById(R.id.img1);
        img[1]=(ImageView) findViewById(R.id.img2);
        img[2]=(ImageView) findViewById(R.id.img3);
        img[3]=(ImageView) findViewById(R.id.img4);
        img[4]=(ImageView) findViewById(R.id.img5);
        img[5]=(ImageView) findViewById(R.id.img6);
        img[6]=(ImageView) findViewById(R.id.img7);
        img[7]=(ImageView) findViewById(R.id.img8);
        img[8]=(ImageView) findViewById(R.id.img9);
        img[9]=(ImageView) findViewById(R.id.img10);

        db = new DatabaseHelper(CharacterActivity.this);

        dbChacker();

        picChange();

        swipeDetector();

        tvCoin = (TextView) findViewById(R.id.tvCoin);
        tvCoin.setText(String.valueOf(db.getCoins()));
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

        if(img[currentChar].getX()<=75 )
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
        else if(img[currentChar].getX()>=100 )
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

    //region choose player
    public void chooseplayer1(View view){
        String character = "atu";
        int idx = 0;
        characteronClick(idx, character);
    }

    public void chooseplayer2(View view){
        String character = "black";
        int idx = 1;
        characteronClick(idx, character);
    }

    public void chooseplayer3(View view){
        String character = "bulb";
        int idx = 2;
        characteronClick(idx, character);
    }

    public void chooseplayer4(View view){
        String character = "flatre";
        int idx = 3;
        characteronClick(idx, character);
    }

    public void chooseplayer5(View view){
        String character = "geeks";
        int idx = 4;
        characteronClick(idx, character);
    }

    public void chooseplayer6(View view){
        String character = "lemon";
        int idx = 5;
        characteronClick(idx, character);
    }

    public void chooseplayer7(View view){
        String character = "oce";
        int idx = 6;
        characteronClick(idx, character);
    }

    public void chooseplayer8(View view){
        String character = "pika";
        int idx = 7;
        characteronClick(idx, character);
    }

    public void chooseplayer9(View view){
        String character = "pur";
        int idx = 8;
        characteronClick(idx, character);
    }

    public void chooseplayer10(View view){
        String character = "red";
        int idx = 9;
        characteronClick(idx, character);
    }
    //endregion

    private void gotoMainMenu(){
        this.finish();
    }

    private void picChange() {
        if(isBuyed[0]==false)
        {
            img[0].setImageResource(R.drawable.atu_ani0_locked);
        }else
        {
            img[0].setImageResource(R.drawable.atu_ani0);
        }
        if(isBuyed[1]==false)
        {
            img[1].setImageResource(R.drawable.black_ani0_locked);
        }else
        {
            img[1].setImageResource(R.drawable.black_ani0);
        }
        if(isBuyed[2]==false)
        {
            img[2].setImageResource(R.drawable.bulb_ani0_locked);
        }else
        {
            img[2].setImageResource(R.drawable.bulb_ani0);
        }
        if(isBuyed[3]==false)
        {
            img[3].setImageResource(R.drawable.flatre_ani0_locked);
        }else
        {
            img[3].setImageResource(R.drawable.flatre_ani0);
        }
        if(isBuyed[4]==false)
        {
            img[4].setImageResource(R.drawable.geeks_ani0_locked);
        }else
        {
            img[4].setImageResource(R.drawable.geeks_ani0);
        }
        if(isBuyed[5]==false)
        {
            img[5].setImageResource(R.drawable.lemon_ani0_locked);
        }else
        {
            img[5].setImageResource(R.drawable.lemon_ani0);
        }
        if(isBuyed[6]==false)
        {
            img[6].setImageResource(R.drawable.oce_ani0_locked);
        }else
        {
            img[6].setImageResource(R.drawable.oce_ani0);
        }
        if(isBuyed[7]==false)
        {
            img[7].setImageResource(R.drawable.pika_ani0_locked);
        }else
        {
            img[7].setImageResource(R.drawable.pika_ani0);
        }
        if(isBuyed[8]==false)
        {
            img[8].setImageResource(R.drawable.pur_ani0_locked);
        }else
        {
            img[8].setImageResource(R.drawable.pur_ani0);
        }
        if(isBuyed[9]==false)
        {
            img[9].setImageResource(R.drawable.red_ani0_locked);
        }else
        {
            img[9].setImageResource(R.drawable.red_ani0);
        }
    }

    private void dbChacker() {
        String tamp=db.getCharactersUnlocked();
        String[] dataDb=tamp.split(",");
        for(int i=0;i<10;i++)
        {
            isBuyed[i]=false;
        }
        for(int i=0;i<dataDb.length;i++)
        {
            if(dataDb[i].equalsIgnoreCase("atu"))
            {
                isBuyed[0]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("black"))
            {
                isBuyed[1]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("bulb"))
            {
                isBuyed[2]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("flatre"))
            {
                isBuyed[3]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("geeks"))
            {
                isBuyed[4]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("lemon"))
            {
                isBuyed[5]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("oce"))
            {
                isBuyed[6]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("pika"))
            {
                isBuyed[7]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("pur"))
            {
                isBuyed[8]=true;
            }
            else if(dataDb[i].equalsIgnoreCase("red"))
            {
                isBuyed[9]=true;
            }
        }
    }

    private void characteronClick(int idx, String character){
        if(isBuyed[idx]) {
            db.setChosenCharacter(character);
            Toast.makeText(getApplicationContext(), "Character" + character + " chosen", Toast.LENGTH_SHORT).show();
        } else {
            if(data.getpriceCharacter(idx)<=db.getCoins()) {
                Toast.makeText(getApplicationContext(), "Bought " + character, Toast.LENGTH_SHORT).show();

                isBuyed[idx] = true;

                db.setCharactersUnlocked(db.getCharactersUnlocked() + ","+ character);
                db.setCoins(db.getCoins() - data.getpriceCharacter(idx));
            } else {
                Toast.makeText(getApplicationContext(), "Money's not enough to buy", Toast.LENGTH_SHORT).show();
            }
        }

        gotoMainMenu();
    }
}
