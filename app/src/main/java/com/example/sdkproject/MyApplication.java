package com.example.sdkproject;

import android.app.Application;

import com.example.analyticslibrary.AnalyticsSDK;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Analytics SDK
        AnalyticsSDK.initialize(this, "http://10.0.2.2:5000/", "homiz");
    }
}
