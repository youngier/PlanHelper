package com.young.planhelper.mvp.common.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/12/19  21:15
 */


public class NotificationInfo extends RealmObject{

    @PrimaryKey
    private long notificationInfoId;

    private long time;


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getNotificationInfoId() {
        return notificationInfoId;
    }

    public void setNotificationInfoId(long notificationInfoId) {
        this.notificationInfoId = notificationInfoId;
    }
}
