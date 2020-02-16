package com.example.allay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class checkUp extends AppCompatActivity {

    private Button startBtn;

    BarChart barChart;

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

        Random rnd = new Random();

        barChart = findViewById(R.id.barChart);

        barChart.setExtraOffsets(5f,10f, 5f,5f);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);


        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(rnd.nextFloat(),0));
        entries.add(new BarEntry(rnd.nextFloat(),1));
        entries.add(new BarEntry(rnd.nextFloat(),2));

        BarDataSet barDataSet = new BarDataSet(entries, "Calculated values");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Self Confidence");
        labels.add("Decision Making");
        labels.add("Social Attributes");

        BarData data = new BarData(labels, barDataSet);
        data.setValueTextSize(20f);
        barChart.setData(data);
        barDataSet.setValueTextSize(20f);

        barChart.setDescription("");

        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        barChart.animateY(2000);
    }
}
