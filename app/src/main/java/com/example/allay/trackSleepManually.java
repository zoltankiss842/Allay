package com.example.allay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class trackSleepManually extends AppCompatActivity {

    private Calendar c;
    private EditText wenttoBed;
    private EditText wokeUp;
    private EditText comments;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_sleep_manually);

        wenttoBed = findViewById(R.id.gotoBed);
        wokeUp = findViewById(R.id.wakeUp);
        comments = findViewById(R.id.comments);

        c = Calendar.getInstance();

        wenttoBed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        wenttoBed.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
                return true;
            }
        });

        wokeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        wokeUp.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        save = findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        wokeUp.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });



    }
}
