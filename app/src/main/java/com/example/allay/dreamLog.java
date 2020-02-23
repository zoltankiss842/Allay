package com.example.allay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.service.dreams.DreamService;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class dreamLog extends AppCompatActivity {

    HashMap<String, String> map;
    private LinearLayout newDream;
    private ArrayList<dreamData> list;
    private IO io;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_log);

        io = new IO();

        map = new HashMap<>();

        newDream = findViewById(R.id.newDream);
        ListView mListView = findViewById(R.id.dreamLists);
        list = new ArrayList<>();

        startReadingData(map);

        if(!map.isEmpty()){
            for(HashMap.Entry<String, String> temp : map.entrySet()){
                list.add(new dreamData(temp.getKey(),temp.getValue()));
            }
        }
        else{
            Toast.makeText(this,"No entries yet...",Toast.LENGTH_SHORT).show();
        }


//        list.add(new dreamData("Met Samuel L. Jackson", "I said “my man” to him like he does in his movies and he became ..."));
//        list.add(new dreamData("Dreamt about a puppy", "I had a disturbing dream last night where I was minding a German Shepherd puppy ..."));
//        list.add(new dreamData("Space/Planets", "For some reason I have no ceiling or roof (I didn’t think anything weird of it in the dream) and I could very  ..."));
//        list.add(new dreamData("A very strange dream", "It started I was on a balcony of this massive red monolith coming out of some fiery void  ..."));
//        list.add(new dreamData("Met Samuel L. Jackson", "I said “my man” to him like he does in his movies and he became ..."));
//        list.add(new dreamData("Dreamt about a puppy", "I had a disturbing dream last night where I was minding a German Shepherd puppy ..."));
//        list.add(new dreamData("Space/Planets", "For some reason I have no ceiling or roof (I didn’t think anything weird of it in the dream) and I could very  ..."));
//        list.add(new dreamData("A very strange dream", "It started I was on a balcony of this massive red monolith coming out of some fiery void  ..."));

        dreamListAdapter adapter = new dreamListAdapter(this,R.layout.dream_adapter_view_layout, list);
        mListView.setAdapter(adapter);

        newDream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), logADream.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean startReadingData(HashMap<String, String> map){

        try {

            File path = new File(Environment.getExternalStorageDirectory() + File.separator + "Allay");
            if(!path.exists()){
//                Toast.makeText(this,"No entries yet...",Toast.LENGTH_SHORT).show();
            }
            else{
//                Toast.makeText(this, "Directory exists", Toast.LENGTH_SHORT).show();
            }

            String fileName = "dream_log.txt";

            File file = new File(path, fileName);

            io.readDataStringKey(file.toString(),map);

//            Toast.makeText(this, "Read from: " + path, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Could not read data.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
