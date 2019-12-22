package com.example.RUA.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message = MainActivity.USER_NAME;
        if(message.contains("@")){
        message = message.substring(0,message.indexOf("@"));}
        TextView textView = findViewById(R.id.user);
        textView.setText(message+"!");
    }

    public void calendar(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    public void gift(View view) {
        Intent intent = new Intent(this, GiftSelection.class);
        startActivity(intent);
    }

    public void hongyiprofile(View view) {
        Intent intent = new Intent(this, hypro.class);
        startActivity(intent);
    }
    public void profile(View view) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

}


