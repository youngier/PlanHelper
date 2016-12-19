package com.young.planhelper.mvp.plan.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/24  14:37
 */


public class PlanSecondItemInfo extends RealmObject{

    @PrimaryKey
    private long planSecondItemInfoId;

    private long planItemInfoId;

    private String title;

    private String content;

    private String time;

    private boolean hasNotification;

    private boolean isFinished;

    public long getPlanSecondItemInfoId() {
        return planSecondItemInfoId;
    }

    public void setPlanSecondItemInfoId(long planSecondItemInfoId) {
        this.planSecondItemInfoId = planSecondItemInfoId;
    }

    public long getPlanItemInfoId() {
        return planItemInfoId;
    }

    public void setPlanItemInfoId(long planItemInfoId) {
        this.planItemInfoId = planItemInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setHasNotification(boolean hasNotification) {
        this.hasNotification = hasNotification;
    }

    public boolean isHasNotification() {
        return hasNotification;
    }


    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
