package com.example.allay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class professionalMainActivityScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_main_screen);

        ImageButton personalInfo = findViewById(R.id.profPersonalInfo);
        ImageButton currPatients = findViewById(R.id.currPatients);

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(professionalMainActivityScreen.this, professionalPersonalInformation.class));
            }
        });

        currPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(professionalMainActivityScreen.this, currentPatients.class));
            }
        });
    }
}
