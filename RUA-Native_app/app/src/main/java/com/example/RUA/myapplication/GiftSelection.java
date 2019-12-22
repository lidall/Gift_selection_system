package com.example.RUA.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class GiftSelection extends AppCompatActivity {


    private ImageView firstImageView;
    private ImageView secondImageView;
    private ImageView thirdImageView;
    private ImageView fourthImageView;
    private ImageView fifthImageView;
    private ImageView sixthImageView;
    private ImageView seventhImageView;
    private ImageView eighthImageView;
    private ImageView ninthImageView;
    private ImageView tenthImageView;
    private TextView firstText;
    private TextView secondText;
    private TextView thirdText;
    private TextView fourthText;
    private TextView fifthText;
    private TextView sixthText;
    private TextView seventhText;
    private TextView eighthText;
    private TextView ninthText;
    private TextView tenthText;
    private Spinner choiceSpinner;
    private EditText KeyWordsIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_selection);
        KeyWordsIn = (EditText)this.findViewById(R.id.GiftKeyWords);
        choiceSpinner = (Spinner)this.findViewById(R.id.ItemSpinner);
        final String[] ItemSort = {"All",
                "Apparel",
                "Appliances",
                "Automotive",
                "Baby",
                "Beauty",
                "Blended",
                "Books",
                "Classic",
                "DVD",
                "Electronics",
                "Grocery",
                "HealthPersonalCare",
                "HomeGarden",
                "Jewelry",
                "KindleStore",
                "Kitchen",
                "Lighting",
                "Luggage",
                "MobileApps",
                "Music",
                "MusicalInstruments",
                "OfficeProducts",
                "PCHardware",
                "PetSupplies",
                "Shoes",
                "Software",
                "SportingGoods",
                "Tools",
                "Toys",
                "UnboxVideo",
                "VHS",
                "VideoGames",
                "Watches"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ItemSort);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choiceSpinner.setAdapter(adapter);
        choiceSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                MainActivity.SORT_CHOICE = ItemSort[arg2];
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
                                                });

        RUAjustRunAlways();





    }


    public void SearchProcess(View view){
        ProcessXML.ITEM_IMAGE.clear();
        ProcessXML.ITEM_TITLE.clear();
        ProcessXML.ITEM_BRAND.clear();
        ProcessXML.ITEM_PRICE.clear();
        ProcessXML.ITEM_URL.clear();
        MainActivity.KEYWORD_CHOICE = KeyWordsIn.getText().toString();
        RUAjustRunAlways();
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

    public void RUAjustRunAlways(){
        new Thread(networkTask).start();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {

                    setContent();


                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }, 1500);
    }


    public static Bitmap getBitmapFromURL(String src) throws Exception {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {

            TestApi.callAPI(MainActivity.SORT_CHOICE,MainActivity.KEYWORD_CHOICE);

        }
    };

    public void setContent() throws Exception{
        ProcessXML.usefulXMLProcess();
        String url = ProcessXML.ITEM_IMAGE.get(0);

        firstImageView = (ImageView)findViewById(R.id.itemImage1);
        new ImageLoadTask(url, firstImageView).execute();
        firstText = (TextView)findViewById(R.id.itemText1);

        firstText.setText(ProcessXML.ITEM_TITLE.get(0)+"\n"+
                ProcessXML.ITEM_BRAND.get(0)+"\n"+ProcessXML.ITEM_PRICE.get(0));

        url = ProcessXML.ITEM_IMAGE.get(1);

        secondImageView = (ImageView)findViewById(R.id.itemImage2);
        new ImageLoadTask(url, secondImageView).execute();
        secondText = (TextView)findViewById(R.id.itemText2);
        secondText.setText(ProcessXML.ITEM_TITLE.get(1)+"\n"+ProcessXML.ITEM_BRAND.get(1)
                +"\n"+ProcessXML.ITEM_PRICE.get(1));



        url = ProcessXML.ITEM_IMAGE.get(2);

        thirdImageView = (ImageView)findViewById(R.id.itemImage3);
        new ImageLoadTask(url, thirdImageView).execute();
        thirdText = (TextView)findViewById(R.id.itemText3);

        thirdText.setText(ProcessXML.ITEM_TITLE.get(2)+"\n"+
                ProcessXML.ITEM_BRAND.get(2)+"\n"+ProcessXML.ITEM_PRICE.get(2));

        url = ProcessXML.ITEM_IMAGE.get(3);

        fourthImageView = (ImageView)findViewById(R.id.itemImage4);
        new ImageLoadTask(url, fourthImageView).execute();
        fourthText = (TextView)findViewById(R.id.itemText4);
        fourthText.setText(ProcessXML.ITEM_TITLE.get(3)+"\n"+ProcessXML.ITEM_BRAND.get(3)
                +"\n"+ProcessXML.ITEM_PRICE.get(3));


        url = ProcessXML.ITEM_IMAGE.get(4);

        fifthImageView = (ImageView)findViewById(R.id.itemImage5);
        new ImageLoadTask(url, fifthImageView).execute();
        fifthText = (TextView)findViewById(R.id.itemText5);

        fifthText.setText(ProcessXML.ITEM_TITLE.get(4)+"\n"+
                ProcessXML.ITEM_BRAND.get(4)+"\n"+ProcessXML.ITEM_PRICE.get(4));

        url = ProcessXML.ITEM_IMAGE.get(5);

        sixthImageView = (ImageView)findViewById(R.id.itemImage6);
        new ImageLoadTask(url, sixthImageView).execute();
        sixthText = (TextView)findViewById(R.id.itemText6);
        sixthText.setText(ProcessXML.ITEM_TITLE.get(5)+"\n"+ProcessXML.ITEM_BRAND.get(5)
                +"\n"+ProcessXML.ITEM_PRICE.get(5));



        url = ProcessXML.ITEM_IMAGE.get(6);

        seventhImageView = (ImageView)findViewById(R.id.itemImage7);
        new ImageLoadTask(url, seventhImageView).execute();
        seventhText = (TextView)findViewById(R.id.itemText7);

        seventhText.setText(ProcessXML.ITEM_TITLE.get(6)+"\n"+
                ProcessXML.ITEM_BRAND.get(6)+"\n"+ProcessXML.ITEM_PRICE.get(6));

        url = ProcessXML.ITEM_IMAGE.get(7);

        eighthImageView = (ImageView)findViewById(R.id.itemImage8);
        new ImageLoadTask(url, eighthImageView).execute();
        eighthText = (TextView)findViewById(R.id.itemText8);
        eighthText.setText(ProcessXML.ITEM_TITLE.get(7)+"\n"+ProcessXML.ITEM_BRAND.get(7)
                +"\n"+ProcessXML.ITEM_PRICE.get(7));


        url = ProcessXML.ITEM_IMAGE.get(8);

        ninthImageView = (ImageView)findViewById(R.id.itemImage9);
        new ImageLoadTask(url, ninthImageView).execute();
        ninthText = (TextView)findViewById(R.id.itemText9);

        ninthText.setText(ProcessXML.ITEM_TITLE.get(8)+"\n"+
                ProcessXML.ITEM_BRAND.get(8)+"\n"+ProcessXML.ITEM_PRICE.get(8));

        url = ProcessXML.ITEM_IMAGE.get(9);

        tenthImageView = (ImageView)findViewById(R.id.itemImage10);
        new ImageLoadTask(url, tenthImageView).execute();
        tenthText = (TextView)findViewById(R.id.itemText10);
        tenthText.setText(ProcessXML.ITEM_TITLE.get(9)+"\n"+ProcessXML.ITEM_BRAND.get(9)
                +"\n"+ProcessXML.ITEM_PRICE.get(9));



    }

    public void Url_1(View view){
        String Url_D = ProcessXML.ITEM_URL.get(0);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void Url_2(View view){
        String Url_D = ProcessXML.ITEM_URL.get(1);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_3(View view){
        String Url_D = ProcessXML.ITEM_URL.get(2);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_4(View view){
        String Url_D = ProcessXML.ITEM_URL.get(3);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_5(View view){
        String Url_D = ProcessXML.ITEM_URL.get(4);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_6(View view){
        String Url_D = ProcessXML.ITEM_URL.get(5);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_7(View view){
        String Url_D = ProcessXML.ITEM_URL.get(6);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_8(View view){
        String Url_D = ProcessXML.ITEM_URL.get(7);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_9(View view){
        String Url_D = ProcessXML.ITEM_URL.get(8);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void Url_10(View view){
        String Url_D = ProcessXML.ITEM_URL.get(9);
        Uri uri = Uri.parse(Url_D);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
