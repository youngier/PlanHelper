package com.young.planhelper.mvp.schedule.model;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/5  21:29
 */


public class BacklogInfo {

    private String content;
    private String time;
    private String location;
    private List<String> bitmapUris;
    private int statue;

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

    public List<String> getBitmapUris() {
        return bitmapUris;
    }

    public void setBitmapUris(List<String> bitmapUris) {
        this.bitmapUris = bitmapUris;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
