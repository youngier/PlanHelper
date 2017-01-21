package com.young.planhelper.mvp.schedule.model.biz;

import android.content.Context;
import android.util.TimeUtils;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

import static com.young.planhelper.constant.AppConstant.ADD_FAILED;
import static com.young.planhelper.constant.AppConstant.ADD_SUCCESS;
import static com.young.planhelper.constant.AppConstant.REALM_NOT_INIT;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/16  20:51
 */


public class ScheduleBIz extends Biz implements IScheduleBiz {

    private Realm mRealm;

    public ScheduleBIz(Context context){
        this.mRealm = AppApplication.get(context).getmAppComponent().getRealm();
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void addBacklogInfo(BacklogInfo backlogInfo, final ICallback callback) {

        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        mRealm.beginTransaction();
        BacklogInfo object = mRealm.copyToRealm(backlogInfo);
        mRealm.commitTransaction();

        callback.onResult(ADD_SUCCESS);
    }

    @Override
    public void getBacklogInfo(ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        List<BacklogInfo> backlogInfos = mRealm.where(BacklogInfo.class).findAll();

        callback.onResult(backlogInfos);
    }

    @Override
    public void getBackLogInfoToday(String date, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = df.parse(date + " 00:00");
            date2 = df.parse(date + " 23:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long timestamp1 = cal.getTimeInMillis();

        cal.setTime(date2);
        long timestamp2 = cal.getTimeInMillis();

        List<BacklogInfo> backlogInfos = mRealm.where(BacklogInfo.class).between("toTime", timestamp1, timestamp2).findAll();

        callback.onResult(backlogInfos);
    }

    @Override
    public void getBackLogInfoToday(ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        List<BacklogInfo> backlogInfos = mRealm.where(BacklogInfo.class).between("toTime", TimeUtil.getTodayStartTime(), TimeUtil.getTodayEndTime()).findAll();

        callback.onResult(backlogInfos);
    }

    @Override
    public void getBackLogInfoFuture(ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        List<BacklogInfo> backlogInfos = mRealm.where(BacklogInfo.class).greaterThan("toTime", TimeUtil.getTodayEndTime()).findAll();

        callback.onResult(backlogInfos);
    }

    @Override
    public void getBackLogInfoOverdue(ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        List<BacklogInfo> backlogInfos = mRealm.where(BacklogInfo.class).equalTo("statue", BacklogInfo.OVERDUE).lessThan("time", TimeUtil.getTodayStartTime()).findAll();

        callback.onResult(backlogInfos);
    }

    @Override
    public void getBackLogInfoDetail(long backlogInfoId, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        BacklogInfo backlogInfo = mRealm.where(BacklogInfo.class).equalTo("backlogInfoId", backlogInfoId).findFirst();

        callback.onResult(backlogInfo);
    }

}
