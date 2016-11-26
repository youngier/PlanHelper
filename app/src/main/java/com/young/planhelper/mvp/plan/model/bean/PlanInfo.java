package com.young.planhelper.mvp.plan.model.bean;

import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:06
 */


public class PlanInfo extends RealmObject{

    public static final int AUTHORITY_ALL = 0;
    public static final int AUTHORITY_MENMBER = 1;
    public static final int AUTHORITY_CREATE = 2;


    @PrimaryKey
    private long planInfoId;

    private String title;
    private RealmList<BacklogInfo> backlogInfos;
    private int authority;


    public long getPlanInfoId() {
        return planInfoId;
    }

    public void setPlanInfoId(long planInfoId) {
        this.planInfoId = planInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<BacklogInfo> getBacklogInfos() {
        return backlogInfos;
    }

    public void setBacklogInfos(RealmList<BacklogInfo> backlogInfos) {
        this.backlogInfos = backlogInfos;
    }


    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
