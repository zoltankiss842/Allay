package com.example.allay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class sleepSleep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_sleep);

        LinearLayout letssleep = findViewById(R.id.letssleepContainer);
        LinearLayout dreamlog = findViewById(R.id.dreamlogContainer);
        LinearLayout trackSleepManual = findViewById(R.id.sleeptrackContainer);

        LineChart lineChart = findViewById(R.id.lineChart);

        List<Entry> entries = new ArrayList<>();
        Random rnd = new Random();

        ArrayList<String> labels = new ArrayList<String>();

        for(int i = 0; i < 30; ++i){
            entries.add(new Entry(rnd.nextFloat(), i));
            labels.add((i+1) + ". day");
        }

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(Color.parseColor("#ff189ad3"));
        dataSet.setLineWidth(3f);
        lineChart.setPinchZoom(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        dataSet.setCircleColor(Color.parseColor("#ff005073"));
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleRadius(4f);

        Legend legend = lineChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        LineData lineData = new LineData(labels, dataSet);
        lineData.addDataSet(dataSet);
        lineData.setHighlightEnabled(true);

        lineChart.setData(lineData);
        lineChart.setDescription("");
        lineChart.animateX(3000);
        lineChart.invalidate();

        letssleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), letsSleep.class));
            }
        });

        dreamlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dreamLog.class));
            }
        });

        trackSleepManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not yet implemented", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), trackSleepManually.class));
            }
        });

    }
}
