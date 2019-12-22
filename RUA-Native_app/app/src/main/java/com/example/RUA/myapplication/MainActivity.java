package com.example.RUA.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    public static String USER_NAME;
    public static String SORT_CHOICE = "VideoGames";
    public static String KEYWORD_CHOICE = "pokemon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void home(View view) {
        Intent intent =new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.inputusername);
        String message=editText.getText().toString();
        USER_NAME = message;
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}