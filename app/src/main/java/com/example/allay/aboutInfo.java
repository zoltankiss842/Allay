package com.example.allay;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class aboutInfo extends AppCompatActivity {

    private ImageView mySelf;
    private ImageView stress;
    private ImageView calmME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);

        // Initializing variables
        mySelf = findViewById(R.id.myPossibleSelf);
        stress = findViewById(R.id.stress);
        calmME = findViewById(R.id.calmME);

        calmME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // If there is know Google Play on phone, then it opens up in a webpage
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=uk.org.stem4.calmharm")));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=uk.org.stem4.calmharm")));
                }
            }
        });

        stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // If there is know Google Play on phone, then it opens up in a webpage
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=com.companionapps.anxietycompanion")));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.companionapps.anxietycompanion&hl=en_GB")));
                }
            }
        });

        mySelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // If there is know Google Play on phone, then it opens up in a webpage
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=com.mypossibleself.app")));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.mypossibleself.app")));
                }
            }
        });
    }
}
