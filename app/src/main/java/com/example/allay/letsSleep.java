package com.example.allay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class letsSleep extends AppCompatActivity {

    private static final int REQUEST_MICROPHONE = 99;     // For storage permissions
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_sleep);

        context = getApplicationContext();

        ImageButton letSleep = findViewById(R.id.sleepBtn);

        letSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks the storage permission
                if(ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(letsSleep.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_MICROPHONE);
                }
                else{
                    Toast.makeText(context, "Microphone permission already granted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
