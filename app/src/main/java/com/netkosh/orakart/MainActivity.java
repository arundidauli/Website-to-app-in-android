package com.netkosh.orakart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.viewanimator.ViewAnimator;


public class MainActivity extends AppCompatActivity {
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

        findViewById(R.id.txtAdminLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("type","admin");
                startActivity(intent);
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);

            }
        });
        findViewById(R.id.txtOther).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("type","other");
                startActivity(intent);
                overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
            }
        });


    }


}
