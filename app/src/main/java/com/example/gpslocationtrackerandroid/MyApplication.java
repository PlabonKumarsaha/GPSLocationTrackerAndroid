package com.example.gpslocationtrackerandroid;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyApplication extends Application {

    private static MyApplication singleton;
    List<Locale> myLocation;

    public List<Locale> getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(List<Locale> myLocation) {
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
