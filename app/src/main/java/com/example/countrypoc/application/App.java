package com.example.countrypoc.application;

import android.app.Application;

public class App extends Application {

    private static App mInstance;
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }
}
