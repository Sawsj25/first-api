package com.example.testgradle;

import android.app.Activity;
import android.provider.ContactsContract;
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

    public MainAdapter (AppCompatActivity activity,ArrayList<ContactModel> arrayList){
        this.activity = (AppCompatActivity) activity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel model = arrayList.get(position);

        holder.textView_name.setText(model.getName());
        holder.textView_number.setText(model.getNumber());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_name , textView_number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_Name);
            textView_name = itemView.findViewById(R.id. textView_number);
        }
    }
}
