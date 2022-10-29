package com.example.testgradle;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DialogActivity extends MvpActivity {
    ArrayList<ContactModel> arrayList;
    OnAdapterItemClickListener callback;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.customDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.customview, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public DialogActivity(ArrayList<ContactModel> arrayList, OnAdapterItemClickListener callback) {
        this.arrayList = arrayList;
        this.callback = callback;
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged() {
    }
}
