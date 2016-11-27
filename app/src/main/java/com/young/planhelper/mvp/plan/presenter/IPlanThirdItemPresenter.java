package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  11:36
 */


public interface IPlanThirdItemPresenter extends IPresenter{

    /**
     * 添加计划内最小单位的任务
     * @param planThirdItemInfo
     * @param callback
     */
    void addPlanThirdItem(PlanThirdItemInfo planThirdItemInfo, IBiz.ICallback callback);

}
