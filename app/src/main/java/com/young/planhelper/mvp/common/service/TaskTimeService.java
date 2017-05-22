package com.young.planhelper.mvp.common.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.common.model.NotificationInfo;
import com.young.planhelper.mvp.home.HomeCloneActivity;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.sql.Time;

import io.realm.Realm;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/12/19  22:41
 */


public class TaskTimeService extends Service{

    private Realm mRealm;

    private static final String TAG = "TaskTimeService";

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.eLog(TAG+"服务：onCreate");
        mRealm = AppApplication.get(getApplicationContext()).getmAppComponent().getRealm();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub

        LogUtil.eLog(TAG+"服务：onStart");

        if( mRealm == null )
            LogUtil.eLog(TAG+"服务：Realm为空");

        NotificationInfo notificationInfo = mRealm.where(NotificationInfo.class).findFirst();
        long time = 0;
        if( notificationInfo != null)
            time = (long) mRealm.where(NotificationInfo.class).min("time");


        LogUtil.eLog("提醒时间为："+time);

        long current = TimeUtil.getCurrentTimeInLong();

        LogUtil.eLog("目标时间为："+time+" 当前时间为："+current);

        if( time >= current + 5 * 60 * 1000 ) {
            Intent pendingIntent = new Intent("com.young.planhelper.mvp.common.service.DIVIDE");
            intent.putExtra("count", 10);
            sendBroadcast(intent);
        }
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }
}
