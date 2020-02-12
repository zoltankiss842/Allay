package com.example.soothe2;

import android.content.ContentProvider;
import android.content.Context;

import androidx.core.content.ContextCompat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.content.res.AssetManager;

public class checkUpCalculator {

    IO io = new IO();
    Context context;
    private double selfConfidence;
    private double decisionMaking;
    private double socialAttributes;
    private HashMap<Integer, String> radioButtonTexts;
    private HashMap<Integer, String> selfConfidenceQuestions;
    private HashMap<Integer, String> decisionMakingQuestions;
    private HashMap<Integer, String> socialAttributesQuestions;

    public checkUpCalculator(Context context){
        this.context = context;     // For file reading from assets
    }

    public HashMap<Integer, String> getRadioButtonTexts() {
        return radioButtonTexts;
    }

    public HashMap<Integer, String> getSelfConfidenceQuestions() {
        return selfConfidenceQuestions;
    }

    public HashMap<Integer, String> getDecisionMakingQuestions() {
        return decisionMakingQuestions;
    }

    public HashMap<Integer, String> getSocialAttributesQuestions() {
        return socialAttributesQuestions;
    }

    public void readQuestions(){
        radioButtonTexts = new HashMap<>();
        selfConfidenceQuestions = new HashMap<>();
        decisionMakingQuestions = new HashMap<>();
        socialAttributesQuestions = new HashMap<>();

        try{
            io.readDataIntegerKey(context.getAssets().open("radioButtonTexts.txt").toString(), radioButtonTexts);
            io.readDataIntegerKey(context.getAssets().open("selfConfidenceQuestions.txt").toString(), selfConfidenceQuestions);
            io.readDataIntegerKey(context.getAssets().open("decisionMakingQuestions.txt").toString(), decisionMakingQuestions);
            io.readDataIntegerKey(context.getAssets().open("socialAttributesQuestions.txt").toString(), socialAttributesQuestions);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
}
