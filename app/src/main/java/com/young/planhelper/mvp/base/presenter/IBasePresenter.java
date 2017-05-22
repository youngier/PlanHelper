package com.young.planhelper.mvp.base.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.List;

/**
 * @author: young
 * date:17/5/9  18:05
 */


public interface IBasePresenter {

    /**
     * 备份
     * @param backlogInfoList
     * @param iCallback
     */
    void backups(List<BacklogInfo> backlogInfoList, IBiz.ICallback iCallback);

    /**
     * 获取所有的备份任务
     * @return
     */
    List<BacklogInfo> getBacklogList();

}
