package com.example.RUA.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void calendar(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
    public void home(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }
    public void gift(View view) {
        Intent intent = new Intent(this, GiftSelection.class);
        startActivity(intent);
    }
    public void friends(View view) {
        Intent intent = new Intent(this, friends.class);
        startActivity(intent);
    }
}
