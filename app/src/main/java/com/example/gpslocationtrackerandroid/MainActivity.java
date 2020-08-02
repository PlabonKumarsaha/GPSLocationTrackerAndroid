package com.example.gpslocationtrackerandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_labellat,tv_lat,tv_labellon,tv_lon,tv_labelaltitude,tv_altitude,tv_labelaccuracy,tv_accuracy,tv_labelspeed;
    TextView tv_speed,tv_labelsensor,tv_sensor,tv_labelupdates,tv_updates;
    Switch sw_locationsupdates,sw_gps;
    TextView tv_address,tv_lbladdress;
    View divider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}