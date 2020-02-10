package com.example.soothe2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class checkUp extends AppCompatActivity {

    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up);

        // Initializing variables
        startBtn = findViewById(R.id.startButton);

        // Starting the Questions
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(checkUp.this, questionsActivity.class));
            }
        });

        // TODO Previous results and graph of progress
    }
}
