package com.example.allay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

public class loginType extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 101;     // For storage permissions
    IO io = new IO();
    private String[] permissionArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private ImageButton patientLogin;
    private ImageButton professionalLogin;
    private ConstraintLayout cl;                                // Background animation
    private AnimationDrawable ad;                               // Background animation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, permissionArray, STORAGE_PERMISSION_CODE);
        }
        else{
            Toast.makeText(this, "Storage permission already granted", Toast.LENGTH_SHORT).show();
        }

        animationStart();

        patientLogin = findViewById(R.id.patientLogin);
        professionalLogin = findViewById(R.id.professionalLogin);

        patientLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(loginType.this, firstTimeOpening.class);
                startSavingData("true");
                startActivity(startIntent);
                finish();
            }
        });

        professionalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(loginType.this, professionalLoginScreen.class);
                startSavingData("false");
                startActivity(startIntent);
                finish();
            }
        });

    }

    private boolean startSavingData(String text){

        try {
            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Allay");
            if(!path.exists()){
//                Toast.makeText(this, (path.mkdirs() ? "Directory has been created" : "Directory not created"),Toast.LENGTH_SHORT).show();
            }
            else{
//                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }
            String fileName = "is_patient.txt";

            io.saveData(fileName, path, text);

//            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
//            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this, "STORAGE permission granted", Toast.LENGTH_SHORT).show();
            }
            else{
//                Toast.makeText(this, "STORAGE permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void animationStart(){
        cl = findViewById(R.id.layout);
        ad = (AnimationDrawable) cl.getBackground();
        ad.setEnterFadeDuration(4000);
        ad.setExitFadeDuration(4000);
        ad.start();
    }
}
