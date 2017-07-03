package com.young.planhelper.mvp.person.model;

import android.content.Context;
import android.util.Log;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanOperationInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.network.BackupsApiService;
import com.young.planhelper.network.RecoveryApiService;
import com.young.planhelper.util.JsonUitl;
import com.young.planhelper.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

            //获得Observable对象
            Observable<String> data = backupsApiService.backups(mUser.getUserId(),
                    JsonUitl.objectToString(result), JsonUitl.objectToString(planResult),
                    JsonUitl.objectToString(planItemResult), JsonUitl.objectToString(planSecondItemResult),
                    JsonUitl.objectToString(planThirdItemResult), JsonUitl.objectToString(planOperationResult));

            iCallback.onResult(data);
        }
    }

    @Override
    public void recovery(ICallback iCallback) {

        RecoveryApiService recoveryApiService = mRetrofit.create(RecoveryApiService.class);

        //获得Observable对象
        Observable<String> data = recoveryApiService.recovery(mUser.getUserId());
        data.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        Log.i("way", "onCompleted");
                        iCallback.onResult("1");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("way", "onError" + e.toString());
                        iCallback.onResult("0");
                    }

                    @Override
                    public void onNext(String s) {
                        recoveryToLocal(s);
                        iCallback.onResult("1");
                    }
                });
    }

    /**
     * 恢复数据到本地
     * @param s
     */
    private void recoveryToLocal(String s) {

        s = s.replace("@@@@", "{");
        s = s.replace("$$$$", "}");
        s = s.replace("%%%%", ":");
        s = s.replace("^^^^", "[");
        s = s.replace("@$%^", "]");
        s = s.replace("$@^%", ",");
        s = s.replace("$^$^", " ");
        s = s.replace("@%@%", "\\n");
        s = s.replace("~~~~", "\\r");
        s = s.replace("~^^~", "/");
        s = s.replace("~@@~", "\\u003d");

        LogUtil.eLog("替换后："+s);

        List<BacklogInfo> backlogList = new ArrayList<>();
        List<PlanInfo> planInfoList = new ArrayList<>();
        List<PlanItemInfo> planItemInfoList = new ArrayList<>();
        List<PlanSecondItemInfo> planSecondItemInfoList = new ArrayList<>();
        List<PlanThirdItemInfo> planThirdItemInfoList = new ArrayList<>();
        List<PlanOperationInfo> planOperationInfoList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray backlogArray = jsonObject.getJSONArray("backlogInfos");
            JSONArray planInfoArray = jsonObject.getJSONArray("planInfos");
            JSONArray planItemInfoArray = jsonObject.getJSONArray("planItemInfos");
            JSONArray planSecondItemInfoArray = jsonObject.getJSONArray("planSecondItemInfos");
            JSONArray planThirdItemInfoArray = jsonObject.getJSONArray("planThirdItemInfos");
            JSONArray planOperationInfoArray = jsonObject.getJSONArray("planOperationInfos");

            for (int i = 0; i < backlogArray.length(); i++) {
                JSONObject object = backlogArray.getJSONObject(i);
                BacklogInfo backlogInfo = (BacklogInfo) JsonUitl.stringToObject(object.toString(), BacklogInfo.class);
                backlogList.add(backlogInfo);
            }

            for (int i = 0; i < planInfoArray.length(); i++) {
                JSONObject object = planInfoArray.getJSONObject(i);
                PlanInfo planInfo = (PlanInfo) JsonUitl.stringToObject(object.toString(), PlanInfo.class);
                planInfoList.add(planInfo);
            }

            for (int i = 0; i < planItemInfoArray.length(); i++) {
                JSONObject object = planItemInfoArray.getJSONObject(i);
                PlanItemInfo planItemInfo = (PlanItemInfo) JsonUitl.stringToObject(object.toString(), PlanItemInfo.class);
                planItemInfoList.add(planItemInfo);
            }

            for (int i = 0; i < planSecondItemInfoArray.length(); i++) {
                JSONObject object = planSecondItemInfoArray.getJSONObject(i);
                PlanSecondItemInfo planSecondItemInfo = (PlanSecondItemInfo) JsonUitl.stringToObject(object.toString(), PlanSecondItemInfo.class);
                planSecondItemInfoList.add(planSecondItemInfo);
            }

            for (int i = 0; i < planThirdItemInfoArray.length(); i++) {
                JSONObject object = planThirdItemInfoArray.getJSONObject(i);
                PlanThirdItemInfo planThirdItemInfo = (PlanThirdItemInfo) JsonUitl.stringToObject(object.toString(), PlanThirdItemInfo.class);
                planThirdItemInfoList.add(planThirdItemInfo);
            }

            for (int i = 0; i < planOperationInfoArray.length(); i++) {
                JSONObject object = planOperationInfoArray.getJSONObject(i);
                PlanOperationInfo planOperationInfo = (PlanOperationInfo) JsonUitl.stringToObject(object.toString(), PlanOperationInfo.class);
                planOperationInfoList.add(planOperationInfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (mRealm == null) {
            LogUtil.eLog(REALM_NOT_INIT);
        }

        for (int i=0; i<backlogList.size(); i++){

            BacklogInfo backlogInfoTemp = backlogList.get(i);

            BacklogInfo backlogInfo = mRealm.where(BacklogInfo.class).
                    equalTo("backlogInfoId", backlogInfoTemp.getBacklogInfoId()).findFirst();

            mRealm.beginTransaction();

            if( backlogInfo == null )
                mRealm.copyToRealm(backlogInfoTemp);
            else{
                backlogInfo.setPlanInfoId(backlogInfoTemp.getPlanInfoId());
                backlogInfo.setContent(backlogInfoTemp.getContent());
                backlogInfo.setStatue(backlogInfoTemp.getStatue());
                backlogInfo.setFromTime(backlogInfoTemp.getFromTime());
                backlogInfo.setToTime(backlogInfoTemp.getToTime());
                backlogInfo.setBacklogImageInfos(backlogInfoTemp.getBacklogImageInfos());
                backlogInfo.setLocation(backlogInfoTemp.getLocation());
                backlogInfo.setMembers(backlogInfoTemp.getMembers());
                backlogInfo.setRepeatType(backlogInfoTemp.getRepeatType());
            }

            mRealm.commitTransaction();
        }

        for (int i=0; i<planInfoList.size(); i++){

            PlanInfo planInfoTemp = planInfoList.get(i);

            PlanInfo planInfo = mRealm.where(PlanInfo.class).
                    equalTo("planInfoId", planInfoTemp.getPlanInfoId()).findFirst();

            mRealm.beginTransaction();

            if( planInfo == null )
                mRealm.copyToRealm(planInfoTemp);
            else{
                planInfo.setTitle(planInfoTemp.getTitle());
                planInfo.setAuthority(planInfoTemp.getAuthority());
                planInfo.setMembers(planInfoTemp.getMembers());
                planInfo.setSynchronized(planInfoTemp.isSynchronized());
            }

            mRealm.commitTransaction();
        }

        for (int i=0; i<planItemInfoList.size(); i++){

            PlanItemInfo planItemInfoTemp = planItemInfoList.get(i);

            PlanItemInfo planItemInfo = mRealm.where(PlanItemInfo.class).
                    equalTo("planItemInfoId", planItemInfoTemp.getPlanItemInfoId()).findFirst();

            mRealm.beginTransaction();

            if( planItemInfo == null )
                mRealm.copyToRealm(planItemInfoTemp);
            else{
                planItemInfo.setPlanInfoId(planItemInfoTemp.getPlanInfoId());
                planItemInfo.setTitle(planItemInfoTemp.getTitle());
            }

            mRealm.commitTransaction();
        }

        for (int i=0; i<planSecondItemInfoList.size(); i++){

            PlanSecondItemInfo planSecondItemInfoTemp = planSecondItemInfoList.get(i);

            PlanSecondItemInfo planSecondItemInfo = mRealm.where(PlanSecondItemInfo.class).
                    equalTo("planSecondItemInfoId", planSecondItemInfoTemp.getPlanSecondItemInfoId()).findFirst();

            mRealm.beginTransaction();

            if( planSecondItemInfo == null )
                mRealm.copyToRealm(planSecondItemInfoTemp);
            else{
                planSecondItemInfo.setTitle(planSecondItemInfoTemp.getTitle());
                planSecondItemInfo.setContent(planSecondItemInfoTemp.getContent());
                planSecondItemInfo.setHasNotification(planSecondItemInfoTemp.isHasNotification());
                planSecondItemInfo.setNotificationInfoId(planSecondItemInfoTemp.getNotificationInfoId());
                planSecondItemInfo.setTime(planSecondItemInfoTemp.getTime());
                planSecondItemInfo.setFinished(planSecondItemInfoTemp.isFinished());
                planSecondItemInfo.setPlanItemInfoId(planSecondItemInfo.getPlanItemInfoId());
            }

            mRealm.commitTransaction();
        }

        for (int i=0; i<planThirdItemInfoList.size(); i++){

            PlanThirdItemInfo planThirdItemInfoTemp = planThirdItemInfoList.get(i);

            PlanThirdItemInfo planThirdItemInfo = mRealm.where(PlanThirdItemInfo.class).
                    equalTo("planThirdItemInfoId", planThirdItemInfoTemp.getPlanThirdItemInfoId()).findFirst();

            mRealm.beginTransaction();

            if( planThirdItemInfo == null )
                mRealm.copyToRealm(planThirdItemInfoTemp);
            else{
                planThirdItemInfo.setTitle(planThirdItemInfoTemp.getTitle());
                planThirdItemInfo.setFinished(planThirdItemInfoTemp.isFinished());
                planThirdItemInfo.setTime(planThirdItemInfoTemp.getTime());
                planThirdItemInfo.setPlanSecondItemInfoId(planThirdItemInfoTemp.getPlanSecondItemInfoId());
            }

            mRealm.commitTransaction();
        }

        for (int i=0; i<planOperationInfoList.size(); i++){

            PlanOperationInfo planOperationInfoTemp = planOperationInfoList.get(i);

            PlanOperationInfo planOperationInfo = mRealm.where(PlanOperationInfo.class).
                    equalTo("planOperationInfoId", planOperationInfoTemp.getPlanOperationInfoId()).findFirst();

            mRealm.beginTransaction();

            if( planOperationInfo == null )
                mRealm.copyToRealm(planOperationInfoTemp);
            else{
                planOperationInfo.setContent(planOperationInfoTemp.getContent());
                planOperationInfo.setPlanSecondItemInfoId(planOperationInfoTemp.getPlanSecondItemInfoId());
                planOperationInfo.setType(planOperationInfoTemp.getType());
                planOperationInfo.setTime(planOperationInfoTemp.getTime());
                planOperationInfo.setName(planOperationInfoTemp.getName());
            }

            mRealm.commitTransaction();
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

        LogUtil.eLog("位置"+"："+result.get(0).toString());
        LogUtil.eLog("结果"+"："+JsonUitl.objectToString(result));

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
