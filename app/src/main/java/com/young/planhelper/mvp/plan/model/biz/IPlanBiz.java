package com.young.planhelper.mvp.plan.model.biz;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;

import java.util.List;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:48
 */


public interface IPlanBiz extends IBiz{

    /**
     * 获取计划数据
     * @param callback
     */
    void getPlanInfo(ICallback callback);

    /**
     * 添加计划
     * @param planInfo
     * @param callback
     */
    void addPlan(PlanInfo planInfo, ICallback callback);

    /**
     * 获取计划任务数据
     * @param planInfoId
     * @param callback
     */
    void getPlanItemInfo(long planInfoId, ICallback callback);


    /**
     * 添加计划任务
     * @param planItemInfo
     * @param callback
     */
    void addPlanItem(PlanItemInfo planItemInfo, ICallback callback);

    /**
     * 添加计划任务子任务项
     * @param planSecondItemInfo
     * @param callback
     */
    void addPlanSecondItem(PlanSecondItemInfo planSecondItemInfo, ICallback callback);

    /**
     * 获取计划任务子任务项数据
     * @param planItemInfoId
     * @param callback
     */
    void getPlanSecondItemInfo(long planItemInfoId, ICallback callback);

    /**
     * 根据具体id获取具体详情
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanSecondItemInfoById(long planSecondItemInfoId, ICallback callback);

    /**
     * 添加计划任务最小任务项
     * @param planThirdItemInfo
     * @param callback
     */
    void addPlanThirdItem(PlanThirdItemInfo planThirdItemInfo, ICallback callback);

    /**
     * 根据子任务id获取计划任务最小任务项
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanThirdItemInfoBySecondId(long planSecondItemInfoId, ICallback callback);

    /**
     * 添加计划子任务操作记录
     * @param planOperationInfo
     * @param callback
     */
    void addPlanSecondItemRecord(PlanOperationInfo planOperationInfo, ICallback callback);

    /**
     * 根据子任务id获取计划子任务操作记录
     * @param planSecondItemInfoId
     * @param callback
     */
    void getPlanOperationInfoBySecondId(long planSecondItemInfoId, ICallback callback);


    /**
     * 修改子任务内容
     * @param planSecondItemInfo
     * @param content
     * @param time
     * @param modifyModle
     * @param callback
     */
    void modifyPlanSecondItemInfo(PlanSecondItemInfo planSecondItemInfo, String content, String time, int modifyModle, ICallback callback);
}
