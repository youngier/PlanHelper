package com.young.planhelper.mvp.profile.model.biz;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.util.TimeUtil;

import java.text.ParseException;
import java.util.List;

import io.realm.Realm;

import static com.young.planhelper.constant.AppConstant.GET_FAILED;
import static com.young.planhelper.constant.AppConstant.REALM_NOT_INIT;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/29  09:21
 */


public class ProfileBiz extends Biz implements IProfileBiz{

    private Realm mRealm;

    public ProfileBiz(Context context) {
        this.mRealm = AppApplication.get(context).getmAppComponent().getRealm();
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void getProfileInfoByMonth(int statue, String time, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(GET_FAILED);
            return;
        }

        String beginTime = time + "1日 00:00";
        String endTime = time + "30日 23:59";

        List<BacklogInfo> backlogInfos = null;
        try {
            backlogInfos = mRealm.where(BacklogInfo.class)
                    .greaterThan("backlogInfoId", TimeUtil.dateToStamp(beginTime))
                    .lessThan("backlogInfoId", TimeUtil.dateToStamp(endTime))
                    .equalTo("statue", statue)
                    .findAll();
        } catch (ParseException e) {
            callback.onResult(GET_FAILED);
        }

        callback.onResult(backlogInfos);
    }
}
