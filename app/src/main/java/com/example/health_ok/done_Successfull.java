package com.example.health_ok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

public class done_Successfull extends AppCompatActivity {
    private int splashtime =2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_successfull);
        Handler hi = new Handler();
        hi.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(done_Successfull.this,consultdoctor.class));
                finish();
            }
        },splashtime);
    }
}