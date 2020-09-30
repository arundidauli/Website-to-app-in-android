package com.netkosh.orakart;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.multidex.MultiDex;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Arun Kumar on 14/6/20.
 * Copyright (c) 2020 wingshieldtechnologies.com All rights reserved.
 */

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    public static final String VERSION = "1.2.0";
    // Refer to "https://github.com/sendbird/quickstart-calls-android".
    public static final String APP_ID = "3CC77F22-2E60-48D8-B59D-1C9967628AA7";
    private static AppController mInstance;
    private RequestQueue mRequestQueue;
    private SharedPreferences sharedPreferences;

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

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }

    // private HttpProxyCacheServer proxy;

    public void cancelPendingRequests(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

   /* public static HttpProxyCacheServer getProxy(Context context) {
        AppController app = (AppController) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }*/

    public <T> void addToRequestQueue(Request<T> req) {
        if (isOnline()) {
            req.setRetryPolicy(new DefaultRetryPolicy(
                    60000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            req.setTag(TAG);
            getRequestQueue().add(req);
        } else {
            Toast.makeText(getApplicationContext(), "Unable to connect to server", Toast.LENGTH_LONG).show();
        }
    }


    //Method to get sharedpreferences
    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null)
            sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    //This method will clear the sharedpreference
    //It will be called on logout
    public void logout() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }


}
