package com.example.RUA.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class hypro extends AppCompatActivity {

    private TextView getBirthday;
    private TextView getWish;
    private static String FRIEND_BIRTH;
    private static String FRIEND_WISH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hypro);

        new Thread(dataTask).start();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {

                    getBirthday = (TextView)findViewById(R.id.textView10);
                    getBirthday.setText(FRIEND_BIRTH);
                    getWish = findViewById(R.id.textView13);
                    getWish.setText(FRIEND_WISH);


                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }, 800);

    }

    Runnable dataTask = new Runnable() {

        @Override
        public void run() {

            FRIEND_BIRTH=DatabaseFunction.getinfor(1);
            FRIEND_WISH=DatabaseFunction.getinfor(2);

        }
    };


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
    public void gift(View view) {
        Intent intent = new Intent(this, GiftSelection.class);
        startActivity(intent);
    }
}
