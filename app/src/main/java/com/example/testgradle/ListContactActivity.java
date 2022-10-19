package com.example.testgradle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListContactActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    ArrayList<ContactModel>arrayList = new ArrayList<ContactModel>();
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        recycler_view = findViewById(R.id.recycler_view);
    }
}