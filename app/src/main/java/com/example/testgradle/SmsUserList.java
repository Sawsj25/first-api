package com.example.testgradle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SmsUserList extends AppCompatActivity {
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private List<MyModel>myModelList;
    private CustomAdapter customAdapter;
    private TextView textView_number;
    private TextView textView_Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_list);
        displayItem();
    }
    private void displayItem(){

        recyclerView = findViewById(R.id.rvContacts);
        myModelList = new ArrayList<>();
        myModelList.add(new MyModel("Sajjad","26"));
        myModelList.add(new MyModel("ali","25"));

        customAdapter = new CustomAdapter(this,myModelList);
        recyclerView.setAdapter(customAdapter);

    }
}