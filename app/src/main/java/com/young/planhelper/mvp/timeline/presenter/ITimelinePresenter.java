package com.young.planhelper.mvp.timeline.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/22  14:30
 */


public interface ITimelinePresenter extends IPresenter{

    /**
     * 获取历史记录
     * @param callback
     */
    void getTimelineInfo(IBiz.ICallback callback);

}
