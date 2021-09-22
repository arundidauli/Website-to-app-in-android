package com.netkosh.orakart;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class WebViewActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    private ProgressDialog progressDialog;
    private final Context mContext = this;
    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        wb = findViewById(R.id.web_view);
        if (getIntent().getStringExtra("type").equals("admin")){
            WebViewer("https://gurukulpublicschool.thewingshield.com/site/login");
        }else {
            WebViewer("https://gurukulpublicschool.thewingshield.com/site/userlogin");
        }


    }

    @Override
    public void onBackPressed() {
        if (wb.canGoBack()) {
            wb.goBack();
        } else {
            super.onBackPressed();
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);


    }

    @SuppressLint("SetJavaScriptEnabled")
    public void WebViewer(String url) {


        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setSupportZoom(true);
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setUseWideViewPort(true);

        wb.getSettings().setBuiltInZoomControls(true);
        wb.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progressDialog.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
        wb.loadUrl(url);
    }


}
