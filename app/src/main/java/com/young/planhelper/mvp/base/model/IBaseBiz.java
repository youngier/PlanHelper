package com.young.planhelper.mvp.base.model;

import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.List;

/**
 * @author: young
 * date:17/5/9  18:07
 */

public interface IBaseBiz {

    /**
     * 获取所有的备份任务
     * @return
     */
    List<BacklogInfo> getBacklogList();

}
