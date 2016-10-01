package com.young.planhelper.mvp.schedule.model;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  21:08
 */


public class DayInfo {

    private String mDay;
    private String mWeek;

    public DayInfo(){
        mDay = "";
        mWeek = "";
    }

    public DayInfo(String day, String week){
        this.mDay = day;
        this.mWeek = week;
    }

    public String getmDay() {
        return mDay;
    }

    public void setmDay(String mDay) {
        this.mDay = mDay;
    }

    public String getmWeek() {
        return mWeek;
    }

    public void setmWeek(String mWeek) {
        this.mWeek = mWeek;
    }
}
