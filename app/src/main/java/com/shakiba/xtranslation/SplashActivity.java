package com.shakiba.xtranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {

                    startActivity(new Intent(SplashActivity.this,UserLogInActivity.class));

               finish();
            }
        }.start();


    }
}
