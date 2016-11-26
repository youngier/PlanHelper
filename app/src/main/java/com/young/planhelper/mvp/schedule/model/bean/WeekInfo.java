package com.young.planhelper.mvp.schedule.model.bean;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  21:07
 */


public class WeekInfo {

    DayInfo[] dayInfos;

    public WeekInfo(){
        this.dayInfos = new DayInfo[7];
    }

    public WeekInfo(DayInfo[] dayInfos){
        this.dayInfos = dayInfos;
    }

    public DayInfo[] getDayInfos(){
        return dayInfos;
    }
}
