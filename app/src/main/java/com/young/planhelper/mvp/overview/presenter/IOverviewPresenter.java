package com.young.planhelper.mvp.overview.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/27  15:50
 */


public interface IOverviewPresenter extends IPresenter{

    /**
     * 显示数据，根据每月份，以及各状态的
     * @param year
     * @param iCallback
     */
    void getDataByMonth(int year, IBiz.ICallback iCallback);

    /**
     * 显示数据，根据每周
     * @param year
     * @param iCallback
     */
    void getDataByWeek(int year, IBiz.ICallback iCallback);

    /**
     * 显示数据，根据时间段
     * @param year
     * @param iCallback
     */
    void getDataByHour(int year, IBiz.ICallback iCallback);
}
