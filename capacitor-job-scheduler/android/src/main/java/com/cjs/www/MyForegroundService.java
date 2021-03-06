package com.cjs.www;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.cjs.www.capacitorjobscheduler.R;

public class MyForegroundService extends Service implements MyForegroundServiceInterface {

  public String ContentTitle;
  public String ContentText;

  @Override
  public String getContentTitle() {
    return ContentTitle;
  }

  @Override
  public String getContentText() {
    return ContentText;
  }

  @Override
  public void setContentTitle(String contentTitle) {
    ContentTitle = contentTitle;
  }

  @Override
  public void setContentText(String contentText) {
    ContentText = contentText;
  }






  @Override
    public int onStartCommand(Intent intent, int flags, int startId, String ContentTitle, String ContentText) {
        createNotificationChannel();

        Intent intent1 = new Intent(this, CapacitorJobScheduler.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent1,0);


        Notification notification = new NotificationCompat.Builder(this,"ChannelId1")
                .setContentTitle(ContentTitle)
                .setContentText(ContentText)
                .setSmallIcon(R.mipmap.ic_launcher)

                .setContentIntent(pendingIntent).build();


        startForeground(1,notification);

        Log.d("Service class", "onStartCommand: ");



        return START_STICKY;
    }

    private void createNotificationChannel() {


        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "ChannelId1","Foreground Notification", NotificationManager.IMPORTANCE_DEFAULT);


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void onDestroy() {

        stopForeground(true);
        stopSelf();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
