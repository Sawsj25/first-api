package com.example.testgradle;

import static com.example.testgradle.MvpActivity.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.text.PrecomputedTextCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Schedule extends Worker  {


    public Schedule(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inputData = getInputData();
        int number = inputData.getInt("number",-1);
        Log.d(TAG,"doWork:" + number);

        for (int i=0;i < number;i++){
            Log.d(TAG, "doNewWork: doWork: i was:" +i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return  Result.success();
    }
}
