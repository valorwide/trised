package com.shakiba.xtranslation;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shakiba.xtranslation.Retrofit.SurahModel;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.CustomViewHolder> {
    private List<SurahModel> dataList;
    private Context context;

    public SurahAdapter(List<SurahModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public SurahAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_parent_surah,parent,false);
        SurahAdapter.CustomViewHolder holder=new SurahAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SurahAdapter.CustomViewHolder holder, int position) {
        SurahModel surahModel=(SurahModel) dataList.get(position);
        holder.textView1.setText(Html.fromHtml(surahModel.getTitle()));
        holder.textView2.setText(surahModel.getTitleBang());
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    static class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.arabic_title);
            textView2=itemView.findViewById(R.id.bangla_title);
        }

    }

}
