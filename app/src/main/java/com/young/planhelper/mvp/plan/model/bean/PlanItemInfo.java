package com.young.planhelper.mvp.plan.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  21:24
 */


public class PlanItemInfo extends RealmObject{

    @PrimaryKey
    private long planItemInfoId;

    private long planInfoId;

    private String title;

    public void copyWith(PlanItemInfo planItemInfo) {
        this.planItemInfoId = planItemInfo.getPlanItemInfoId();
        this.planInfoId = planItemInfo.getPlanInfoId();
        this.title = planItemInfo.getTitle();
    }

    public void setPlanItemInfoId(long planItemInfoId) {
        this.planItemInfoId = planItemInfoId;
    }

    public long getPlanItemInfoId() {
        return planItemInfoId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public long getPlanInfoId() {
        return planInfoId;
    }

    public void setPlanInfoId(long planInfoId) {
        this.planInfoId = planInfoId;
    }

}
