package com.young.planhelper.mvp.schedule.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;
import com.young.planhelper.mvp.schedule.model.biz.IScheduleBiz;
import com.young.planhelper.mvp.schedule.model.biz.ScheduleBIz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/17  20:00
 */


public class ScheduleAddPresenter extends Presenter implements IScheduleAddPresenter{

    private IScheduleBiz mBiz;

    public ScheduleAddPresenter(IView view, Context context) {
        super(view, context);
        this.mBiz = new ScheduleBIz(context);
    }

    /**
     * 添加备忘任务
     * @param backlogInfo
     */
    @Override
    public void addBacklogInfo(BacklogInfo backlogInfo, IBiz.ICallback callback) {
        mBiz.addBacklogInfo(backlogInfo, callback);
    }

    @Override
    public void initData() {

    }
}
