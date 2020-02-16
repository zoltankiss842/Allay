package com.example.allay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class logADream extends AppCompatActivity {

    private IO io;
    private EditText title;
    private EditText story;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_adream);

        io = new IO();

        title = findViewById(R.id.actualTitle);
        story = findViewById(R.id.actualStory);
        saveButton = findViewById(R.id.saveDream);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!title.getText().toString().equals("") && !story.getText().toString().equals("")){
                    startSavingData(title.getText().toString(),story.getText().toString(), true);
                    startActivity(new Intent(logADream.this, dreamLog.class));
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Cannot save the story...", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }

    private boolean startSavingData(String key, String text, boolean append){

        if(io.containsIllegalChars(key) || io.containsIllegalChars(text)){
            return false;
        }

        try {

            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Allay");
            if(!path.exists()){
                Toast.makeText(this, (path.mkdirs() ? "Directory has been created" : "Directory not created"),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }

            String fileName = "dream_log.txt";

            File file = new File(path, fileName);

            io.saveData(fileName,path,key,text,append);

            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
