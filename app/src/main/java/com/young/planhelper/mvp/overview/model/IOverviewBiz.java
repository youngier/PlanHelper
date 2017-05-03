package com.young.planhelper.mvp.overview.model;

import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/27  15:58
 */


public interface IOverviewBiz extends IBiz {

    /**
     * 显示数据，根据每月份，以及各状态的
     * @param year
     * @param iCallback
     */
    void getDataByMonth(int year, ICallback iCallback);

    /**
     * 显示数据，根据每周
     * @param year
     * @param iCallback
     */
    void getDataByWeek(int year, ICallback iCallback);

    /**
     * 显示数据，根据时间段
     * @param year
     * @param iCallback
     */
    void getDataByHour(int year, ICallback iCallback);
}
