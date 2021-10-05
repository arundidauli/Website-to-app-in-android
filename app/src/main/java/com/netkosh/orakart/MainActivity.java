package com.netkosh.orakart;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.viewanimator.ViewAnimator;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

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


        onProfileImageClick();

    }


    public void onProfileImageClick() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            findViewById(R.id.staffLogin).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                                    intent.putExtra(AppController.LOGIN_TYPE, AppController.ADMIN);
                                    startActivity(intent);
                                }
                            });

                            findViewById(R.id.parentLogin).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                                    intent.putExtra(AppController.LOGIN_TYPE, AppController.PARENT);
                                    startActivity(intent);
                                }
                            });
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(MainActivity.this, "Need permission", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

}
