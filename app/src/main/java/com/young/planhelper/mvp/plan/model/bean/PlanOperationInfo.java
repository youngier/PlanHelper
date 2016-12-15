package com.young.planhelper.mvp.plan.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  20:25
 */


public class PlanOperationInfo extends RealmObject{

    public static final int CREATE = 0;
    public static final int MODIFY_TEXT = 1;
    public static final int MODIFY_TIME = 2;

    @PrimaryKey
    private long planOperationInfoId;

    private long planSecondItemInfoId;

    private String name;

    private String content;

    private String time;

    private int type;

    public long getPlanOperationInfoId() {
        return planOperationInfoId;
    }

    public void setPlanOperationInfoId(long planOperationInfoId) {
        this.planOperationInfoId = planOperationInfoId;
    }

    public long getPlanSecondItemInfoId() {
        return planSecondItemInfoId;
    }

    public void setPlanSecondItemInfoId(long planSecondItemInfoId) {
        this.planSecondItemInfoId = planSecondItemInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
