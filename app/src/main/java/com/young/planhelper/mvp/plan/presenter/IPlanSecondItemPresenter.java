package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/24  14:39
 */


public interface IPlanSecondItemPresenter extends IPresenter{

    /**
     * 获取计划任务的子任务项内容
     * @param planItemInfoId
     * @param callback
     */
    void getPlanSecondItemInfo(long planItemInfoId, final IBiz.ICallback callback);

    /**
     * 修改子任务的状态
     * @param planSecondItemInfoId
     * @param isChecked
     * @param callback
     */
    void modifyPlanSecondItemInfoStateById(long planSecondItemInfoId, boolean isChecked, final IBiz.ICallback callback);

    /**
     * 修改任务项标题
     * @param planItemInfoId
     * @param title
     * @param callback
     */
    void modifyPlanSecondItemInfoTitle(long planItemInfoId, String title, final IBiz.ICallback callback);

    /**
     * 删除任务项
     * @param planItemInfoId
     * @param callback
     */
    void deletePlanItemInfo(long planItemInfoId, final IBiz.ICallback callback);
}
