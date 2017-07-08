package com.young.planhelper.mvp.common.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.young.planhelper.R;
import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.common.model.NotificationInfo;
import com.young.planhelper.util.TimeUtil;

import java.lang.reflect.Method;

import io.realm.Realm;

public class AlarmReceiver extends BroadcastReceiver{

    private final static String DIVIDE_RESULT="com.young.planhelper.mvp.common.service.DIVIDE";

    @Override
    public void onReceive(Context context, Intent intent) {

        Realm mRealm = AppApplication.get(context).getmAppComponent().getRealm();

        if( mRealm != null ){
            NotificationInfo notificationInfo = mRealm.where(NotificationInfo.class).findFirst();
            long time = 0;
            if( notificationInfo != null)
                time = (long) mRealm.where(NotificationInfo.class).min("time");

            long current = TimeUtil.getCurrentTimeInLong();

            if( time >= current + 5 * 60 * 1000  && time <= current + 10 * 60 * 1000) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification mNotification=new Notification(R.drawable.ic_launcher,"用电脑时间过长了！白痴！"
                        ,System.currentTimeMillis());
                if (Build.VERSION.SDK_INT <16) {
                    Class clazz = mNotification.getClass();
                    try {
                        Method m2 = clazz.getDeclaredMethod("setLatestEventInfo", Context.class,CharSequence.class,CharSequence.class,PendingIntent.class);
                        m2.invoke(mNotification, context, "任务提醒",
                                "您有任务即将到期", null);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }

//                  mNotification.setLatestEventInfo(mContext, mContentTitle,
//                      mContentTitle, mContentIntent);
                }
                else
                {
                    mNotification = new Notification.Builder(context)
                            .setAutoCancel(true)
                            .setContentTitle("任务提醒")
                            .setContentText("您有一条任务即将到期")
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setWhen(System.currentTimeMillis())
                            .build();
                }

                mRealm.beginTransaction();
                notificationInfo.deleteFromRealm();
                mRealm.commitTransaction();
                mNotification.defaults = Notification.DEFAULT_ALL;
                manager.notify(1, mNotification);
            }
        }
//        String action = intent.getAction();
//        if(action.equals(DIVIDE_RESULT))
//        {
//            int count = intent.getIntExtra("count", 10);
//            LogUtil.eLog("广播获得："+count);
//        }
////        LogUtil.eLog("广播获得："+intent.getStringExtra("message"));
////
////        //设置通知内容并在onReceive()这个函数执行时开启
////        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
////        Notification notification=new Notification(R.mipmap.ic_launcher,"到时间干活！"
////             ,System.currentTimeMillis());
////        notification.defaults = Notification.DEFAULT_ALL;
////        manager.notify(1, notification);
////
////
////        //再次开启LongRunningService这个服务，从而可以
////        Intent i = new Intent(context, TaskTimeService.class);
////        context.startService(i);
//    }


        //再次开启LongRunningService这个服务，从而可以
        Intent i = new Intent(context, TaskTimeService.class);
        context.startService(i);
    }

}