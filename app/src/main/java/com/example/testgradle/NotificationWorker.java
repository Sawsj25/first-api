package com.example.testgradle;


import android.app.NotificationManager;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationWorker extends Worker {
    private static final String WORK_RESULT = "work_result";
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data taskData = getInputData();
        String taskDataString = taskData.getString(MvpActivity.MASSAGE);
        showNotification("WorkManager",taskDataString !=null ? taskDataString :"Message has been Sent");
        Data outputData = new Data.Builder().putString(WORK_RESULT,"Job Finished").build();
        return Result.success(outputData);
    }
    private void showNotification (String task,String desc){
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId ="task_channel";
        String channelName = "task_name";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),channelId)
                .setContentTitle(task)
                 .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);
        manager.notify(1, builder.build());
    }
    WorkManager mworkmanager = WorkManager.getInstance();
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class).build();
}
