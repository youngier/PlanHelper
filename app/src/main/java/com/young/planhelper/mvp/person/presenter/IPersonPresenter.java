package com.young.planhelper.mvp.person.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.List;

/**
 * @author: young
 * date:17/5/23  14:44
 */


public interface IPersonPresenter {

    /**
     * 备份所有的日程任务
     * @param iCallback
     */
    void backups(IBiz.ICallback iCallback);


}
