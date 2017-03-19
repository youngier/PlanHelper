package com.young.planhelper.mvp.schedule.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

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
     * 获取任务
     * @param date
     * @param callback
     */
    void getBacklogInfos(String date, IBiz.ICallback callback);


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

    /**
     * 查询是否存在任务
     * @param timeBegin
     * @param timeEnd
     * @param callback
     */
    void queryByMonth(String timeBegin, String timeEnd, IBiz.ICallback callback);

    /**
     * 根据id获取日程任务详情
     * @param backlogInfoId
     * @param callback
     */
    void getBacklogDetailById(long backlogInfoId, IBiz.ICallback callback);

    /**
     * 修改日程任务
     * @param mBacklogInfo
     * @param backlogInfo
     * @param callback
     */
    void modifyBacklogInfo(BacklogInfo mBacklogInfo, BacklogInfo backlogInfo, IBiz.ICallback callback);

    /**
     * 删除日程任务
     * @param mBacklogInfo
     * @param callback
     */
    void deleteBacklog(BacklogInfo mBacklogInfo, IBiz.ICallback callback);

    /**
     * 完成任务
     * @param mBacklogInfo
     * @param callback
     */
    void finishBacklogInfo(BacklogInfo mBacklogInfo, IBiz.ICallback callback);
}
