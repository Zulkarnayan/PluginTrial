package com.cjs.www;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public interface MyForegroundServiceInterface {
    String getContentTitle();

    String getContentText();

    void setContentTitle(String contentTitle);

    void setContentText(String contentText);

    int onStartCommand(Intent intent, int flags, int startId, String ContentTitle, String ContentText);

    void onDestroy();

    @Nullable
    IBinder onBind(Intent intent);
}
