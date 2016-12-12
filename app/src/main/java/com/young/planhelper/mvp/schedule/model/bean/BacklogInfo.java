package com.young.planhelper.mvp.schedule.model.bean;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/5  21:29
 */


public class BacklogInfo extends RealmObject {

    public static final int UNFINISH = 0;
    public static final int FINISHED = 1;
    public static final int OVERDUE = 2;

    @PrimaryKey
    private long backlogInfoId;     //当前时间戳来设置为id

    @Required
    private String content;

    private long time;

    private String location;
    private RealmList<BacklogImageInfo> backlogImageInfos;
    private int statue;

    public BacklogInfo(){

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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
}
