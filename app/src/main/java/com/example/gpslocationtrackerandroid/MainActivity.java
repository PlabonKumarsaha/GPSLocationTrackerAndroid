package com.example.gpslocationtrackerandroid;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_FINE_REQUEST = 99;
    TextView tv_lat, tv_labellon, tv_lon, tv_altitude, tv_accuracy;
    TextView tv_speed, tv_sensor, tv_updates;
    Switch sw_locationsupdates, sw_gps;
    TextView tv_address;
    View divider;
    final static int REFACTOR_UPDATE_INTERVAL = 1000;
    final static int FASTEST_UPDATE_INTERVAL = 1000;

    Button Btn_newWayPoint,Btn_showWayPoint;


    //Google map's api for location serice.majority of the features depend on this.
    FusedLocationProviderClient fusedLocationProviderClient;
    //Loacation request is a configurable file/settings related to FusedLocationProviderClient
    LocationRequest locationRequest;

    //location call back instance
    LocationCallback locationCallBack;

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
        tv_updates = findViewById(R.id.tv_updates);

        sw_locationsupdates = findViewById(R.id.sw_locationsupdates);
        sw_gps = findViewById(R.id.sw_gps);

        tv_address = findViewById(R.id.tv_address);

        divider = findViewById(R.id.divider);

        //initialze the buttons of way points
        Btn_newWayPoint = findViewById(R.id.Btn_newWayPoint);
        Btn_showWayPoint = findViewById(R.id.Btn_showWayPoint);

        //set properties of location
        locationRequest = new LocationRequest();
        locationRequest.setInterval(REFACTOR_UPDATE_INTERVAL * 30);
        //if we are using maximum power then how often we want to update the location
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL * 5);

        //this method is triggered when location is updated(the updated time)
        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();
                updateUiValues(location);
            }

        };

        //set priority about the service
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw_gps.isChecked()) {
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensor...");
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("using WIFI + tower..");
                }
            }
        });

        sw_locationsupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sw_locationsupdates.isChecked()) {
                    //turn on tacking
                    startLocatonUpdates();
                } else {
                    //turn off racking
                    stopLocationUpdates();
                }
            }
        });

        //call the update GPS method

        updateGPS();

    }

    private void stopLocationUpdates() {


        tv_updates.setText("locaton not being tracked!");
        tv_lat.setText("not tracking!");
        tv_updates.setText("not tracking");
        tv_altitude.setText("not tracking");
        tv_lon.setText("not tracking!");
        tv_accuracy.setText("not tracking");
        tv_speed.setText("no tracking!");
        //no longer updating the locationcall back!
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);

    }


    private void startLocatonUpdates() {
        tv_updates.setText("locaton   tracked!");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_FINE_REQUEST :
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    updateGPS();
                } else{
                    Toast.makeText(getApplicationContext(),"Must need permission acess to use ",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    void updateGPS(){

            //permit from the users to track GPS
            //get the current location from the fused client
            // update the UI to show the values in the screen
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                //user provided the permit
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                     //we got permission.Now put the values of the location there.
                    updateUiValues(location);

                    }
                });

            }else{

                //if permission is deined the again ask for permission .But now we have to look for mobile versions
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_FINE_REQUEST);
                }
            }
        }

    private void updateUiValues(Location location) {
        //update all the text view objects
        tv_lat.setText(String.valueOf(location.getLatitude()));
        tv_lon.setText(String.valueOf(location.getLongitude()));
        tv_accuracy.setText(String.valueOf(location.getAccuracy()));

        //check if the phone has altitude ..so check if we can get the altitude

        if(location.hasAltitude()){
            tv_altitude.setText(String.valueOf(location.getAltitude()));
        } else{
            tv_altitude.setText("Not availabe in your phone!");
        }

        if(location.hasSpeed()){
            tv_speed.setText(String.valueOf(location.getSpeed()));
        } else{
            tv_speed.setText("Not availabe in your phone!");
        }
        Geocoder geocoder = new Geocoder(MainActivity.this);
        try{
            //list of most recently seen adresses.so take the values in a adress list.
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            tv_address.setText(addresses.get(0).getAddressLine(0).toString()+",locality : "+addresses.get(0).getLocality()+",COUNTRY :"+addresses.get(0).getCountryName());

        }catch (Exception e){

            tv_address.setText("failed to get location!");
        }
    }
}