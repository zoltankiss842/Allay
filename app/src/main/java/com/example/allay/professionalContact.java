package com.example.allay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class professionalContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_contact);

        final ListView mListView = findViewById(R.id.professionalList);

        ArrayList<patientData> patients = new ArrayList<>();
        patients.add(new patientData("Jake", "Occupational therapists", "drawable://" + R.drawable.prof2, "Active"));
        patients.add(new patientData("Emma", "Community mental health nurse", "drawable://" + R.drawable.prof3, "Active"));
        patients.add(new patientData("Ava", "Psychiatrist", "drawable://" + R.drawable.prof4, "Busy"));
        patients.add(new patientData("Harold", "Pain management", "drawable://" + R.drawable.prof1, "Busy"));
        patients.add(new patientData("Charlotte", "Mental health pharmacist", "drawable://" + R.drawable.prof6, "Active"));

        patientListAdapter adapter = new patientListAdapter(this, R.layout.adapter_view_layout, patients);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(professionalContact.this, professionalJake.class));
                        break;
                    case 1:
                        startActivity(new Intent(professionalContact.this, professionalEmma.class));
                        break;
                    case 2:
                        startActivity(new Intent(professionalContact.this, professionalAva.class));
                        break;
                    case 3:
                        startActivity(new Intent(professionalContact.this, professionalHarold.class));
                        break;
                    case 4:
                        startActivity(new Intent(professionalContact.this, professionalCharlotte.class));
                        break;
                }

            }
        });
    }
}
