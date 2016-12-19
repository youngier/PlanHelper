package com.young.planhelper.mvp.plan.presenter;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.IPresenter;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/25  19:28
 */


public interface IPlanSecondItemDetailPresenter extends IPresenter{

    /**
     * 根据具体id获取具体详情
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanSecondItemInfoById(long planSecondItemInfoId, final IBiz.ICallback callback);

    /**
     * 根据子任务项id获取具体详情
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanThirdItemInfoBySecondId(long planSecondItemInfoId, final IBiz.ICallback callback);

    /**
     * 添加计划任务
     */
    void addPlanSecondItemRecond(PlanOperationInfo planOperationInfo, IBiz.ICallback callback);

    /**
     * 根据子任务项id获取操作记录
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanOperationInfoBySecondId(long planSecondItemInfoId, final IBiz.ICallback callback);

    /**
     * 修改子任务项内容
     * @param planSecondItemInfo
     * @param content
     * @param time
     * @param modifyModle
     * @param callback
     */
    void modifyPlanSecondItemInfo(PlanSecondItemInfo planSecondItemInfo, String content, String time, int modifyModle, final IBiz.ICallback callback);

    /**
     * 修改子任务项下的任务的状态
     * @param planThirdItemInfoId
     * @param isFinished
     * @param callback
     */
    void modifyPlanThirdItemInfoStateById(long planThirdItemInfoId, boolean isFinished, final IBiz.ICallback callback);

    /**
     * 修改子任务的状态
     * @param planSecondItemInfoId
     * @param isChecked
     * @param callback
     */
    void modifyPlanSecondItemInfoStateById(long planSecondItemInfoId, boolean isChecked, final IBiz.ICallback callback);
}
