package com.young.planhelper.mvp.plan.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  11:37
 */


public class PlanThirdItemInfo extends RealmObject{

    @PrimaryKey
    private long planThirdItemInfoId;

    private long planSecondItemInfoId;

    private String title;

    private String time;

    private boolean isFinished;

    public void copyWith(PlanThirdItemInfo planThirdItemInfo) {
        this.planThirdItemInfoId = planThirdItemInfo.getPlanThirdItemInfoId();
        this.planSecondItemInfoId = planThirdItemInfo.getPlanSecondItemInfoId();
        this.title = planThirdItemInfo.getTitle();
        this.time = planThirdItemInfo.getTime();
        this.isFinished = planThirdItemInfo.isFinished();
    }

    public long getPlanSecondItemInfoId() {
        return planSecondItemInfoId;
    }

    public void setPlanSecondItemInfoId(long planSecondItemInfoId) {
        this.planSecondItemInfoId = planSecondItemInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public long getPlanThirdItemInfoId() {
        return planThirdItemInfoId;
    }

    public void setPlanThirdItemInfoId(long planThirdItemInfoId) {
        this.planThirdItemInfoId = planThirdItemInfoId;
    }
}
