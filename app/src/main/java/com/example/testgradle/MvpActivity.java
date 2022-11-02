package com.example.testgradle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MvpActivity extends AppCompatActivity implements View.OnClickListener, OnAdapterItemClickListener {
    private EditText phoneNumberEditText;
    private EditText writeMassageEditText;
    private Button datePickerButton;
    private Button timePickerButton;
    private Button sendButton;
    private EditText saveDateEditText;
    private EditText saveTimeEditText;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    class MyDate {
        int mYear;
        int mMonth;
        int mDay;
        int mHour;
        int mMinute;
    }
    private ImageView contactImageView;
    public static final String NUMBER = "contactName";
    public static final String MASSAGE = "text";
    public static final String TAG = "MvpActivity";
    public static  final String MESSAGE_STATUS = "messageStatus";
    final WorkManager showNotificationWorkManager = WorkManager.getInstance();
    final OneTimeWorkRequest showWorkManagerRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        findViews();
        onClicks();
    }
    private void findViews(){

        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        writeMassageEditText = findViewById(R.id.writeMassageEditText);
        datePickerButton = findViewById(R.id.datePickerButton);
        timePickerButton = findViewById(R.id.timePickerButton);
        sendButton = findViewById(R.id.btn_send);
        saveDateEditText = findViewById(R.id.saveDateEditText);
        saveTimeEditText = findViewById(R.id.saveTimeEditText);
        contactImageView= findViewById(R.id.contactImageView);
        datePickerButton.setOnClickListener((View.OnClickListener) this);
        timePickerButton.setOnClickListener((View.OnClickListener) this);
        sendButton.setOnClickListener((View.OnClickListener) this);
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
        if (view == datePickerButton) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dataPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    saveDateEditText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            dataPickerDialog.show();
        }
        if (view == timePickerButton) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    saveTimeEditText.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onAdapterItemClickListener(ContactModel contact) {
        Toast.makeText(this, "Your Contact Is Pick", Toast.LENGTH_SHORT).show();
        phoneNumberEditText.setText(contact.getNumber());
    }

    private boolean validateNumber() {
        String validate = phoneNumberEditText.getText().toString().trim();
        String namePattern = "@\"^09[0|1|2|3][0-9]{8}$\";";

        if (validate.isEmpty()) {
            phoneNumberEditText.setError("Field cannot be empty");
            return false;
        } else if (!validate.matches(namePattern)) {
            phoneNumberEditText.setError("Add Your Name");
            return false;
        } else {
            phoneNumberEditText.setError(null);
            return true;
        }
    }

    private boolean validateText() {

        String validate = writeMassageEditText.getText().toString().trim();
        String textPattern = "";
        if (validate.isEmpty()) {
            writeMassageEditText.setError("Field cannot empty");
            return false;
        } else if (!validate.matches(textPattern)) {
            writeMassageEditText.setError("Invalid email address");
            return false;
        } else {
            writeMassageEditText.setError(null);
            return true;
        }
    }
    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(MvpActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MvpActivity.this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(MvpActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(MvpActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        } else {

        }
    }
    private void onClicks(){
        contactImageView.setOnClickListener(new View.OnClickListener() {
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
        contactImageView.setOnClickListener(new View.OnClickListener() {
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
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationWorkManager.enqueue(showWorkManagerRequest);
                String Number = phoneNumberEditText.getText().toString();
                String Massage = writeMassageEditText.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(Number, null, Massage, null, null);
                    Toast.makeText(MvpActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MvpActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationWorkManager.enqueue(showWorkManagerRequest);
                String Number = phoneNumberEditText.getText().toString();
                String Massage = writeMassageEditText.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(Number, null, Massage, null, null);
                    Toast.makeText(MvpActivity.this, "Sent", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MvpActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        validateNumber();
        validateText();
        checkPermission();
    }
}


