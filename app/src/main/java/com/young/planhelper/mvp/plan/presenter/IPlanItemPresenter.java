package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  21:37
 */


public interface IPlanItemPresenter extends IPresenter {

    /**
     * 获取计划内所有任务内容
     * @param planInfoId
     * @param callback
     */
    void getPlanItemInfo(long planInfoId, final IBiz.ICallback callback);

    /**
     * 获取在线计划内所有任务内容
     * @param planInfoId
     * @param callback
     */
    void getPlanItemInfoByNetWork(long planInfoId, final IBiz.ICallback callback);
}

