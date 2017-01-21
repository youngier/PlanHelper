package com.young.planhelper.mvp.schedule.model.biz;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/16  20:49
 */


public interface IScheduleBiz extends IBiz{

    /**
     * 添加任务
     * @param backlogInfo
     * @param callback
     */
    void addBacklogInfo(BacklogInfo backlogInfo, final ICallback callback);

    /**
     * 获取任务
     * @param callback
     */
    void getBacklogInfo(final ICallback callback);

    /**
     * 根据获取任务的信息
     * @param date
     * @param callback
     */
    void getBackLogInfoToday(String date, ICallback callback);

    /**
     * 获取今天的所有任务
     * @param callback
     */
    void getBackLogInfoToday(final ICallback callback);

    /**
     * 获取未来的所有任务
     * @param callback
     */
    void getBackLogInfoFuture(final  ICallback callback);

    /**
     * 获取过期的所有任务
     * @param callback
     */
    void getBackLogInfoOverdue(final ICallback callback);

    /**
     * 获取任务的详情信息
     * @param backlogInfoId
     * @param callback
     */
    void getBackLogInfoDetail(long backlogInfoId, final ICallback callback);


}
