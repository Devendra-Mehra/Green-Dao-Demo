package com.example.greendaodemo.application;

import android.content.Context;

import com.example.greendaodemo.BuildConfig;
import com.facebook.stetho.Stetho;


public class Application extends android.app.Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    public static Context getContext() {
        return context;
    }

}
