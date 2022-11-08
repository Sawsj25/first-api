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
        myModelList.add(new MyModel("ali","26"));
        myModelList.add(new MyModel("hasan","26"));
        myModelList.add(new MyModel("mohammad","26"));
        myModelList.add(new MyModel("g","24"));
        myModelList.add(new MyModel("q","23"));
        myModelList.add(new MyModel("w","22"));
        myModelList.add(new MyModel("e","21"));
        myModelList.add(new MyModel("r","20"));
        myModelList.add(new MyModel("t","19"));
        myModelList.add(new MyModel("y","18"));
        myModelList.add(new MyModel("u","17"));
        myModelList.add(new MyModel("i","16"));
        myModelList.add(new MyModel("o","15"));
        myModelList.add(new MyModel("p","14"));
        myModelList.add(new MyModel("a","13"));
        myModelList.add(new MyModel("s","12"));
        myModelList.add(new MyModel("d","11"));
        myModelList.add(new MyModel("f","10"));
        myModelList.add(new MyModel("h","9"));
        myModelList.add(new MyModel("j","8"));
        myModelList.add(new MyModel("k","7"));
        myModelList.add(new MyModel("l","6"));
        myModelList.add(new MyModel("z","5"));
        myModelList.add(new MyModel("x","4"));
        myModelList.add(new MyModel("c","3"));
        myModelList.add(new MyModel("v","2"));
        myModelList.add(new MyModel("b","1"));
        myModelList.add(new MyModel("n","26"));
        myModelList.add(new MyModel("m","26"));
        myModelList.add(new MyModel("re","26"));
        myModelList.add(new MyModel("ty","26"));
        myModelList.add(new MyModel("iu","26"));
        customAdapter = new CustomAdapter(this,myModelList);
        recyclerView.setAdapter(customAdapter);

    }
}