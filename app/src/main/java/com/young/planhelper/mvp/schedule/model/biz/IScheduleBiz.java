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
    void getBackLogInfoByday(String date, ICallback callback);


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


    /**
     * 查询是否有任务
     * @param timeBegin
     * @param timeEnd
     * @param callback
     */
    void queryByMonth(String timeBegin, String timeEnd, ICallback callback);

    /**
     * 修改日程任务
     * @param mBacklogInfo
     * @param backlogInfo
     * @param callback
     */
    void modifyBacklogInfo(BacklogInfo mBacklogInfo, BacklogInfo backlogInfo, ICallback callback);

    /**
     * 删除日程任务
     * @param mBacklogInfo
     * @param callback
     */
    void deleteBacklog(BacklogInfo mBacklogInfo, ICallback callback);

    /**
     * 完成日程任务
     * @param mBacklogInfo
     * @param callback
     */
    void finishBacklogInfo(BacklogInfo mBacklogInfo, ICallback callback);

    /**
     * 初始化任务
     * @param callback
     */
    void initBacklogInfo(ICallback callback);

    /**
     * 获取所选日期需要完成的任务
     * @param date
     * @param callback
     */
    void getBacklogInfoNeed(String date, ICallback callback);
}
