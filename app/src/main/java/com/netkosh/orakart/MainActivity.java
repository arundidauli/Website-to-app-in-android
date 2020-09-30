package com.netkosh.orakart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.viewanimator.ViewAnimator;
import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends AppCompatActivity {
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logo = findViewById(R.id.logo);

        ViewAnimator
                .animate(logo)
                .translationY(-1000, 0)
                .alpha(0, 1)
                .andAnimate()
                .dp().translationX(-20, 0)
                .decelerate()
                .duration(2000)
                .start();


        avi = findViewById(R.id.avi);
        startAnim();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), WebViewActivity.class));
                finish();
                stopAnim();
            }
        }, 4000);
    }

    void startAnim() {
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim() {
        avi.hide();
        // or avi.smoothToHide();
    }
}
