package com.shakiba.xtranslation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shakiba.xtranslation.Retrofit.GetDataService;
import com.shakiba.xtranslation.Retrofit.RetrofitClientInstance;
import com.shakiba.xtranslation.Retrofit.SurahModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahActivity extends AppCompatActivity implements SurahAdapter.OnClickAdapterItem {
    private SurahAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        service.getAllSurah().enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Log.d("chkstring",""+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
     //   });
        Call<List<SurahModel>> call = service.getAllSurah();
        call.enqueue(new Callback<List<SurahModel>>() {
            @Override
            public void onResponse(Call<List<SurahModel>> call, Response<List<SurahModel>> response) {
               // progressDoalog.dismiss();
                if(response.isSuccessful())
                {

                    Log.d("datacheck", "response successful: "+response.body());
                    generateDataList(response.body());
                }
                else
                {
                    Log.d(" ", "response error: "+response.code());
                }


            }

            @Override
            public void onFailure(Call<List<SurahModel>> call, Throwable t) {
               // progressDoalog.dismiss();
                Log.d("datacheck", "response error: "+t.getMessage());
                Toast.makeText(SurahActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<SurahModel> surahModelList) {
        recyclerView = findViewById(R.id.recycleview_surah);
        adapter = new SurahAdapter(surahModelList,this,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SurahActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickItem(int id,int lan) {
        Intent intent=new Intent(SurahActivity.this,LogInActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("lan",lan);

        finish();
        startActivity(intent);
    }
}
