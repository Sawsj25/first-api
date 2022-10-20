package com.example.testgradle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    AppCompatActivity  activity;
    ArrayList<ContactModel> arrayList;
    OnAdapterItemClickListener callback;

    public MainAdapter(AppCompatActivity activity, ArrayList<ContactModel> arrayList, OnAdapterItemClickListener callback){
        this.activity = (AppCompatActivity) activity;
        this.arrayList = arrayList;
        this.callback = callback;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        ContactModel model = arrayList.get(position);

        holder.textviewName.setText(model.getName());
        holder.textviewNumber.setText(model.getNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onAdapterItemClickListener(model);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewName, textviewNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewName = itemView.findViewById(R.id.textView_Name);
            textviewNumber = itemView.findViewById(R.id. textView_number);
        }
    }
}
