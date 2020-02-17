package com.example.allay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class chatWithOthers extends AppCompatActivity {

    private ListView mListView;
    private Switch switchToAnon;
    private patientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_others);

        mListView = findViewById(R.id.currently_active_list);

        final ArrayList<patientData> patients = new ArrayList<>();
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

        adapter = new patientListAdapter(this, R.layout.adapter_view_layout, patients);
        mListView.setAdapter(adapter);

        switchToAnon = findViewById(R.id.switchAnon);

        switchToAnon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                addPatients(patients, isChecked);
                mListView.setAdapter(adapter);
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Switched to Anonymous server!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Switched to Normal server!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(chatWithOthers.this, ChatActivity.class);
                String nameOfUser = patients.get(position).getName();
                String imageOfUser = patients.get(position).getImgUrl();
                intent.putExtra("NameOfUser", nameOfUser);
                intent.putExtra("ImageOfUser", imageOfUser);
                startActivity(intent);
            }
        });
        

    }

    private void addPatients(ArrayList<patientData> list, boolean isAnon){
        list.clear();
        if(isAnon){
            list.add(new patientData("User_2365", "Start chat!", "drawable://" + R.drawable.patient, "Active"));
            list.add(new patientData("User_2342", "Start chat!", "drawable://" + R.drawable.patient, "Active"));
            list.add(new patientData("User_2335", "Start chat!", "drawable://" + R.drawable.patient, "Busy"));
            list.add(new patientData("User_2322", "Start chat!", "drawable://" + R.drawable.patient, "Busy"));
            list.add(new patientData("User_2327", "Start chat!", "drawable://" + R.drawable.patient, "Busy"));
            list.add(new patientData("User_2399", "Start chat!", "drawable://" + R.drawable.patient, "Active"));
            list.add(new patientData("User_2232", "Start chat!", "drawable://" + R.drawable.patient, "Busy"));
            list.add(new patientData("User_2653", "Start chat!", "drawable://" + R.drawable.patient, "Active"));
            list.add(new patientData("User_2357", "Start chat!", "drawable://" + R.drawable.patient, "Active"));
            list.add(new patientData("User_1337", "Start chat!", "drawable://" + R.drawable.patient, "Busy"));
        }
        else{
            list.add(new patientData("Jake", "Thank you so much!", "drawable://" + R.drawable.patient1, "Busy"));
            list.add(new patientData("Emma", "I'm not sure about that...", "drawable://" + R.drawable.patient2, "Busy"));
            list.add(new patientData("Ava", "Yes, I had difficulties with that...", "drawable://" + R.drawable.patient3, "Busy"));
            list.add(new patientData("Liam", "Thank you", "drawable://" + R.drawable.patient4, "Busy"));
            list.add(new patientData("Oliver", "Amazing!", "drawable://" + R.drawable.patient5, "Active"));
            list.add(new patientData("Benjamin", "Thank you so much!", "drawable://" + R.drawable.patient1, "Busy"));
            list.add(new patientData("Charlotte", "I'm not sure about that...", "drawable://" + R.drawable.patient2, "Busy"));
            list.add(new patientData("Sophia", "Yes, I had difficulties with that...", "drawable://" + R.drawable.patient3, "Active"));
            list.add(new patientData("Jacob", "Thank you", "drawable://" + R.drawable.patient4, "Active"));
            list.add(new patientData("Matthew", "Amazing!", "drawable://" + R.drawable.patient5, "Active"));
        }

    }
}
