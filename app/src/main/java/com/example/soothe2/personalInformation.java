package com.example.soothe2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class personalInformation extends AppCompatActivity {

    MainActivity main = new MainActivity();
    HashMap<String, String> user_data;


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
    private IO io = new IO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        Intent intent = getIntent();
        user_data = (HashMap<String, String>)intent.getSerializableExtra("map");

        EditText yourNameEdit = (EditText) findViewById(R.id.yourNameEdit);
        yourNameEdit.setText(user_data.get("name"));

        final EditText dateEdit = (EditText) findViewById(R.id.dateEdit);
        dateEdit.setText(user_data.get("month") + "/" +
                user_data.get("day") + "/" +
                user_data.get("year"));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderIdentity);
        final Spinner genderSpin = (Spinner) findViewById(R.id.genderSpin);
        genderSpin.setAdapter(adapter);
        genderSpin.setSelection(Integer.parseInt(user_data.get("gender")));

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderPronouns);
        final Spinner pronounSpin = (Spinner) findViewById(R.id.pronounSpin);
        pronounSpin.setAdapter(adapter2);
        pronounSpin.setSelection(Integer.parseInt(user_data.get("pronoun")));

        EditText genderSpecify = (EditText) findViewById(R.id.genderSpecify);

        genderSpecify.setText(user_data.get("genderSpecify"));
        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditText genderSpecify = (EditText) findViewById(R.id.genderSpecify);
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


        Button nextBtn2 = (Button) findViewById(R.id.saveBtn);

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEdit = (EditText) findViewById(R.id.yourNameEdit);
                String name = nameEdit.getText().toString();
                EditText dateEdit = (EditText) findViewById(R.id.dateEdit);
                EditText genderSpec = (EditText) findViewById(R.id.genderSpecify);
                String wholeDate = dateEdit.getText().toString();

                String[] tokens = wholeDate.split("/");
                String year = tokens[2];
                String month = tokens[0];
                String day = tokens[1];
                String gender = genderSpin.getSelectedItem().toString();
                String pronoun = pronounSpin.getSelectedItem().toString();
                String genderSp = genderSpec.getText().toString();

                startSavingData("name", name, false);
                startSavingData("year",year, true);
                startSavingData("month",month, true);
                startSavingData("day",day, true);
                if(!genderSp.isEmpty()){
                    startSavingData("gender", Integer.toString(genderSpin.getSelectedItemPosition()), true);
                    startSavingData("genderSpecify",genderSp, true);
                }
                else{
                    startSavingData("gender",Integer.toString(genderSpin.getSelectedItemPosition()), true);
                    startSavingData("genderSpecify","none", true);
                }


                startSavingData("pronoun",Integer.toString(pronounSpin.getSelectedItemPosition()), true);

                Intent intent = new Intent(personalInformation.this, MainActivity.class);
                intent.putExtra("modified map", user_data);
                startActivity(intent);
                finish();


            }
        });

        final Calendar cal = Calendar.getInstance();

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
                new DatePickerDialog(personalInformation.this, dateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void updateLabel(EditText dateEdit, Calendar cal){
        String format = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);

        dateEdit.setText(sdf.format(cal.getTime()));
    }



    private void startSavingData(String key, String text, boolean append){


        try {

            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Soothe");
            if(!path.exists()){
                Toast.makeText(this, (path.mkdirs() ? "Directory has been created" : "Directory not created"),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }

            String fileName = "user_data.txt";

            File file = new File(path, fileName);

            io.saveData(fileName,path,key,text, append);

            Toast.makeText(this, "Saved to: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not save data.", Toast.LENGTH_SHORT).show();
        }
    }


}

