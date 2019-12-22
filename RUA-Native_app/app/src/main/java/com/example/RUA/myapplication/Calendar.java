package com.example.RUA.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);




    }
    public void home(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }
    public void gift(View view) {
        Intent intent = new Intent(this, GiftSelection.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }
}
