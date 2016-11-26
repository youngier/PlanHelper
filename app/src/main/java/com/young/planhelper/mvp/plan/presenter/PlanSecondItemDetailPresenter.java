package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/25  19:31
 */


public class PlanSecondItemDetailPresenter extends Presenter implements IPlanSecondItemDetailPresenter{

    private IPlanBiz mBiz;

    public PlanSecondItemDetailPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getPlanSecondItemInfoById(long planSecondItemInfoId, IBiz.ICallback callback) {
        mBiz.getPlanSecondItemInfoById(planSecondItemInfoId, callback);
    }
}
