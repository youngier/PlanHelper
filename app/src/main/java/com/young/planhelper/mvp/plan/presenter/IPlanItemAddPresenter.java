package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/23  21:23
 */


public interface IPlanItemAddPresenter extends IPresenter{

    /**
     * 添加计划任务
     */
    void addPlanItem(PlanItemInfo planItemInfo, IBiz.ICallback callback);

}
