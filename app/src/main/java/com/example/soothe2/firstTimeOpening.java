package com.example.soothe2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class firstTimeOpening extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 101;     // For storage permissions
    private String[] permissionArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private String name = "";                                   // User name stored in here
    private IO io = new IO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_opening);
        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.layout);
        AnimationDrawable ad = (AnimationDrawable) cl.getBackground();
        ad.setEnterFadeDuration(4000);
        ad.setExitFadeDuration(4000);
        ad.start();

        // Checks the storage permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, permissionArray, STORAGE_PERMISSION_CODE);
        }
        else{
            Toast.makeText(this, "Storage permission already granted", Toast.LENGTH_SHORT).show();
        }

        // Changes the title and the quote to Vegan font
        TextView sootheTitle = (TextView) findViewById(R.id.sootheTitle);
        TextView quote = (TextView) findViewById(R.id.quoteText);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Vegan.ttf");
        sootheTitle.setTypeface(typeface);
        quote.setTypeface(typeface);

        // Initializes the listener for the button
        Button nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameInput = (EditText) findViewById(R.id.enterName);       //Gets the input from the EditText
                name = nameInput.getText().toString();
                name = name.trim();

                if(name.length() == 0){
                    nameEmptyToast();
                }
                else{
                    startSavingData("name", name);
                    startNextScreen();
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "STORAGE permission granted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "STORAGE permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startNextScreen(){ //Starts firstTimeOpening2
        Intent startIntent = new Intent(getApplicationContext(), firstTimeOpening2.class);
        startIntent.putExtra("com.example.soothe2.FIRST2", name);
        startActivity(startIntent);
    }

    private void nameEmptyToast(){
        Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
    }


    private void startSavingData(String key, String text){

        try {
            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Soothe");
            if(!path.exists()){
                Toast.makeText(this, (path.mkdirs() ? "Directory has been created" : "Directory not created"),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }
            String fileName = "user_data.txt";

            io.saveData(fileName, path, key, name);

            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }
    }
}
