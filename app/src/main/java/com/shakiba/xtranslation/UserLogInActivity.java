package com.shakiba.xtranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.shakiba.xtranslation.Retrofit.GetDataService;
import com.shakiba.xtranslation.Retrofit.RetrofitClientInstance;
import com.shakiba.xtranslation.Retrofit.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogInActivity extends AppCompatActivity {
    private int mood=1;
    private EditText usernameText;
    private EditText passwordText;
    private GetDataService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);
        usernameText=findViewById(R.id.username);
        passwordText=findViewById(R.id.password);
        mService= RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

    }

    public void ConnecttoServer(View view) {
       String username= usernameText.getText().toString().trim();
       String password= passwordText.getText().toString().trim();
       if(!username.isEmpty()&&!password.isEmpty()){
           mService.userAUth().enqueue(new Callback<UserLogin>() {
               @Override
               public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                   if(response.isSuccessful())
                   {
                       UserLogin login=response.body();
                       if(login.getResult()==true)
                           mood=1;
                       else {
                           mood=2;
                       }
                       checkMode(mood);
                   }
                   else {

                   }
               }

               @Override
               public void onFailure(Call<UserLogin> call, Throwable t) {

               }
           });
       }
       else {
           usernameText.setError("Please enter username");
           passwordText.setError("Please enter password");
       }

    }
    private void checkMode(int mood){
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
