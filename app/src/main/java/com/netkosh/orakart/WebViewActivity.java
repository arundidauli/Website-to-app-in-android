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
    private Context mContext = this;
    private WebView wb;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    WebViewer("https://greengalaxy.in/");
                    return true;
                case R.id.navigation_gifts:
                    WebViewer("https://greengalaxy.in/wishlist/");
                    return true;
                case R.id.navigation_cart:
                    WebViewer("https://greengalaxy.in/cart/");
                    return true;
                case R.id.navigation_profile:
                    WebViewer("https://greengalaxy.in/my-account");
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        progressDialog = new ProgressDialog(mContext, R.style.Custom);
        wb = findViewById(R.id.webview);
        WebViewer("https://greengalaxy.in/");

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

        // progressDialog.setTitle(R.string.loading);
        progressDialog.show();
        wb.getSettings().setLoadsImagesAutomatically(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setSupportZoom(true);
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
                progressDialog.dismiss();

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog.show();
                super.onPageStarted(view, url, favicon);
            }
        });
        wb.loadUrl(url);
    }


}
