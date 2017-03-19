package com.young.planhelper.mvp.timeline.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.timeline.model.biz.ITimelineBiz;
import com.young.planhelper.mvp.timeline.model.biz.TimelineBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/1/22  14:33
 */


public class TimelinePresenter extends Presenter implements ITimelinePresenter{

    private ITimelineBiz mBiz;

    public TimelinePresenter(IView view, Context context) {
        super(view, context);
        this.mBiz = new TimelineBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getTimelineInfoByStatue(int statue, IBiz.ICallback callback) {
        mBiz.getTimelineInfoByStatue(statue, callback);
    }
}
