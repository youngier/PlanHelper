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
 * date:16/11/24  14:50
 */


public class PlanSecondItemPresenter extends Presenter implements IPlanSecondItemPresenter{

    private IPlanBiz mBiz;

    public PlanSecondItemPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getPlanSecondItemInfo(long planItemInfoId, IBiz.ICallback callback) {
        mBiz.getPlanSecondItemInfo(planItemInfoId, callback);
    }

    @Override
    public void modifyPlanSecondItemInfoStateById(long planSecondItemInfoId, boolean isChecked, IBiz.ICallback callback) {
        mBiz.modifyPlanSecondItemInfoStateById(planSecondItemInfoId, isChecked, callback);
    }
}
