package com.young.planhelper.mvp.schedule.presenter;

import android.content.Context;

import com.young.planhelper.application.AppApplication;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.model.biz.IScheduleBiz;
import com.young.planhelper.mvp.schedule.model.biz.ScheduleBIz;

import java.util.List;

import io.realm.Realm;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/20  12:30
 */


public class SchedulePresenter extends Presenter implements ISchedulePresenter{

    private IScheduleBiz mBiz;

    public SchedulePresenter(IView view, Context context) {
        super(view, context);
        this.mBiz = new ScheduleBIz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getBacklogInfos(IBiz.ICallback callback) {
        mBiz.getBacklogInfo(callback);
    }

    @Override
    public void getBackLogInfoToday(IBiz.ICallback callback) {
        mBiz.getBackLogInfoToday(callback);
    }
}
