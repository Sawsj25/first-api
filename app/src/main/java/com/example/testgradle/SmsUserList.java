package com.example.testgradle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Modifier;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Random;

public class SmsUserList extends AppCompatActivity {
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private List<MyModel>myModelList;
    private CustomAdapter customAdapter;
    private TextView textView_number;
    private TextView textView_Name;
    WorkManager workManager;
    final TypedArray imgs= getResources().obtainTypedArray(R.array.random_images_array);
    final Random rand = new Random();
    final int rndInt=rand.nextInt(imgs.length());
    final int resID= imgs.getResourceId(rndInt,0);



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