package com.young.planhelper.mvp.timeline.model.bean;

import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/21  19:08
 */


public class TimelineInfo {

    private String date = "";

    private List<BacklogInfo> backlogInfoList = new ArrayList<>();

    public List<BacklogInfo> getBacklogInfoList() {
        return backlogInfoList;
    }

    public void setBacklogInfoList(List<BacklogInfo> backlogInfoList) {
        this.backlogInfoList = backlogInfoList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
