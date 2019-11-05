package com.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.shakiba.xtranslation.R;

public class ShowAlertDialog  {
    private Context context;
    EditText usernametext,adresstext,porttext;
    private Dialog dialog;
    private Button buttonOk,buttonCancel;
    private OnRadioButtonClick onRadioButtonClick;
    int buttonvalue=0;
    public ShowAlertDialog(@NonNull Context context) {
        this.context=context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buildDialog(context);
        }
    }

    public void setOnRadioButtonClick(OnRadioButtonClick onRadioButtonClick) {
        this.onRadioButtonClick = onRadioButtonClick;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void buildDialog(Context context)
    {
        dialog =new Dialog(context,android.R.style.Theme_Material_Light_Dialog_Alert);
        dialog.setContentView(R.layout.login_dialog);

        buttonOk=dialog.findViewById(R.id.login);
        buttonCancel=dialog.findViewById(R.id.logout);
        usernametext=(EditText)dialog.findViewById(R.id.username);
        adresstext=(EditText)dialog.findViewById(R.id.adresstext);
        porttext=(EditText)dialog.findViewById(R.id.porttext);
        buttonOk.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onRadioButtonClick!=null)
            {
                String username=usernametext.getText().toString().trim();
                String port=porttext.getText().toString().trim();
                String addr=adresstext.getText().toString().trim();

                if(!username.isEmpty()||!port.isEmpty()||!addr.isEmpty()) {
                    onRadioButtonClick.onButtonClick(usernametext.getText().toString().trim(), adresstext.getText().toString().trim(), Integer.parseInt(porttext.getText().toString().trim()));

                    dialog.dismiss();
                }
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
        public void onButtonClick(String username,String address,int port);
    }


}