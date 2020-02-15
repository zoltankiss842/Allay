package com.example.allay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class professionalLoginScreen extends AppCompatActivity {

    private Button loginButton;
    private TextView noEmail;

    private ConstraintLayout cl;                                // Background animation
    private AnimationDrawable ad;                               // Background animation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_login_screen);

        animationStart();

        loginButton = findViewById(R.id.loginButton);
        noEmail = findViewById(R.id.noEmail);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(professionalLoginScreen.this, professionalMainActivityScreen.class);
                startActivity(startIntent);
                finish();
            }
        });

        noEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(professionalLoginScreen.this);
                builder1.setTitle("In case you have not received an email");
                builder1.setMessage("You have to contact with the agency again. Email: agency@agency.uk");
                builder1.setCancelable(true);


                builder1.setNegativeButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }

    private void animationStart(){
        cl = findViewById(R.id.layout);
        ad = (AnimationDrawable) cl.getBackground();
        ad.setEnterFadeDuration(4000);
        ad.setExitFadeDuration(4000);
        ad.start();
    }
}
