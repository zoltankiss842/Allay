package com.example.soothe2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.File;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> userData = new HashMap<>();
    private IO io = new IO();

    public HashMap<String, String> getUserData() {
        return userData;
    }

    public void setUserData(HashMap<String, String> userData) {
        this.userData = userData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        Intent intent = getIntent();
        if(intent.hasExtra("modified_map")){
            userData = (HashMap<String, String>) intent.getSerializableExtra("modified_map");
        }


        if(firstStart){
            showFirstTime();
        }
        else{
            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Soothe" + File.separator + "user_data.txt");
            String path_to = path.toString();
            try {
                io.readData(path_to, userData);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            if(Build.VERSION.SDK_INT >= 24){
                setContentView(R.layout.activity_main);
            }
            else{
                setContentView(R.layout.activity_main_v21);
            }

            TextView helloThereUser = (TextView) findViewById(R.id.helloThereUser);
            helloThereUser.setText("Hello there " + userData.get("name"));

            final CardView perInf = (CardView) findViewById(R.id.personalInfo);
            perInf.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            perInf.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            Intent intent = new Intent(MainActivity.this, personalInformation.class);
                            intent.putExtra("map", userData);
                            startActivity(intent);
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            perInf.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            perInf.setCardBackgroundColor(Color.parseColor("#FF808080"));
                            break;
                        }
                    }
                    return true;
                }
            });

            final CardView checkUp = (CardView) findViewById(R.id.checkUp);
            checkUp.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            checkUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, checkUp.class));
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            checkUp.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, checkUp.class));
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            checkUp.setCardBackgroundColor(Color.parseColor("#FF808080"));
                            break;
                        }
                    }
                    return true;
                }
            });

            final CardView professionalContact = (CardView) findViewById(R.id.professionalContact);
            professionalContact.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            professionalContact.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, professionalContact.class));
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            professionalContact.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            professionalContact.setCardBackgroundColor(Color.parseColor("#FF808080"));
                            break;
                        }
                    }
                    return true;
                }
            });

            final CardView chatWithOthers = (CardView) findViewById(R.id.chatWithOthers);
            chatWithOthers.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            chatWithOthers.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, chatWithOthers.class));
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            chatWithOthers.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, chatWithOthers.class));
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            chatWithOthers.setCardBackgroundColor(Color.parseColor("#FF808080"));
                            break;
                        }
                    }
                    return true;
                }
            });

            final CardView sleepSleep = (CardView) findViewById(R.id.sleepSleep);
            sleepSleep.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            sleepSleep.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, sleepSleep.class));
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            sleepSleep.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, sleepSleep.class));
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            sleepSleep.setCardBackgroundColor(Color.parseColor("#FF808080"));
                            break;
                        }
                    }
                    return true;
                }
            });

            final CardView aboutInfo = (CardView) findViewById(R.id.aboutInfo);
            aboutInfo.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:{
                            aboutInfo.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, aboutInfo.class));
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:{
                            aboutInfo.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            startActivity(new Intent(MainActivity.this, aboutInfo.class));
                            break;
                        }
                        case MotionEvent.ACTION_DOWN:{
                            aboutInfo.setCardBackgroundColor(Color.parseColor("#FF808080"));
                            break;
                        }
                    }
                    return true;
                }
            });

        }

    }

    private void showFirstTime(){
        startActivity(new Intent(MainActivity.this, firstTimeOpening.class));

        SharedPreferences prefs = getSharedPreferences("prefs" , MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}
