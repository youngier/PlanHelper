package com.young.planhelper.mvp.common.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.young.planhelper.R;
import com.young.planhelper.util.LogUtil;

public class AlarmReceiver extends BroadcastReceiver{
    private final static String DIVIDE_RESULT="com.young.planhelper.mvp.common.service.DIVIDE";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(DIVIDE_RESULT))
        {
            int count = intent.getIntExtra("count", 0);
            LogUtil.eLog("广播获得："+count);
        }
//        LogUtil.eLog("广播获得："+intent.getStringExtra("message"));
//
//        //设置通知内容并在onReceive()这个函数执行时开启
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification=new Notification(R.mipmap.ic_launcher,"到时间干活！"
//             ,System.currentTimeMillis());
//        notification.defaults = Notification.DEFAULT_ALL;
//        manager.notify(1, notification);
//
//
//        //再次开启LongRunningService这个服务，从而可以
//        Intent i = new Intent(context, TaskTimeService.class);
//        context.startService(i);
    }
}