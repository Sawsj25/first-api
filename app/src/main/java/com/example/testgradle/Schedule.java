package com.example.testgradle;

import static com.example.testgradle.MvpActivity.TAG;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
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
        String number = inputData.getString("number");
        String text = inputData.getString("text");
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null ,text, null, null);
        Log.d(TAG,"doWork:" + number + text);
        showNotification("notification","Your Sms has been Sent");
        return  Result.success();

    }
    public void showNotification(String title , String des){
        NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE );
        String channelid="";
        if (Build.VERSION.SDK_INT>=26){
            NotificationChannel channel=new NotificationChannel(channelid,"home",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notificationCompat = new
                NotificationCompat.Builder(getApplicationContext(),channelid)
                .setContentText(des)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher);
        notificationManager.notify(1,notificationCompat.build());

    }
}
