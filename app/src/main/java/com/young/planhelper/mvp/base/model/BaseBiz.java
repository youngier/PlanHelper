package com.young.planhelper.mvp.base.model;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.login.model.bean.User;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.network.BackupsApiService;
import com.young.planhelper.network.LoginApiService;
import com.young.planhelper.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Retrofit;
import rx.Observable;

import static com.young.planhelper.constant.AppConstant.ADD_FAILED;
import static com.young.planhelper.constant.AppConstant.REALM_NOT_INIT;

/**
 * @author: young
 * date:17/5/9  18:07
 */


public class BaseBiz extends Biz implements IBaseBiz{

    private final Retrofit mRetrofit;
    private final Realm mRealm;
    private User mUser;

    public BaseBiz(Context context) {
        mRetrofit = AppApplication.get(context).getmAppComponent().getRetrofit();
        mUser = AppApplication.get(context).getmAppComponent().getUserInfo();
        mRealm = AppApplication.get(context).getmAppComponent().getRealm();
    }

    @Override
    public void getData(ICallback callback) {

    }


    @Override
    public List<BacklogInfo> getBacklogList() {
        if (mRealm == null) {
            LogUtil.eLog(REALM_NOT_INIT);
            return new ArrayList<BacklogInfo>() ;
        }

        List<BacklogInfo> backlogInfoList = mRealm.where(BacklogInfo.class).findAll();

        return backlogInfoList;

    }
}
