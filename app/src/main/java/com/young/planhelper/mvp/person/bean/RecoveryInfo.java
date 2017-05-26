package com.young.planhelper.mvp.person.bean;

import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.List;

/**
 * @author: young
 * date:17/5/26  19:54
 */


public class RecoveryInfo {

    private List<BacklogInfo> backlogInfos;

    private List<PlanInfo> planInfos;

    private List<PlanItemInfo> planItemInfos;

    private List<PlanSecondItemInfo> planSecondItemInfos;

    private List<PlanThirdItemInfo> planThirdItemInfos;

    private List<PlanOperationInfo> planOperationInfos;


    public List<BacklogInfo> getBacklogInfos() {
        return backlogInfos;
    }

    public void setBacklogInfos(List<BacklogInfo> backlogInfos) {
        this.backlogInfos = backlogInfos;
    }

    public List<PlanInfo> getPlanInfos() {
        return planInfos;
    }

    public void setPlanInfos(List<PlanInfo> planInfos) {
        this.planInfos = planInfos;
    }

    public List<PlanItemInfo> getPlanItemInfos() {
        return planItemInfos;
    }

    public void setPlanItemInfos(List<PlanItemInfo> planItemInfos) {
        this.planItemInfos = planItemInfos;
    }

    public List<PlanSecondItemInfo> getPlanSecondItemInfos() {
        return planSecondItemInfos;
    }

    public void setPlanSecondItemInfos(List<PlanSecondItemInfo> planSecondItemInfos) {
        this.planSecondItemInfos = planSecondItemInfos;
    }

    public List<PlanThirdItemInfo> getPlanThirdItemInfos() {
        return planThirdItemInfos;
    }

    public void setPlanThirdItemInfos(List<PlanThirdItemInfo> planThirdItemInfos) {
        this.planThirdItemInfos = planThirdItemInfos;
    }

    public List<PlanOperationInfo> getPlanOperationInfos() {
        return planOperationInfos;
    }

    public void setPlanOperationInfos(List<PlanOperationInfo> planOperationInfos) {
        this.planOperationInfos = planOperationInfos;
    }
}
