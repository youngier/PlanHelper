package com.young.planhelper.config.api;

import com.young.planhelper.mvp.schedule.model.DayInfo;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/10/8  10:08
 */


public interface ApiService {

    @POST("/list")
    Call<DayInfo> load();
}
