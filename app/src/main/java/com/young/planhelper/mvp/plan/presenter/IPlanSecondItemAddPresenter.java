package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/24  14:39
 */


public interface IPlanSecondItemAddPresenter {

    /**
     * 添加计划任务
     */
    void addPlanSecondItem(PlanSecondItemInfo planSecondItemInfo, IBiz.ICallback callback);

    /**
     * 添加线上计划子任务
     * @param planSecondItemInfo
     * @param callback
     */
    void addPlanSecondItemByNetWork(PlanSecondItemInfo planSecondItemInfo, IBiz.ICallback callback);

}
