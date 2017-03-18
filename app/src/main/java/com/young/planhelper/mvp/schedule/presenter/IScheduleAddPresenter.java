package com.young.planhelper.mvp.schedule.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/17  20:01
 */


public interface IScheduleAddPresenter extends IPresenter{

    /**
     *
     * @param backlogInfo
     * @param callback
     */
    void addBacklogInfo(BacklogInfo backlogInfo, IBiz.ICallback callback);

}
