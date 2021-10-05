package com.netkosh.orakart;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.multidex.MultiDex;


/**
 * Created by Arun Kumar on 14/6/20.
 * Copyright (c) 2020 wingshieldtechnologies.com All rights reserved.
 */

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    public static final String VERSION = "1.2.0";
    public static final String LOGIN_TYPE = "login_type";
    public static final String ADMIN = "admin";
    public static final String PARENT = "parent";
    private static AppController mInstance;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Log.i(AppController.TAG, "[BaseApplication] onCreate()");


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }





}
