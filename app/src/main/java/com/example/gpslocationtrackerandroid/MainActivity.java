package com.example.gpslocationtrackerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_lat,tv_labellon,tv_lon,tv_altitude,tv_accuracy;
    TextView tv_speed,tv_sensor,tv_updates;
    Switch sw_locationsupdates,sw_gps;
    TextView tv_address;
    View divider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_lat = findViewById(R.id.tv_lat);
        tv_labellon = findViewById(R.id.tv_labellon);
        tv_lon = findViewById(R.id.tv_lon);
        tv_altitude = findViewById(R.id.tv_altitude);
        tv_accuracy = findViewById(R.id.tv_accuracy);
        tv_speed = findViewById(R.id.tv_speed);
        tv_sensor = findViewById(R.id.tv_sensor);
        tv_updates= findViewById(R.id.tv_updates);

        sw_locationsupdates = findViewById(R.id.sw_locationsupdates);
        sw_gps = findViewById(R.id.sw_gps);

        tv_address = findViewById(R.id.tv_address);

        divider = findViewById(R.id.divider);





    }
}