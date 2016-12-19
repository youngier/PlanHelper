package com.young.planhelper.mvp.plan.model.biz;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.common.model.NotificationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.sql.Time;
import java.util.List;

import io.realm.Realm;

import static com.young.planhelper.constant.AppConstant.MODIFY_CONTENT;
import static com.young.planhelper.constant.AppConstant.MODIFY_CONTENT_AND_TIME;
import static com.young.planhelper.constant.AppConstant.MODIFY_TIME;

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

        /**
         * 存储任务操作纪录
         */
        PlanOperationInfo planOperationInfo = new PlanOperationInfo();
        planOperationInfo.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
        planOperationInfo.setName("三分钟热度");
        planOperationInfo.setType(PlanOperationInfo.CREATE);
        planOperationInfo.setTime(TimeUtil.getCurrentDateTimeInString());
        planOperationInfo.setContent("创建了这个子任务");
        planOperationInfo.setPlanSecondItemInfoId(planSecondItemInfo.getPlanSecondItemInfoId());
        PlanOperationInfo object1 = mRealm.copyToRealm(planOperationInfo);

        LogUtil.eLog("这个时间提醒设置："+planSecondItemInfo.isHasNotification());
        if( planSecondItemInfo.isHasNotification() ){

            long notificationInfoId = TimeUtil.getCurrentTimeInLong();

            planSecondItemInfo.setNotificationInfoId(notificationInfoId);

            /**
             * 存储任务提醒时间
             */
            NotificationInfo notificationInfo = new NotificationInfo();
            notificationInfo.setNotificationInfoId(notificationInfoId);
            notificationInfo.setTime(planSecondItemInfo.getTime());

            NotificationInfo object = mRealm.copyToRealm(notificationInfo);

        }

        PlanSecondItemInfo object2 = mRealm.copyToRealm(planSecondItemInfo);

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

    @Override
    public void addPlanThirdItem(PlanThirdItemInfo planThirdItemInfo, ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("添加失败");
            return;
        }

        mRealm.beginTransaction();
        PlanThirdItemInfo object = mRealm.copyToRealm(planThirdItemInfo);

        PlanOperationInfo planOperationInfo = new PlanOperationInfo();
        planOperationInfo.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
        planOperationInfo.setName("三分钟热度");
        planOperationInfo.setTime(TimeUtil.getCurrentDateTimeInString());
        planOperationInfo.setContent("创建了单元任务："+planThirdItemInfo.getTitle());
        planOperationInfo.setPlanSecondItemInfoId(planThirdItemInfo.getPlanSecondItemInfoId());
        PlanOperationInfo object1 = mRealm.copyToRealm(planOperationInfo);

        mRealm.commitTransaction();

        callback.onResult("添加成功");

    }

    @Override
    public void getPlanThirdItemInfoBySecondId(long planSecondItemInfoId, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        List<PlanThirdItemInfo> planThirdItemInfos = mRealm.where(PlanThirdItemInfo.class).equalTo("planSecondItemInfoId", planSecondItemInfoId).findAll();

        callback.onResult(planThirdItemInfos);
    }

    @Override
    public void addPlanSecondItemRecord(PlanOperationInfo planOperationInfo, ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("添加失败");
            return;
        }

        mRealm.beginTransaction();
        PlanOperationInfo object = mRealm.copyToRealm(planOperationInfo);
        mRealm.commitTransaction();

        callback.onResult("添加成功");

    }

    @Override
    public void getPlanOperationInfoBySecondId(long planSecondItemInfoId, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        List<PlanOperationInfo> planOperationInfos = mRealm.where(PlanOperationInfo.class).equalTo("planSecondItemInfoId", planSecondItemInfoId).findAll();

        callback.onResult(planOperationInfos);
    }

    @Override
    public void modifyPlanSecondItemInfo(PlanSecondItemInfo planSecondItemInfo, String content, String time, int modifyModle, ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        String mContent = planSecondItemInfo.getContent();
        String mTime = planSecondItemInfo.getTime();

        mRealm.beginTransaction();

        planSecondItemInfo.setContent(content);
        planSecondItemInfo.setTime(time);

        String oTime = TimeUtil.getCurrentDateTimeInString();

        switch ( modifyModle ){
            case MODIFY_CONTENT:
                PlanOperationInfo planOperationInfo = new PlanOperationInfo();
                planOperationInfo.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
                planOperationInfo.setName("三分钟热度");
                planOperationInfo.setTime(oTime);
                planOperationInfo.setType(PlanOperationInfo.MODIFY_TEXT);
                planOperationInfo.setContent("修改了任务的内容，原内容为："+mContent);
                planOperationInfo.setPlanSecondItemInfoId(planSecondItemInfo.getPlanSecondItemInfoId());
                PlanOperationInfo object = mRealm.copyToRealm(planOperationInfo);
                break;
            case MODIFY_TIME:
                PlanOperationInfo planOperationInfo1 = new PlanOperationInfo();
                planOperationInfo1.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
                planOperationInfo1.setName("三分钟热度");
                planOperationInfo1.setType(PlanOperationInfo.MODIFY_TIME);
                planOperationInfo1.setTime(oTime);
                planOperationInfo1.setPlanSecondItemInfoId(planSecondItemInfo.getPlanSecondItemInfoId());
                if( mTime.equals("") )
                    planOperationInfo1.setContent("修改了任务的时间，原时间并没有指定");
                else
                    planOperationInfo1.setContent("修改了任务的时间，原时间为："+mTime);
                PlanOperationInfo object1 = mRealm.copyToRealm(planOperationInfo1);
                break;
            case MODIFY_CONTENT_AND_TIME:
                PlanOperationInfo planOperationInfo2 = new PlanOperationInfo();
                planOperationInfo2.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
                planOperationInfo2.setName("三分钟热度");
                planOperationInfo2.setTime(oTime);
                planOperationInfo2.setType(PlanOperationInfo.MODIFY_TEXT);
                planOperationInfo2.setContent("修改了任务的内容，原内容为："+mContent);
                planOperationInfo2.setPlanSecondItemInfoId(planSecondItemInfo.getPlanSecondItemInfoId());
                PlanOperationInfo object2 = mRealm.copyToRealm(planOperationInfo2);

                PlanOperationInfo planOperationInfo3 = new PlanOperationInfo();
                planOperationInfo3.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
                planOperationInfo3.setName("三分钟热度");
                planOperationInfo3.setTime(oTime);
                planOperationInfo3.setType(PlanOperationInfo.MODIFY_TIME);
                planOperationInfo3.setPlanSecondItemInfoId(planSecondItemInfo.getPlanSecondItemInfoId());
                if( mTime.equals("") )
                    planOperationInfo3.setContent("修改了任务的时间，原时间并没有指定");
                else
                    planOperationInfo3.setContent("修改了任务的时间，原时间为："+mTime);
                PlanOperationInfo object3 = mRealm.copyToRealm(planOperationInfo3);
                break;
        }


        mRealm.commitTransaction();

        callback.onResult("");

    }

    @Override
    public void modifyPlanThirdItemInfoStateById(long planThirdItemInfoId, boolean isFinished, ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        mRealm.beginTransaction();

        PlanThirdItemInfo planThirdItemInfo = mRealm.where(PlanThirdItemInfo.class).equalTo("planThirdItemInfoId", planThirdItemInfoId)
                .findFirst();
        planThirdItemInfo.setFinished(isFinished);

        PlanOperationInfo planOperationInfo = new PlanOperationInfo();
        planOperationInfo.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
        planOperationInfo.setName("三分钟热度");
        planOperationInfo.setTime(TimeUtil.getCurrentDateTimeInString());
        if( isFinished == true ) {
            planOperationInfo.setType(PlanOperationInfo.FINISH);
            planOperationInfo.setContent("完成了单元任务：" + planThirdItemInfo.getTitle());
        }else{
            planOperationInfo.setType(PlanOperationInfo.FINISH);
            planOperationInfo.setContent("重新开启了单元任务：" + planThirdItemInfo.getTitle());
        }
        planOperationInfo.setPlanSecondItemInfoId(planThirdItemInfo.getPlanSecondItemInfoId());
        PlanOperationInfo object = mRealm.copyToRealm(planOperationInfo);

        mRealm.commitTransaction();

        callback.onResult("");
    }

    @Override
    public void modifyPlanSecondItemInfoStateById(long planSecondItemInfoId, boolean isChecked, ICallback callback) {

        checkRealm(callback);

        mRealm.beginTransaction();

        PlanSecondItemInfo planSecondItemInfo = mRealm.where(PlanSecondItemInfo.class).equalTo("planSecondItemInfoId", planSecondItemInfoId)
                .findFirst();
        planSecondItemInfo.setFinished(isChecked);

        PlanOperationInfo planOperationInfo = new PlanOperationInfo();
        planOperationInfo.setPlanOperationInfoId(TimeUtil.getCurrentTimeInLong());
        planOperationInfo.setName("三分钟热度");
        planOperationInfo.setTime(TimeUtil.getCurrentDateTimeInString());
        if( isChecked == true ) {
            planOperationInfo.setType(PlanOperationInfo.FINISH);
            planOperationInfo.setContent("完成了子任务：" + planSecondItemInfo.getTitle());
        }else{
            planOperationInfo.setType(PlanOperationInfo.FINISH);
            planOperationInfo.setContent("重新开启了子任务：" + planSecondItemInfo.getTitle());
        }
        planOperationInfo.setPlanSecondItemInfoId(planSecondItemInfo.getPlanSecondItemInfoId());
        PlanOperationInfo object = mRealm.copyToRealm(planOperationInfo);

        mRealm.commitTransaction();

        callback.onResult("");
    }

    private void checkRealm(ICallback callback){
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }
    }
}
