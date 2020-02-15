package com.example.allay;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class firstTimeOpening2 extends AppCompatActivity {

    private String[] genderIdentity = new String[]{
            "Male (including transgender men)",
            "Female (including transgender women)",
            "Prefer to self describe as ____________ ",
            "Prefer not to say"
    };

    private String[] genderPronouns = new String[]{
            "he/him",
            "she/her",
            "[name]/[name]'s",
            "per/per",
            "sie/sir",
            "they/them",
            "ve/ver",
            "zie/zim"
    };

    private DatePickerDialog.OnDateSetListener dateListener;
    private String name;
    private IO io = new IO();
    private EditText dateEdit;

    private ConstraintLayout cl;                                // Background animation
    private AnimationDrawable ad;                               // Background animation

    private EditText genderSpecify;
    private Spinner genderSpin;
    private Spinner pronounSpin;

    private Button nextBtn2;

    private Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_opening2);

        animationStart();       // Starts animation

        // Checks if there is a "name" from firstTimeOpening
        if(getIntent().hasExtra("com.example.allay.FIRST2")){
            TextView namefTO = findViewById(R.id.namefTO);
            name = getIntent().getExtras().getString("com.example.allay.FIRST2");
            namefTO.setText(name);
        }

        // Initialising variables
        dateEdit = findViewById(R.id.dateEdit);
        genderSpecify = findViewById(R.id.genderSpecify);

        // Filling up gender options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderIdentity);
        genderSpin = findViewById(R.id.genderSpin);
        genderSpin.setAdapter(adapter);
        genderSpin.setSelection(0);

        // Filling up pronoun options
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderPronouns);
        pronounSpin = findViewById(R.id.pronounSpin);
        pronounSpin.setAdapter(adapter2);
        pronounSpin.setSelection(0);

        // Default genderSpecify is Invisible
        genderSpecify.setVisibility(View.INVISIBLE);

        // If tap on gender options and custom preference is set, then genderSpecify text will be VISIBLE
        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditText genderSpecify = findViewById(R.id.genderSpecify);
                if(genderSpin.getItemAtPosition(position).toString().equals(genderIdentity[2])){
                   genderSpecify.setVisibility(View.VISIBLE);
                }
                else{
                    genderSpecify.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Next button procedures
        nextBtn2 = findViewById(R.id.saveBtn);
        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText dateEdit = findViewById(R.id.dateEdit);
                EditText genderSpec = findViewById(R.id.genderSpecify);
                String wholeDate = dateEdit.getText().toString();
                if(wholeDate.isEmpty()){    // If date is empty, cannot proceed
                    Toast.makeText(firstTimeOpening2.this, "Date cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Preparation for data save
                String[] tokens = wholeDate.split("/");
                String year = tokens[2];
                String month = tokens[0];
                String day = tokens[1];
                String gender = genderSpin.getSelectedItem().toString();
                String pronoun = pronounSpin.getSelectedItem().toString();
                String genderSp = genderSpec.getText().toString();

                startSavingData("year",year, false);
                startSavingData("month",month, true);
                startSavingData("day",day, true);
                if(!genderSp.isEmpty()){
                    startSavingData("gender", Integer.toString(genderSpin.getSelectedItemPosition()), true);
                    if(!startSavingData("genderSpecify",genderSp, true)){
                        Toast.makeText(firstTimeOpening2.this, "Illegal character in gender specification", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    startSavingData("gender",Integer.toString(genderSpin.getSelectedItemPosition()), true);
                    startSavingData("genderSpecify","none", true);
                }


                startSavingData("pronoun",Integer.toString(pronounSpin.getSelectedItemPosition()), true);


                nextScreen();
                finish();
            }
        });

        // Calendar dialog listener
        cal = Calendar.getInstance();
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel(dateEdit, cal);
            }
        };

        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here we set the limits of the calendar
                DatePickerDialog datePickDiag = new DatePickerDialog(firstTimeOpening2.this, dateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                // TODO setMinDate as well
                long maxDate = System.currentTimeMillis() - 999999999; // TODO implement actual method
                datePickDiag.getDatePicker().setMaxDate(maxDate);
                datePickDiag.show();
            }
        });


    }

    private void updateLabel(EditText dateEdit, Calendar cal){
        // Correctly shows the date in EditText
        String format = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
        dateEdit.setText(sdf.format(cal.getTime()));
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

            String fileName = "user_data.txt";

            File file = new File(path, fileName);

            io.saveData(fileName,path,key,text);

            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


     private void nextScreen(){
          Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(startIntent);
     }

    private void animationStart(){
        cl = findViewById(R.id.layout);
        ad = (AnimationDrawable) cl.getBackground();
        ad.setEnterFadeDuration(4000);
        ad.setExitFadeDuration(4000);
        ad.start();
    }
}
