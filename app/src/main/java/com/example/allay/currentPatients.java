package com.example.allay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class currentPatients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_patients);

        ListView mListView = findViewById(R.id.patientList);

        ArrayList<patientData> patients = new ArrayList<>();
        patients.add(new patientData("Jake", "Thank you so much!", "drawable://" + R.drawable.patient1, "Active"));
        patients.add(new patientData("Emma", "I'm not sure about that...", "drawable://" + R.drawable.patient2, "Active"));
        patients.add(new patientData("Ava", "Yes, I had difficulties with that...", "drawable://" + R.drawable.patient3, "Busy"));
        patients.add(new patientData("Liam", "Thank you", "drawable://" + R.drawable.patient4, "Busy"));
        patients.add(new patientData("Oliver", "Amazing!", "drawable://" + R.drawable.patient5, "Busy"));
        patients.add(new patientData("Benjamin", "Thank you so much!", "drawable://" + R.drawable.patient1, "Active"));
        patients.add(new patientData("Charlotte", "I'm not sure about that...", "drawable://" + R.drawable.patient2, "Busy"));
        patients.add(new patientData("Sophia", "Yes, I had difficulties with that...", "drawable://" + R.drawable.patient3, "Active"));
        patients.add(new patientData("Jacob", "Thank you", "drawable://" + R.drawable.patient4, "Active"));
        patients.add(new patientData("Matthew", "Amazing!", "drawable://" + R.drawable.patient5, "Busy"));

        patientListAdapter adapter = new patientListAdapter(this, R.layout.adapter_view_layout, patients);
        mListView.setAdapter(adapter);
    }
}
