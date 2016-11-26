package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/21  22:07
 */


public interface IPlanAddPresenter extends IPresenter{

    /**
     * 添加新计划
     * @param planInfo
     * @param callback
     */
    void addPlan(PlanInfo planInfo, IBiz.ICallback callback);

}
