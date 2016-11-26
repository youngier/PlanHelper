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
    private List<String> content;


    public DayInfo(){
        day = "";
        week = "";
        holiday = "";
        content = new ArrayList<>();
    }

    public DayInfo(String day, String week){
        this.day = day;
        this.week = week;
    }

    public DayInfo(String day, String week, String holiday, List<String> content){
        this.day = day;
        this.week = week;
        this.holiday = holiday;
        this.content = content;
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

    public List<String> getContent() {
        return content;
    }
}
