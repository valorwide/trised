package com.shakiba.xtranslation.surah;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shakiba.xtranslation.R;
import com.shakiba.xtranslation.Retrofit.GetDataService;
import com.shakiba.xtranslation.Retrofit.RetrofitClientInstance;
import com.shakiba.xtranslation.Retrofit.SurahDetailsModel;
import com.shakiba.xtranslation.Retrofit.SurahModel;
import com.shakiba.xtranslation.SurahActivity;
import com.shakiba.xtranslation.base.BaseFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahFragment extends BaseFragment {
    private View view;
    private TextView textView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_surah,container,false);
        textView=view.findViewById(R.id.surahText);
        progressBar=view.findViewById(R.id.progressBar);
        //int surah_id=getArguments().getInt("id");
        // surah details
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<SurahDetailsModel>> call = service.getSurahDetails();
        call.enqueue(new Callback<List<SurahDetailsModel>>() {
            @Override
            public void onResponse(Call<List<SurahDetailsModel>> call, Response<List<SurahDetailsModel>> response) {
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
            public void onFailure(Call<List<SurahDetailsModel>> call, Throwable t) {
                // progressDoalog.dismiss();
                Log.d("datacheck", "response error: "+t.getMessage());
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void setSurahText(String text) {
        if(!text.isEmpty())
        {
            textView.setText(text);
            stopProgress();
        }
    }

    @Override
    public void startProgress() {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
    }

    @Override
    public void stopProgress() {
        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
    }
    public void generateDataList(List<SurahDetailsModel> surahModelList)
    {
    }
}
