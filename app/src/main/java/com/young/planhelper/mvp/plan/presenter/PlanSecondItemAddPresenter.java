package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanSecondItemInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/24  14:40
 */


public class PlanSecondItemAddPresenter extends Presenter implements IPlanSecondItemAddPresenter{

    private IPlanBiz mBiz;

    public PlanSecondItemAddPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addPlanSecondItem(PlanSecondItemInfo planSecondItemInfo, IBiz.ICallback callback) {
        mBiz.addPlanSecondItem(planSecondItemInfo, callback);
    }
}
