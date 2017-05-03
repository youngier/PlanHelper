package com.young.planhelper.mvp.overview.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.friend.model.biz.FriendBiz;
import com.young.planhelper.mvp.friend.model.biz.IFriendBiz;
import com.young.planhelper.mvp.overview.model.IOverviewBiz;
import com.young.planhelper.mvp.overview.model.OverviewBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:17/3/27  15:51
 */


public class OverviewPresenter extends Presenter implements IOverviewPresenter{

    private IOverviewBiz mBiz;

    public OverviewPresenter(IView view, Context context) {
        super(view, context);

        mBiz = new OverviewBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getDataByMonth(int year, IBiz.ICallback iCallback) {
        mBiz.getDataByMonth(year, iCallback);
    }

    @Override
    public void getDataByWeek(int year, IBiz.ICallback iCallback) {
        mBiz.getDataByWeek(year, iCallback);
    }

    @Override
    public void getDataByHour(int year, IBiz.ICallback iCallback) {
        mBiz.getDataByHour(year, iCallback);
    }
}
