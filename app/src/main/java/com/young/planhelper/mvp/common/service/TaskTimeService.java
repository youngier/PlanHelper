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

    private static final String TAG = "TaskTimeService";

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.eLog(TAG+"服务：onCreate");
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub

        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //读者可以修改此处的Minutes从而改变提醒间隔时间
        //此处是设置每隔90分钟启动一次
        //这是90分钟的毫秒数
        int Minutes = 3*6*1000;
        //SystemClock.elapsedRealtime()表示1970年1月1日0点至今所经历的时间
        long triggerAtTime = SystemClock.elapsedRealtime() + Minutes;
        //此处设置开启AlarmReceiver这个Service
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        //ELAPSED_REALTIME_WAKEUP表示让定时任务的出发时间从系统开机算起，并且会唤醒CPU。
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //在Service结束后关闭AlarmManager
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.cancel(pi);

    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // TODO Auto-generated method stub
//
//        NotificationInfo notificationInfo = mRealm.where(NotificationInfo.class).findFirst();
//        long time = 0;
//        if( notificationInfo != null)
//            time = (long) mRealm.where(NotificationInfo.class).min("time");
//
//
//        LogUtil.eLog("提醒时间为："+time);
//
//        long current = TimeUtil.getCurrentTimeInLong();
//
//        LogUtil.eLog("目标时间为："+time+" 当前时间为："+current);
//
//        if( time >= current + 5 * 60 * 1000 ) {
//            Intent pendingIntent = new Intent("com.young.planhelper.mvp.common.service.DIVIDE");
//            pendingIntent.putExtra("count", 10);
//            sendBroadcast(pendingIntent);
//        }
//
//        return super.onStartCommand(intent, flags, startId);
//    }

}
