package com.example.RUA.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class friends extends AppCompatActivity {

    private String FRIEND_WISH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
    }

    public void calendar(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
    public void home(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
    public void hypro(View view) {
        Intent intent = new Intent(this, hypro.class);
        startActivity(intent);
    }
    public void chooseGift(View view) {

        new Thread(dataTask).start();

        final Random random = new Random();

        new Handler().postDelayed(new Runnable() {
            public void run() {
            try {
                View view;

                String[] tempData = FRIEND_WISH.split(",");
                String randChoice = tempData[random.nextInt(tempData.length)];

                MainActivity.KEYWORD_CHOICE = randChoice;
                MainActivity.SORT_CHOICE = "All";


                ProcessXML.ITEM_IMAGE.clear();
                ProcessXML.ITEM_TITLE.clear();
                ProcessXML.ITEM_BRAND.clear();
                ProcessXML.ITEM_PRICE.clear();
                ProcessXML.ITEM_URL.clear();

                Timer timer = new Timer();
                timer.schedule(new Task(),100);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }, 800);


    }
    Runnable dataTask = new Runnable() {

        @Override
        public void run() {

            FRIEND_WISH=DatabaseFunction.getinfor(2);

        }
    };


    private class Task extends TimerTask {
        @Override
        public void run() {
            startActivity(new Intent(friends.this,GiftSelection.class));
        }
    }

}
