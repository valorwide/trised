package com.shakiba.xtranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserLogInActivity extends AppCompatActivity {
    private int mood=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);
    }

    public void ConnecttoServer(View view) {
        if(mood==1){
            startActivity(new Intent(this,SurahActivity.class));
        }
        else {
            Intent intent=new Intent(UserLogInActivity.this,LogInActivity.class);
            intent.putExtra("mood",mood);
            finish();
            startActivity(intent);
        }

        finish();
    }
}
