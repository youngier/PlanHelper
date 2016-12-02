package com.young.planhelper.mvp.schedule.model.biz;

import android.content.Context;
import android.util.TimeUtils;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

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
    public void getBackLogInfoToday(ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(ADD_FAILED);
            return;
        }

        List<BacklogInfo> backlogInfos = mRealm.where(BacklogInfo.class).between("time", TimeUtil.getTodayStartTime(), TimeUtil.getTodayEndTime()).findAll();

        callback.onResult(backlogInfos);
    }

}
