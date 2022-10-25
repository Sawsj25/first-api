package com.example.testgradle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MvpActivity extends AppCompatActivity implements View.OnClickListener, OnAdapterItemClickListener {
    private Button btnDatePicker;
    private Button btnTimePicker;
    private Button btn_send;
    private EditText txtDate;
    private EditText txtTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int  mMinute ;
    private ImageView imageView_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        btnDatePicker =  findViewById(R.id.btn_date);
        btnTimePicker =  findViewById(R.id.btn_time);
        btn_send=findViewById(R.id.btn_send);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        imageView_contact = findViewById(R.id.imageView_contact);
        btnDatePicker.setOnClickListener((View.OnClickListener) this);
        btnTimePicker.setOnClickListener((View.OnClickListener) this);
        btn_send.setOnClickListener((View.OnClickListener)this);
        imageView_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (ContextCompat.checkSelfPermission(MvpActivity.this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    ContactListDialog dialog = new ContactListDialog(MvpActivity.this,MvpActivity.this);
                    dialog.show();
                } else {
                   requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, 112);
                }

          }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 112 && resultCode == RESULT_OK){
                ContactListDialog dialog = new ContactListDialog(MvpActivity.this,MvpActivity.this);
                dialog.show();
        } else {
            Toast.makeText(this, "need permission oskol", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View view) {
        if (view == btnDatePicker) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dataPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            dataPickerDialog.show();
        }
        if (view == btnTimePicker) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    txtTime.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onAdapterItemClickListener(ContactModel contact) {

    }
}

