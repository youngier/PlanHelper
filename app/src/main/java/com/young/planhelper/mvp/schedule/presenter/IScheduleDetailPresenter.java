package com.young.planhelper.mvp.schedule.presenter;

import com.young.planhelper.mvp.base.model.IBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/12/5  11:10
 */


public interface IScheduleDetailPresenter {

    /**
     * 获取任务详情信息
     * @param backlogInfoId
     * @param callback
     */
    void getBacklogInfoDetail(long backlogInfoId, IBiz.ICallback callback);
}
