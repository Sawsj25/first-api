package com.example.testgradle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.Context;
import android.content.SharedPreferences;
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

public class SmsUserList extends AppCompatActivity {
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private List<MyModel>myModelList;
    private CustomAdapter customAdapter;
    private TextView textView_number;
    private TextView textView_Name;
    WorkManager workManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_list);
        displayItem();
        workManager = WorkManager.getInstance(this);
//        barrasi shavad class sharepref
//        workManager.getWorkInfoByIdLiveData(Spref.get("reqeustId")).observe(this, new Observer<WorkInfo>() {
//            @Override
//            public void onChanged(WorkInfo workInfo) {
//                if (workInfo != null) {
//                    Toast.makeText(SmsUserList.this, "Sms Is Scheduled", Toast.LENGTH_SHORT).show();
//                   return ;
//                }
//            }
//        });
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