package com.young.planhelper.mvp.schedule.model.bean;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/3  22:57
 */


public class CalendarInfo {


    DayInfo[] dayInfos;

    public CalendarInfo(){
        this.dayInfos = new DayInfo[31];
    }

    public CalendarInfo(DayInfo[] dayInfos){
        this.dayInfos = dayInfos;
    }

    public DayInfo[] getDayInfos(){
        return dayInfos;
    }

    public void setDayInfos(DayInfo[] dayInfos) {
        this.dayInfos = dayInfos;
    }
}
