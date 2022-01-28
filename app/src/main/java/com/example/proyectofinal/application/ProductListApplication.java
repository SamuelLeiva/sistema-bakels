package com.example.proyectofinal.application;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class ProductListApplication  extends Application {

    private static ProductListApplication instance;
    private static Context appContext;

    public static ProductListApplication getInstance() {return instance;}
    public static Context getAppContext() {return appContext;}
    public void setAppContext(Context mAppContext) {this.appContext = mAppContext;}

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


}
