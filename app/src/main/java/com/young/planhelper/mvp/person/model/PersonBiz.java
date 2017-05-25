package com.young.planhelper.mvp.person.model;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.network.BackupsApiService;
import com.young.planhelper.util.JsonUitl;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Retrofit;
import rx.Observable;

import static com.young.planhelper.constant.AppConstant.REALM_NOT_INIT;

/**
 * @author: young
 * date:17/5/23  14:46
 */


public class PersonBiz extends Biz implements IPersonBiz{

    private final User mUser;
    private final Retrofit mRetrofit;
    private final Realm mRealm;

    public PersonBiz(Context context){
        mUser = AppApplication.get(context).getmAppComponent().getUserInfo();
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();
        mRealm = AppApplication.get(context).getmAppComponent().getRealm();
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void backups(ICallback iCallback) {

        if (mRealm == null) {
            LogUtil.eLog(REALM_NOT_INIT);
            iCallback.onResult("");
        }


        List<BacklogInfo> result = getBacklogList();

        List<PlanInfo> planResult = getPlanList();

        List<PlanItemInfo> planItemResult = getPlanItemList();

        List<PlanSecondItemInfo> planSecondItemResult = getPlanSecondItemList();

        List<PlanThirdItemInfo> planThirdItemResult = getPlanThirdItemList();

        List<PlanOperationInfo> planOperationResult = getPlanOperationList();


        if( mUser.getUserId().isEmpty() ){

            iCallback.onResult("fail");

        }else {

            BackupsApiService backupsApiService = mRetrofit.create(BackupsApiService.class);

            LogUtil.eLog("备份情况:"+JsonUitl.objectToString(planOperationResult));

            //获得Observable对象
            Observable<String> data = backupsApiService.backups(mUser.getUserId(),
                    JsonUitl.objectToString(result), JsonUitl.objectToString(planResult),
                    JsonUitl.objectToString(planItemResult), JsonUitl.objectToString(planSecondItemResult),
                    JsonUitl.objectToString(planThirdItemResult), JsonUitl.objectToString(planOperationResult));

            iCallback.onResult(data);
        }
    }

    /**
     * 获取所需备份的日程任务
     * @return
     */
    public List<BacklogInfo> getBacklogList() {

        List<BacklogInfo> backlogInfoList = mRealm.where(BacklogInfo.class).findAll();

        List<BacklogInfo> result = new ArrayList<>();

        for (int i = 0; i < backlogInfoList.size(); i++) {
            BacklogInfo backlogInfo = backlogInfoList.get(i);
            BacklogInfo temp = new BacklogInfo();
            temp.copyWith(backlogInfo);
            result.add(temp);
        }

        return result;
    }

    /**
     * 获取所需备份的计划
     * @return
     */
    public List<PlanInfo> getPlanList() {

        List<PlanInfo> planInfoList = mRealm.where(PlanInfo.class).findAll();

        List<PlanInfo> result = new ArrayList<>();

        for (int i = 0; i < planInfoList.size(); i++) {
            PlanInfo planInfo = planInfoList.get(i);
            PlanInfo temp = new PlanInfo();
            temp.copyWith(planInfo);
            result.add(temp);
        }

        return result;
    }

    /**
     * 获取所需备份的计划任务
     * @return
     */
    public List<PlanItemInfo> getPlanItemList() {

        List<PlanItemInfo> planItemInfoList = mRealm.where(PlanItemInfo.class).findAll();

        List<PlanItemInfo> result = new ArrayList<>();

        for (int i = 0; i < planItemInfoList.size(); i++) {
            PlanItemInfo planItemInfo = planItemInfoList.get(i);
            PlanItemInfo temp = new PlanItemInfo();
            temp.copyWith(planItemInfo);
            result.add(temp);
        }

        return result;
    }

    /**
     * 获取所需备份的计划子任务
     * @return
     */
    public List<PlanSecondItemInfo> getPlanSecondItemList() {

        List<PlanSecondItemInfo> planSecondItemInfoList = mRealm.where(PlanSecondItemInfo.class).findAll();

        List<PlanSecondItemInfo> result = new ArrayList<>();

        for (int i = 0; i < planSecondItemInfoList.size(); i++) {
            PlanSecondItemInfo planSecondItemInfo = planSecondItemInfoList.get(i);
            PlanSecondItemInfo temp = new PlanSecondItemInfo();
            temp.copyWith(planSecondItemInfo);
            result.add(temp);
        }

        return result;
    }

    /**
     * 获取所需备份的计划单元任务
     * @return
     */
    public List<PlanThirdItemInfo> getPlanThirdItemList() {

        List<PlanThirdItemInfo> planThirdItemInfoList = mRealm.where(PlanThirdItemInfo.class).findAll();

        List<PlanThirdItemInfo> result = new ArrayList<>();

        for (int i = 0; i < planThirdItemInfoList.size(); i++) {
            PlanThirdItemInfo planThirdItemInfo = planThirdItemInfoList.get(i);
            PlanThirdItemInfo temp = new PlanThirdItemInfo();
            temp.copyWith(planThirdItemInfo);
            result.add(temp);
        }

        return result;
    }

    /**
     * 获取所需备份的计划操作记录
     * @return
     */
    public List<PlanOperationInfo> getPlanOperationList() {

        List<PlanOperationInfo> planOperationInfoList = mRealm.where(PlanOperationInfo.class).findAll();

        List<PlanOperationInfo> result = new ArrayList<>();

        for (int i = 0; i < planOperationInfoList.size(); i++) {
            PlanOperationInfo planOperationInfo = planOperationInfoList.get(i);
            PlanOperationInfo temp = new PlanOperationInfo();
            temp.copyWith(planOperationInfo);
            result.add(temp);
        }

        return result;
    }
}
