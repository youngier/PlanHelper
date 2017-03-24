package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:19
 */


public interface IPlanPresenter extends IPresenter{

    /**
     * 获取所有计划内容
     * @param callback
     */
    void getPlanInfo(final IBiz.ICallback callback);

    /**
     * 根据网络获取所有计划内容
     * @param callback
     */
    void getPlanInfoByNetWork(final IBiz.ICallback callback);

    /**
     * 删除计划
     * @param planInfo
     * @param callback
     */
    void deletePlanInfo(PlanInfo planInfo, final IBiz.ICallback callback);
}
