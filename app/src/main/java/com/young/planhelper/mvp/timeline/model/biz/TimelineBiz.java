package com.young.planhelper.mvp.timeline.model.biz;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.timeline.presenter.ITimelinePresenter;
import com.young.planhelper.util.LogUtil;
import com.young.planhelper.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

import static com.young.planhelper.constant.AppConstant.ADD_FAILED;
import static com.young.planhelper.constant.AppConstant.GET_FAILED;
import static com.young.planhelper.constant.AppConstant.REALM_NOT_INIT;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/22  14:35
 */


public class TimelineBiz extends Biz implements ITimelineBiz{

    private Realm mRealm;

    public TimelineBiz(Context context) {
        this.mRealm = AppApplication.get(context).getmAppComponent().getRealm();
    }

    @Override
    public void getData(ICallback callback) {

    }

    @Override
    public void getTimelineInfoByStatue(int statue, ICallback callback) {
        if( mRealm == null ){
            LogUtil.eLog(REALM_NOT_INIT);
            callback.onResult(GET_FAILED);
            return;
        }

        List<BacklogInfo> backlogInfos = new ArrayList<>();

        switch ( statue ){
            case Toolbar.ALL:
                backlogInfos = mRealm.where(BacklogInfo.class).findAllSorted("backlogInfoId");
                break;
            case Toolbar.FINISHED:
                backlogInfos = mRealm.where(BacklogInfo.class).equalTo("statue", BacklogInfo.FINISHED).findAllSorted("backlogInfoId");
                break;
            case Toolbar.DOING:
                backlogInfos = mRealm.where(BacklogInfo.class).equalTo("statue", BacklogInfo.UNFINISH).findAllSorted("backlogInfoId");
                break;
            case Toolbar.OVERDUE:
                backlogInfos = mRealm.where(BacklogInfo.class).equalTo("statue", BacklogInfo.OVERDUE).findAllSorted("backlogInfoId");
                break;
        }

        callback.onResult(backlogInfos);
    }
}
