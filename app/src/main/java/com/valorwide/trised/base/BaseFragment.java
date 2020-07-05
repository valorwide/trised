package com.valorwide.trised.base;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public abstract void setSurahText(String text);
    public abstract void startProgress();
    public abstract void stopProgress();
}
