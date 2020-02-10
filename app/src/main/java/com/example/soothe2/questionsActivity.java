package com.example.soothe2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class questionsActivity extends AppCompatActivity {

    //TODO result array, save at the end

    private String[] questions = {
            "I felt that my worry was out of my control.",
            "I was very anxious, worried or scared about a lot of things in my life.",
            "I was scared that I would lose control, go crazy or die."
    };

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView questionText;
    Button nextBtn;

    IO io = new IO();

    int nextQ = 0;
    int[] valuesOfQ = new int[questions.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        questionText = findViewById(R.id.questionText);

        radioGroup = findViewById(R.id.radioGroup);

        showNextQuestion();

        nextBtn = findViewById(R.id.button);

        if(nextQ == questions.length){
            nextBtn.setText("Finish");
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSavingData(true);
                showNextQuestion();
            }
        });

    }

    private void showNextQuestion() {

        if(nextQ < questions.length){
            questionText.setText(questions[nextQ]);
            nextQ++;

        }
        else if(nextQ == questions.length-1){
            questionText.setText(questions[nextQ]);
            nextQ++;
        }
        else{
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(questionsActivity.this);
        builder1.setTitle("Exit assessment");
        builder1.setMessage("Are you sure you want to quit? Your progress will not be saved.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(questionsActivity.this, MainActivity.class));
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void startSavingData(boolean append){

        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        int answer = radioGroup.indexOfChild(radioButton);

        try {

            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Soothe");
            if(!path.exists()){
                Toast.makeText(this, (path.mkdirs() ? "Directory has been created" : "Directory not created"),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }

            String fileName = "question_result.txt";

            File file = new File(path, fileName);

            io.saveData(fileName,path,Integer.toString(nextQ-1),Integer.toString(answer));

            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }
    }
}

