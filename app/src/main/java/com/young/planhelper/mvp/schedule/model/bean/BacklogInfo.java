package com.young.planhelper.mvp.schedule.model.bean;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/5  21:29
 */


public class BacklogInfo extends RealmObject implements Serializable{

    public static final int UNFINISH = 0;
    public static final int FINISHED = 1;
    public static final int OVERDUE = 2;

    /**
     * 重复类型
     */
    public static final int NONE = 0;
    public static final int DAILY = 1;
    public static final int MONTHLY = 2;
    public static final int YEARLY = 3;

    @PrimaryKey
    private long backlogInfoId;     //当前时间戳来设置为id

    @Required
    private String content = "";

    private long fromTime;

    private long toTime;

    private String location = "";
    private RealmList<BacklogImageInfo> backlogImageInfos;
    private int statue;

    private long planInfoId;
    private int repeatType;
    private String members = "";

    public BacklogInfo(){

    }

    @Override
    public String toString() {
        return "{ backlogInfoId:"+backlogInfoId+", content:"+content+", fromTime:"+fromTime
                +", toTime:"+toTime+", location:"+location+", backlogImageInfos: , statue:"+statue
                +", planInfoId:"+planInfoId+", repeatType:"+repeatType+", members:"+members+" }";
    }

    public void copyWith(BacklogInfo backlogInfo){
        this.backlogInfoId = backlogInfo.getBacklogInfoId();
        this.content = backlogInfo.getContent();
        this.fromTime = backlogInfo.getFromTime();
        this.toTime = backlogInfo.getToTime();
        this.location = backlogInfo.getLocation();
        this.backlogImageInfos = backlogInfo.getBacklogImageInfos();
        this.statue = backlogInfo.getStatue();
        this.planInfoId = backlogInfo.getPlanInfoId();
        this.repeatType = backlogInfo.getRepeatType();
        this.members = backlogInfo.getMembers();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }


    public long getBacklogInfoId() {
        return backlogInfoId;
    }

    public void setBacklogInfoId(long backlogInfoId) {
        this.backlogInfoId = backlogInfoId;
    }

    public RealmList<BacklogImageInfo> getBacklogImageInfos() {
        return backlogImageInfos;
    }

    public void setBacklogImageInfos(RealmList<BacklogImageInfo> backlogImageInfos) {
        this.backlogImageInfos = backlogImageInfos;
    }

    public long getFromTime() {
        return fromTime;
    }

    public void setFromTime(long fromTime) {
        this.fromTime = fromTime;
    }

    public long getToTime() {
        return toTime;
    }

    public void setToTime(long toTime) {
        this.toTime = toTime;
    }

    public void setPlanInfoId(long planInfoId) {
        this.planInfoId = planInfoId;
    }

    public long getPlanInfoId() {
        return planInfoId;
    }

    public void setRepeatType(int repeatType) {
        this.repeatType = repeatType;
    }

    public int getRepeatType() {
        return repeatType;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getMembers() {
        return members;
    }
}
