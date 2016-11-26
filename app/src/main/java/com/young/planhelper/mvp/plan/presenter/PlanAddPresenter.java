package com.young.planhelper.mvp.plan.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.plan.model.bean.PlanInfo;
import com.young.planhelper.mvp.plan.model.biz.IPlanBiz;
import com.young.planhelper.mvp.plan.model.biz.PlanBiz;

/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/11/21  22:07
 */


public class PlanAddPresenter extends Presenter implements IPlanAddPresenter{

    private IPlanBiz mBIz;

    public PlanAddPresenter(IView view, Context context) {
        super(view, context);
        mBIz = new PlanBiz(context);
    }

    @Override
    public void initData() {

    }

    public void addPlan(PlanInfo planInfo, IBiz.ICallback callback){
        mBIz.addPlan(planInfo, callback);
    }
}
