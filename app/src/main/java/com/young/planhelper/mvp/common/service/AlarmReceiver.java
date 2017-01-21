package com.young.planhelper.mvp.common.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.young.planhelper.R;

public class AlarmReceiver extends BroadcastReceiver{

 @Override
 public void onReceive(Context context, Intent intent) {
    //设置通知内容并在onReceive()这个函数执行时开启
     NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
     Notification notification=new Notification(R.mipmap.ic_launcher,"用电脑时间过长了！白痴！"
             ,System.currentTimeMillis());
     notification.defaults = Notification.DEFAULT_ALL;
     manager.notify(1, notification);


     //再次开启LongRunningService这个服务，从而可以
     Intent i = new Intent(context, TaskTimeService.class);
     context.startService(i);
 }
}