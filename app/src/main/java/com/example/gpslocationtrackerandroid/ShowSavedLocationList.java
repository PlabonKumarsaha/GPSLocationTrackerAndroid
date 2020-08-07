package com.example.gpslocationtrackerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShowSavedLocationList extends AppCompatActivity {
    ListView lw_waypoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_location_list);

        MyApplication myApplication = (MyApplication) getApplicationContext();
        List<Location>savedLocation = myApplication.getMyLocation();

        lw_waypoints = findViewById(R.id.lw_waypoints);


        //default adapter to show the list!
        lw_waypoints.setAdapter(new ArrayAdapter<Location>(this,android.R.layout.simple_expandable_list_item_1,savedLocation));

    }
}