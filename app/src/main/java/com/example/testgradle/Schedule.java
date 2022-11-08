package com.example.testgradle;

import static com.example.testgradle.MvpActivity.MASSAGE;
import static com.example.testgradle.MvpActivity.TAG;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Schedule extends Worker  {

    public static final String MASSAGE = "Salam Emad Jan";
    private String number;
    public Schedule(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inputData = getInputData();
        String number = inputData.getString("number");
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage((String) number, null, MASSAGE, null, null);
        Log.d(TAG,"doWork:" + number);
        return  Result.success();
    }
}
