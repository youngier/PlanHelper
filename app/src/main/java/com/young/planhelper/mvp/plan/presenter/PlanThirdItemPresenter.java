package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanThirdItemInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/27  11:39
 */


public class PlanThirdItemPresenter extends Presenter implements IPlanThirdItemPresenter{

    private IPlanBiz mBiz;

    public PlanThirdItemPresenter(IView view, Context context) {
        super(view, context);
        mBiz = new PlanBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addPlanThirdItem(PlanThirdItemInfo planThirdItemInfo, IBiz.ICallback callback) {
        mBiz.addPlanThirdItem(planThirdItemInfo, callback);
    }
}
