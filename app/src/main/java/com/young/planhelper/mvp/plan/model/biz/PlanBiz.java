package com.young.planhelper.mvp.plan.model.biz;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.LogUtil;

import java.util.List;

import io.realm.Realm;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:50
 */


public class PlanBiz extends Biz implements IPlanBiz{

    private final Realm mRealm;

    public PlanBiz(Context context) {
        mRealm = AppApplication.get(context).getmAppComponent().getRealm();
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void getPlanInfo(ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        List<PlanInfo> planInfos = mRealm.where(PlanInfo.class).findAll();

        callback.onResult(planInfos);
    }

    @Override
    public void addPlan(PlanInfo planInfo, ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("添加失败");
            return;
        }

        mRealm.beginTransaction();
        PlanInfo object = mRealm.copyToRealm(planInfo);
        mRealm.commitTransaction();

        callback.onResult("添加成功");
    }

    @Override
    public void addPlanItem(PlanItemInfo planItemInfo, ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("添加失败");
            return;
        }

        mRealm.beginTransaction();
        PlanItemInfo object = mRealm.copyToRealm(planItemInfo);
        mRealm.commitTransaction();

        callback.onResult("添加成功");
    }

    @Override
    public void addPlanSecondItem(PlanSecondItemInfo planSecondItemInfo, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("添加失败");
            return;
        }

        mRealm.beginTransaction();
        PlanSecondItemInfo object = mRealm.copyToRealm(planSecondItemInfo);
        mRealm.commitTransaction();

        callback.onResult("添加成功");
    }

    @Override
    public void getPlanSecondItemInfo(long planItemInfoId, ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        List<PlanSecondItemInfo> planSecondItemInfos = mRealm.where(PlanSecondItemInfo.class).equalTo("planItemInfoId", planItemInfoId).findAll();

        callback.onResult(planSecondItemInfos);

    }

    @Override
    public void getPlanItemInfo(long planInfoId, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        List<PlanItemInfo> planItemInfos = mRealm.where(PlanItemInfo.class).equalTo("planInfoId", planInfoId).findAll();


        callback.onResult(planItemInfos);
    }

    @Override
    public void getPlanSecondItemInfoById(long planSecondItemInfoId, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        PlanSecondItemInfo planSecondItemInfo = mRealm.where(PlanSecondItemInfo.class).equalTo("planSecondItemInfoId", planSecondItemInfoId).findFirst();

        callback.onResult(planSecondItemInfo);
    }
}
