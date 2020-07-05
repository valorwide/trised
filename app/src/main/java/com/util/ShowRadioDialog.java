package com.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.valorwide.trised.R;

public class ShowRadioDialog {
    private Context context;
    private Dialog dialog;
    private RadioButton publicRadioButton,privateRadioButton;
    private Button buttonOk,buttonCancel;
    private OnRadioButtonClick onRadioButtonClick;
    private int surahId;
    int buttonvalue=0;
    public ShowRadioDialog(@NonNull Context context) {
        this.context=context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buildDialog(context);
        }
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public void setOnRadioButtonClick(OnRadioButtonClick onRadioButtonClick) {
        this.onRadioButtonClick = onRadioButtonClick;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void buildDialog(final Context context)
    {
        dialog =new Dialog(context,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        dialog.setContentView(R.layout.language_selection_dialog);

        publicRadioButton=dialog.findViewById(R.id.radiopublic);
        publicRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Arabic", Toast.LENGTH_SHORT).show();
                buttonvalue=1;
            }
        });
        privateRadioButton=dialog.findViewById(R.id.radioprivate);
        privateRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(context,"Bangla",Toast.LENGTH_SHORT).show();
                buttonvalue=0;

            }
        });
        buttonOk=dialog.findViewById(R.id.ok);
        buttonCancel=dialog.findViewById(R.id.cancel);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRadioButtonClick!=null)
                {
                    onRadioButtonClick.onButtonClick(getSurahId(),buttonvalue);
                    dialog.dismiss();
                }

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //Toast.makeText(context,"Cancel",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void show()
    {
        if(dialog!=null){
            dialog.show();
        }
    }
    public interface OnRadioButtonClick
    {
        public void onButtonClick(int id,int lan);
    }

}
