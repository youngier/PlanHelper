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
    private long planThridItemInfoId;

    private long planSecondItemInfoId;

    private String title;

    private String time;

    public long getPlanThridItemInfoId() {
        return planThridItemInfoId;
    }

    public void setPlanThridItemInfoId(long planThridItemInfoId) {
        this.planThridItemInfoId = planThridItemInfoId;
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
}
