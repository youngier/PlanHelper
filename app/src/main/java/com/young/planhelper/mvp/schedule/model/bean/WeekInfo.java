package com.young.planhelper.mvp.schedule.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  21:07
 */


public class WeekInfo {

    List<DayInfo> mDayInfoList;

    public WeekInfo(){
        this.mDayInfoList = new ArrayList<>();
    }

    public WeekInfo(List<DayInfo> dayInfos){
        this.mDayInfoList = dayInfos;
    }

    public List<DayInfo> getDayInfos(){
        return mDayInfoList;
    }
}
