package com.young.planhelper.mvp.schedule.model.bean;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/1  21:08
 */


public class DayInfo {

    private String day;
    private String week;
    private String holiday;

    private String date;

    private boolean isHave = false;


    public DayInfo(){
        day = "";
        week = "";
        holiday = "";
    }

    public DayInfo(String day, String week){
        this.day = day;
        this.week = week;
    }

    public DayInfo(String day, String week, String holiday){
        this.day = day;
        this.week = week;
        this.holiday = holiday;
    }

    public DayInfo(String date, String day, String week, String holiday){
        this.day = day;
        this.week = week;
        this.holiday = holiday;
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getHoliday() {
        return holiday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isHave() {
        return isHave;
    }

    public void setHave(boolean have) {
        isHave = have;
    }
}
