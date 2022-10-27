package com.example.testgradle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class MvpActivity extends AppCompatActivity implements View.OnClickListener, OnAdapterItemClickListener  {
    private EditText ediText_to;
    private EditText editText_massage;
    private Button btnDatePicker;
    private Button btnTimePicker;
    private Button btn_send;
    private EditText txtDate;
    private EditText txtTime;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private ImageView imageView_contact;
    public static final String Number = "contactname";
    public static final String Massage = "text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        if (ContextCompat.checkSelfPermission(MvpActivity.this,
                Manifest.permission.SEND_SMS) !=PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MvpActivity.this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(MvpActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }else{
                ActivityCompat.requestPermissions(MvpActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},1);
            }
        }else{
//            nothing
        }
        ediText_to = findViewById(R.id.ediText_to);
        editText_massage = findViewById(R.id.editText_massage);
        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        btn_send = findViewById(R.id.btn_send);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        imageView_contact = findViewById(R.id.imageView_contact);
        btnDatePicker.setOnClickListener((View.OnClickListener) this);
        btnTimePicker.setOnClickListener((View.OnClickListener) this);
        btn_send.setOnClickListener((View.OnClickListener) this);
        imageView_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MvpActivity.this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    ContactListDialog dialog = new ContactListDialog(MvpActivity.this, MvpActivity.this);
                    dialog.show();
                } else {
                    requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, 112);
                }

            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Number =ediText_to.getText().toString();
                String Massage = editText_massage.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(Number,null,Massage,null,null);
                    Toast.makeText(MvpActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MvpActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        validateNumber();
        validateText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 112 && resultCode == RESULT_OK) {
            ContactListDialog dialog = new ContactListDialog(MvpActivity.this, MvpActivity.this);
            dialog.show();
        } else {
            Toast.makeText(this, "need permission", Toast.LENGTH_SHORT).show();
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
    public void onAdapterItemClickListener(ContactModel contact)  {
        Toast.makeText(this, "Your Contact Is Pick", Toast.LENGTH_SHORT).show();
        ediText_to.setText(contact.getNumber());
    }

    private boolean validateNumber() {
        String validate = ediText_to.getText().toString().trim();
        String namePattern =  "@\"^09[0|1|2|3][0-9]{8}$\";";

        if (validate.isEmpty()) {
            ediText_to.setError("Field cannot be empty");
            return false;
        } else if (!validate.matches(namePattern)) {
            ediText_to.setError("Add Your Name");
            return false;
        } else {
            ediText_to.setError(null);
            return true;
        }
    }
    private boolean validateText(){

        String validate = editText_massage.getText().toString().trim();
        String textPattern = "";
        if (validate.isEmpty()) {
            editText_massage.setError("Field cannot empty");
            return false;
        } else if (!validate.matches(textPattern)) {
            editText_massage.setError("Invalid email address");
            return false;
        } else {
            editText_massage.setError(null);
            return true;
        }


    }

}

