package com.shakiba.xtranslation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shakiba.xtranslation.Retrofit.SurahModel;
import com.shakiba.xtranslation.surah.SurahFragment;
import com.util.ShowAlertDialog;
import com.util.ShowRadioDialog;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.CustomViewHolder> implements ShowRadioDialog.OnRadioButtonClick {
    private List<SurahModel> dataList;
    private Context context;
    private ShowRadioDialog showRadioDialog;
    private boolean flag = true;
    public OnClickAdapterItem onClickAdapterItem;

    public SurahAdapter(List<SurahModel> dataList, Context context, OnClickAdapterItem onClickAdapterItem) {
        this.dataList = dataList;
        this.context = context;
        showRadioDialog = new ShowRadioDialog(context);
        showRadioDialog.setOnRadioButtonClick(this);
        this.onClickAdapterItem = onClickAdapterItem;

    }

    @NonNull
    @Override
    public SurahAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_parent_surah, parent, false);
        SurahAdapter.CustomViewHolder holder = new SurahAdapter.CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SurahAdapter.CustomViewHolder holder, final int position) {
        final SurahModel surahModel = dataList.get(position);
        holder.textView1.setText(Html.fromHtml(surahModel.getTitle()));
        holder.textView2.setText(surahModel.getTitleBang());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRadioDialog.setSurahId(dataList.get(position).getId());
                showRadioDialog.show();
                if (flag == true) {
//                    SurahFragment fragmentB=new SurahFragment();
//                    Bundle bundle=new Bundle();
//                    bundle.putInt("id",surahModel.getId());
//                    fragmentB.setArguments(bundle);
//                    context.startActivity(new Intent(context, LogInActivity.class));

                }

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onButtonClick(int id,int lan) {


        onClickAdapterItem.onClickItem(id,lan);

    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2;
        CardView cardView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.arabic_title);
            textView2 = itemView.findViewById(R.id.bangla_title);
            cardView = itemView.findViewById(R.id.surahlistcardview);
        }

    }

    public interface OnClickAdapterItem {
        public void onClickItem(int id,int value);
    }
}
