package com.shakiba.xtranslation.surah;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shakiba.xtranslation.R;
import com.shakiba.xtranslation.base.BaseFragment;

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
}
