package com.example.testgradle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView contact_name,contact_number;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        contact_name = itemView.findViewById(R.id.textView_Name);
        contact_number = itemView.findViewById(R.id.textView_number);
    }
}
