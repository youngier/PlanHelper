package com.young.planhelper.mvp.schedule.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.schedule.model.biz.IScheduleBiz;
import com.young.planhelper.mvp.schedule.model.biz.ScheduleBIz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/12/5  11:10
 */


public class ScheduleDetailPresenter extends Presenter implements IScheduleDetailPresenter{

    private IScheduleBiz mBiz;

    public ScheduleDetailPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new ScheduleBIz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getBacklogInfoDetail(long backlogInfoId, IBiz.ICallback callback) {
        mBiz.getBackLogInfoDetail(backlogInfoId, callback);
    }
}
