package com.example.gpslocationtrackerandroid;

import android.app.Application;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyApplication extends Application {

    private static MyApplication singleton;
    List<Location> myLocation;

    public List<Location> getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(List<Location> myLocation) {
        this.myLocation = myLocation;
    }

    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        myLocation = new ArrayList<>();
    }
}
