package com.young.planhelper.mvp.schedule.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:30
 */

public interface ISchedulePresenter extends IPresenter{

    /**
     * 获取任务
     * @param callback
     */
    void getBacklogInfos(IBiz.ICallback callback);

    /**
     * 获取今天的任务
     * @param callback
     */
    void getBackLogInfoToday(IBiz.ICallback callback);

    /**
     * 获取未来的任务
     * @param callback
     */
    void getBackLogInfoFuture(IBiz.ICallback callback);

    /**
     * 获取过期的任务
     * @param callback
     */
    void getBackLogInfoOverdue(IBiz.ICallback callback);

}
