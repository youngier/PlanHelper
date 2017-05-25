package com.young.planhelper.mvp.plan.model.bean;

import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:06
 */


public class PlanInfo extends RealmObject implements Serializable{

    public static final int AUTHORITY_ALL = 0;
    public static final int AUTHORITY_MENMBER = 1;
    public static final int AUTHORITY_CREATE = 2;


    @PrimaryKey
    private long planInfoId;

    private String title;
    private int authority;

    private boolean isSynchronized = false;

    /**
     * 成员用逗号隔开
     */
    private String members = "";

    public void copyWith(PlanInfo planInfo) {
        this.planInfoId = planInfo.getPlanInfoId();
        this.title = planInfo.getTitle();
        this.authority = planInfo.getAuthority();
        this.isSynchronized = planInfo.isSynchronized();
        this.members = planInfo.getMembers();
    }




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

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public void setSynchronized(boolean aSynchronized) {
        isSynchronized = aSynchronized;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getMembers() {
        return members;
    }

}
