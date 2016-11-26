package com.young.planhelper.mvp.schedule.model.biz;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.LogUtil;

import java.util.List;

import io.realm.Realm;

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
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("添加失败");
            return;
        }

        mRealm.beginTransaction();
        BacklogInfo object = mRealm.copyToRealm(backlogInfo);
        mRealm.commitTransaction();

        callback.onResult("添加成功");
    }

    @Override
    public void getBacklogInfo(ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog("Realm没有初始化");
            callback.onResult("获取失败");
            return;
        }

        List<BacklogInfo> backlogInfos = mRealm.where(BacklogInfo.class).findAll();

        callback.onResult(backlogInfos);
    }

}
