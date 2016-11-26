package com.young.planhelper.mvp.schedule.model.bean;

import io.realm.RealmObject;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/17  20:57
 */


public class BacklogImageInfo extends RealmObject{

    private String bitmapUrl;

    public String getBitmapUrl() {
        return bitmapUrl;
    }

    public void setBitmapUrl(String bitmapUrl) {
        this.bitmapUrl = bitmapUrl;
    }
}
