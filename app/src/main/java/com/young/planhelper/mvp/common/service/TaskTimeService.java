package com.young.planhelper.mvp.common.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.common.model.NotificationInfo;
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

    @Override
    public void onCreate() {
        super.onCreate();
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

        long time = (long) mRealm.where(NotificationInfo.class).min("time");

        LogUtil.eLog("提醒时间为："+time);

        long current = TimeUtil.getCurrentTimeInLong();

        if( time >= current && time <= current + 60 ) {
            Intent broadcase = new Intent();
            broadcase.setAction("com.young.planhelper.mvp.common.service.AlarmReceiver");
            Bundle bundle = new Bundle();
            bundle.putString("message", "0");
            broadcase.putExtras(bundle);
            sendBroadcast(broadcase);
        }
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }
}
