package com.example.allay;

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
import java.util.HashMap;
import java.util.Map;

public class questionsActivity extends AppCompatActivity {

    //TODO result array, save at the end

    checkUpCalculator checkUpCalculator = new checkUpCalculator(this);


    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView questionText;
    Button nextBtn;
    private String[] questions = {
            "I was very anxious, worried or scared about a lot of things in my life.",
            "I felt my worry was out of control.",
            "I felt restless, agitated, frantic or tense.", //selfConfidence
            "Potential negative consequences keep me from taking action.",
            "Before making a decision, I try to anticipate the factors that could influence the outcome.",
            "I am a \"chicken\".",
            "I am afraid that something unexpected might ruin all my plans.",
            "I check important projects carefully for mistakes before submitting them.",
            "Before making a risky business decision, I do some research to determine whether it's the right path to take.",
            "Before making a final decision, I make sure to have a Plan B in case something goes wrong.",
            "I consider the pros and cons of a decision before I proceed.",
            "I remain calm and relaxed in situations in which most people would become fearful, upset, or stressed.",
            "Given the choice, I would prefer to defer risky decisions to others.",
            "I_____ look for ways to spice up my daily routine.",
            "Others tell me that my actions are \"crazy\".",
            "I realize that past actions were more dangerous than I thought at the time.", //decisionMaking
            "When I talk to someone, I try to put myself in his/her shoes.",
            "When I approach someone for conversation, I adjust to his/her level (I sit if he/she is sitting or stand if he/she is standing).",
            "I say or do insensitive things that upset my friends/co-workers.",
            "People tell me that I behave inappropriately in social situations.",
            "I consider how others will be affected by my words and actions.",
            "I explain my ideas clearly.",
            "During conversations, people tell me that I don't look like I am interested in what they are saying.",
            "I find myself snapping at others when I am feeling stressed." //social
    };

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
                radioGroup.clearCheck();
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
            calculateGraphData();
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

            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Allay");
            if(!path.exists()){
//                Toast.makeText(this, (path.mkdirs() ? "Directory has been created" : "Directory not created"),Toast.LENGTH_SHORT).show();
            }
            else{
//                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }

            String fileName = "question_result.txt";

            File file = new File(path, fileName);

            io.saveData(fileName,path,Integer.toString(nextQ-1),Integer.toString(answer));

//            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }
    }

    private double[] calculateGraphData(){
        HashMap<String, String> map = new HashMap<>();
        double[] array = new double[3];

        try {

            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Allay");
            if(!path.exists()){
//                Toast.makeText(this, (path.mkdirs() ? "Directory has been created" : "Directory not created"),Toast.LENGTH_SHORT).show();
            }
            else{
//                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }

            String fileName = "question_result.txt";

            File file = new File(path, fileName);

            io.readDataStringKey(file.toString(),map);

            double confidence = 0;
            double decision = 0;
            double social = 0;

            for(Map.Entry<String, String> temp : map.entrySet()){
                int key = Integer.parseInt(temp.getKey());
                if(key<3){
                    confidence += Integer.parseInt(temp.getValue());
                }
                else if(key >= 3 && key <= 15){
                    decision += Integer.parseInt(temp.getValue());
                }
                else{
                    social += Integer.parseInt(temp.getValue());
                }
            }

            confidence *= 0.125;
            decision *= 0.542;
            social *= 0.333;

            array[0] = confidence;
            array[1] = decision;
            array[2] = social;

//            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }

        //TODO return array to checkUp and make a graph
        //TODO How to make graph
        return array;
    }
}

