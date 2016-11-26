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
}
